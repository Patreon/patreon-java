package com.patreon;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.github.jasminb.jsonapi.*;
import com.patreon.resources.shared.BaseResource;
import com.patreon.resources.shared.Field;
import com.patreon.resources.v1.Campaign;
import com.patreon.resources.v1.Pledge;
import com.patreon.resources.v1.RequestUtil;
import com.patreon.resources.v1.User;
import com.patreon.resources.v2.CampaignV2;
import com.patreon.resources.v2.Member;
import com.patreon.resources.v2.Tier;
import com.patreon.resources.v2.UserV2;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.client.utils.URLEncodedUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

public class PatreonAPI {
  /**
   * The base URI for requests to the patreon API. This may be overridden (e.g. for testing) by passing
   * -Dpatreon.rest.uri="https://my.other.server.com" as jvm arguments
   */
  public static final String BASE_URI = System.getProperty("patreon.rest.uri", "https://www.patreon.com");

  private static final Logger LOG = LoggerFactory.getLogger(PatreonAPI.class);
  private final String accessToken;
  private final RequestUtil requestUtil;
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
    this(accessToken, new RequestUtil());
  }

  /**
   * For use in test.
   */
  PatreonAPI(String accessToken, RequestUtil requestUtil) {
    this.accessToken = accessToken;
    this.requestUtil = requestUtil;

    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
    objectMapper.configure(com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    this.converter = new ResourceConverter(
      objectMapper,
      User.class,
      UserV2.class,
      Campaign.class,
      Member.class,
      CampaignV2.class,
      Pledge.class,
      Tier.class
    );
    this.converter.enableDeserializationOption(DeserializationFeature.ALLOW_UNKNOWN_INCLUSIONS);
  }

  public JSONAPIDocument<UserV2> v2FetchIdentity() throws IOException {
    URIBuilder pathBuilder = new URIBuilder()
      .setPath("v2/identity")
      .addParameter("include", "campaign");
    addFieldsParam(pathBuilder, UserV2.class, UserV2.UserField.getDefaultFields());
    addFieldsParam(pathBuilder, CampaignV2.class, CampaignV2.CampaignField.getDefaultFields());
    return converter.readDocument(
      getDataStream(pathBuilder.toString()),
      UserV2.class
    );
  }

  public JSONAPIDocument<List<Member>> v2FetchCampaignMembers(String campaignId, Integer count) throws IOException {
    // Check count is less than 1000
    if (count > 1000) {
      throw new IllegalArgumentException("Count must be less than 1000");
    }

    URIBuilder pathBuilder = new URIBuilder()
      .setPath(String.format("v2/campaigns/%s/members", campaignId))
      .addParameter("include", "user,currently_entitled_tiers")
      .addParameter("page[count]", String.valueOf(count));
    addFieldsParam(pathBuilder, Member.class, Member.MemberField.getDefaultFields());
    addFieldsParam(pathBuilder, UserV2.class, UserV2.UserField.getDefaultFields());
    addFieldsParam(pathBuilder, Tier.class, Tier.TierField.getDefaultFields());
    return converter.readDocumentCollection(
      getDataStream(pathBuilder.toString()),
      Member.class
    );
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
                    .setPath("api/current_user")
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
                    .setPath("api/current_user/campaigns")
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
                               .setPath(String.format("api/campaigns/%s/pledges", campaignId))
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
    return fetchAllPledges(campaignId, false);
  }
  /**
   * Retrieve all pledges for the specified campaign
   *
   * @param campaignId id for campaign to retrieve
   * @return the list of pledges
   * @throws IOException Thrown when the GET request failed
   */
  public List<Pledge> fetchAllPledges(String campaignId, boolean full) throws IOException {
    Set<Pledge> pledges = new HashSet<>();
    String cursor = null;
    Collection<Pledge.PledgeField> fields = null;
    if (full){
      fields = Pledge.PledgeField.getAllFields();
    }
    while (true) {
      JSONAPIDocument<List<Pledge>> pledgesPage = fetchPageOfPledges(campaignId, 15, cursor, fields);
      pledges.addAll(pledgesPage.get());
      cursor = getNextCursorFromDocument(pledgesPage);
      if (cursor == null) {
        break;
      }
    }
    return new ArrayList<>(pledges);
  }


  private InputStream getDataStream(String suffix) throws IOException {
    return this.requestUtil.request(suffix, this.accessToken);
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
    builder.addParameter("fields[" + typeStr + "]", String.join(",", fieldNames));

    return builder;
  }

}
