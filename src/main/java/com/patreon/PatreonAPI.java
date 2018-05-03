package com.patreon;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.github.jasminb.jsonapi.*;
import com.patreon.resources.Campaign;
import com.patreon.resources.Pledge;
import com.patreon.resources.User;
import com.patreon.resources.shared.BaseResource;
import com.patreon.resources.shared.Field;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.client.utils.URLEncodedUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

public class PatreonAPI {
  /**
   * The base URI for requests to the patreon API. This may be overridden (e.g. for testing) by passing
   * -Dpatreon.rest.uri="https://my.other.server.com" as jvm arguments
   */
  public static final String BASE_URI = System.getProperty("patreon.rest.uri", "https://www.patreon.com");

  private static final Logger LOG = LoggerFactory.getLogger(PatreonAPI.class);
  private final String accessToken;
  private ResourceConverter converter;

  /**
   * Create a new instance of the Patreon API. You only need <b>one</b> of these objects unless you are using the API
   * with multiple tokens
   *
   * @param accessToken The "Creator's Access Token" found on
   *                    <a href="https://www.patreon.com/platform/documentation/clients">the patreon client page</a>
   *                    <b>OR</b> OAuth access token
   */
  public PatreonAPI(String accessToken) {
    this.accessToken = accessToken;

    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
    this.converter = new ResourceConverter(
                                            objectMapper,
                                            User.class,
                                            Campaign.class,
                                            Pledge.class
    );
    this.converter.enableDeserializationOption(DeserializationFeature.ALLOW_UNKNOWN_INCLUSIONS);
  }

  /**
   * Get the user object of the creator
   *
   * @return the current user
   * @throws IOException Thrown when the GET request failed
   */
  public JSONAPIDocument<User> fetchUser() throws IOException {
    return fetchUser(null);
  }

  /**
   * Get the user object of the creator
   *
   * @param optionalFields A list of optional fields to request, or null.  See {@link User.UserField}
   * @return the current user
   * @throws IOException Thrown when the GET request failed
   */
  public JSONAPIDocument<User> fetchUser(Collection<User.UserField> optionalFields) throws IOException {
    URIBuilder pathBuilder = new URIBuilder()
                    .setPath("current_user")
                    .addParameter("include", "pledges");
    if (optionalFields != null) {
      Set<User.UserField> optionalAndDefaultFields = new HashSet<>(optionalFields);
      optionalAndDefaultFields.addAll(User.UserField.getDefaultFields());
      addFieldsParam(pathBuilder, User.class, optionalAndDefaultFields);
    }

    return converter.readDocument(
      getDataStream(pathBuilder.toString()),
      User.class
    );
  }

  /**
   * Get a list of campaigns the current creator is running - also contains other related data like Goals
   * Note: The first campaign data object is located at index 0 in the data list
   *
   * @return the list of campaigns
   * @throws IOException Thrown when the GET request failed
   */
  public JSONAPIDocument<List<Campaign>> fetchCampaigns() throws IOException {
    String path = new URIBuilder()
                    .setPath("current_user/campaigns")
                    .addParameter("include", "rewards,creator,goals")
                    .toString();
    return converter.readDocumentCollection(
      getDataStream(path),
      Campaign.class
    );
  }

  /**
   * Retrieve pledges for the specified campaign
   *
   * @param campaignId id for campaign to retrieve
   * @param pageSize   how many pledges to return
   * @param pageCursor A cursor retreived from a previous API call, or null for the initial page.
   *                   See {@link #getNextCursorFromDocument(JSONAPIDocument)}
   * @return the page of pledges
   * @throws IOException Thrown when the GET request failed
   */
  public JSONAPIDocument<List<Pledge>> fetchPageOfPledges(String campaignId, int pageSize, String pageCursor) throws IOException {
    return fetchPageOfPledges(campaignId, pageSize, pageCursor, null);
  }

  /**
   * Retrieve pledges for the specified campaign
   *
   * @param campaignId id for campaign to retrieve
   * @param pageSize   how many pledges to return
   * @param pageCursor A cursor retreived from a previous API call, or null for the initial page.
   *                   See {@link #getNextCursorFromDocument(JSONAPIDocument)}
   * @param optionalFields A list of optional fields to return.  See {@link Pledge.PledgeField}
   * @return the page of pledges
   * @throws IOException Thrown when the GET request failed
   */
  public JSONAPIDocument<List<Pledge>> fetchPageOfPledges(String campaignId, int pageSize, String pageCursor, Collection<Pledge.PledgeField> optionalFields) throws IOException {
    URIBuilder pathBuilder = new URIBuilder()
                               .setPath(String.format("campaigns/%s/pledges", campaignId))
                               .addParameter("page[count]", String.valueOf(pageSize));
    if (pageCursor != null) {
      pathBuilder.addParameter("page[cursor]", pageCursor);
    }
    if (optionalFields != null) {
      Set<Pledge.PledgeField> optionalAndDefaultFields = new HashSet<>(optionalFields);
      optionalAndDefaultFields.addAll(Pledge.PledgeField.getDefaultFields());
      addFieldsParam(pathBuilder, Pledge.class, optionalAndDefaultFields);
    }
    return converter.readDocumentCollection(
      getDataStream(pathBuilder.toString()),
      Pledge.class
    );
  }

  public String getNextCursorFromDocument(JSONAPIDocument document) {
    Links links = document.getLinks();
    if (links == null) {
      return null;
    }
    Link nextLink = links.getNext();
    if (nextLink == null) {
      return null;
    }
    String nextLinkString = nextLink.toString();
    try {
      List<NameValuePair> queryParameters = URLEncodedUtils.parse(new URI(nextLinkString), "utf8");
      for (NameValuePair pair : queryParameters) {
        String name = pair.getName();
        if (name.equals("page[cursor]")) {
          return pair.getValue();
        }
      }
    } catch (URISyntaxException e) {
      LOG.error(e.getMessage());
    }
    return null;
  }

  public List<Pledge> fetchAllPledges(String campaignId) throws IOException {
    Set<Pledge> pledges = new HashSet<>();
    String cursor = null;
    while (true) {
      JSONAPIDocument<List<Pledge>> pledgesPage = fetchPageOfPledges(campaignId, 15, cursor);
      pledges.addAll(pledgesPage.get());
      cursor = getNextCursorFromDocument(pledgesPage);
      if (cursor == null) {
        break;
      }
    }
    return new ArrayList<>(pledges);
  }

  private InputStream getDataStream(String suffix) throws IOException {
    try {
      String prefix = BASE_URI + "/api/oauth2/api/";
      URL url = new URL(prefix.concat(suffix));
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setRequestProperty("Authorization", "Bearer ".concat(this.accessToken));
      connection.setRequestProperty("User-Agent",
        String.format(
          "Patreon-Java, version %s, platform %s %s",
          getVersion(),
          System.getProperty("os.name"),
          System.getProperty("os.version")));
      return connection.getInputStream();
    } catch (IOException e) {
      LOG.error(e.getMessage());
      throw e;
    }
  }

  private String getVersion() throws IOException {
    InputStream resourceAsStream = this.getClass().getResourceAsStream("/version.properties");
    java.util.Properties prop = new java.util.Properties();
    prop.load(resourceAsStream);
    return prop.getProperty("version");
  }

  /**
   * Add fields[type]=fieldName,fieldName,fieldName as a query parameter to the request represented by builder
   * @param builder A URIBuilder building a request to the API
   * @param type A BaseResource annotated with {@link com.github.jasminb.jsonapi.annotations.Type}
   * @param fields A list of fields to include.  Only fields in this list will be retrieved in the query
   * @return builder
   */
  private URIBuilder addFieldsParam(URIBuilder builder, Class<? extends BaseResource> type, Collection<? extends Field> fields) {
    List<String> fieldNames = new ArrayList<>();
    for (Field f : fields) {
      fieldNames.add(f.getPropertyName());
    }
    String typeStr = BaseResource.getType(type);
    builder.addParameter("fields[" + typeStr + "]", join(fieldNames));

    return builder;
  }

  /**
   * Join a collection of strings with commas as separators.
   */
  private String join(Collection<? extends CharSequence> items) {
    if (items == null) {
      return null;
    }

    StringBuilder sb = new StringBuilder();
    boolean first = true;
    for (CharSequence item : items) {
      if (first) {
        first = false;
      } else {
        sb.append(",");
      }
      sb.append(item);
    }

    return sb.toString();
  }
}
