package com.patreon;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.github.jasminb.jsonapi.DeserializationFeature;
import com.github.jasminb.jsonapi.JSONAPIDocument;
import com.github.jasminb.jsonapi.ResourceConverter;
import com.patreon.resources.Campaign;
import com.patreon.resources.Pledge;
import com.patreon.resources.User;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

public class PatreonAPI {
    private final String accessToken;
    private ResourceConverter converter;

    /**
     * Create a new instance of the Patreon API. You only need <b>one</b> of these objects unless you are using the API with multiple tokens
     *
     * @param accessToken The "Creator's Access Token" found on <a href="https://www.patreon.com/platform/documentation/clients">the patreon client page</a> <b>OR</b> OAuth access token
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
     * @return JSONAPIDocument<User> containing all data pertaining to the current user
     * @throws IOException Thrown when the GET request failed
     */
    public JSONAPIDocument<User> fetchUser() throws IOException {
        return converter.readDocument(
            getDataStream("current_user"),
            User.class
        );
    }

    /**
     * Get a list of campaigns the current creator is running - also contains other related data like Goals
     * Note: The first campaign data object is located at index 0 in the data list
     *
     * @return JSONAPIDocument<List<Campaign>> containing the above-mentioned data
     * @throws IOException Thrown when the GET request failed
     */
    public JSONAPIDocument<List<Campaign>> fetchCampaigns() throws IOException {
        return converter.readDocumentCollection(
            getDataStream("current_user/campaigns?include=rewards,creator,goals"),
            Campaign.class
        );
    }

    /**
     * Retrieve pledges for the specified campaign
     *
     * @param campaignId id for campaign to retrieve
     * @param pageSize   how many pledges to return
     * @param pageCursor ignore, put null.
     * @return JSONAPIDocument<List<Pledge>> containing pledges & associated data
     * @throws IOException Thrown when the GET request failed
     */
    public JSONAPIDocument<List<Pledge>> fetchPageOfPledges(String campaignId, int pageSize, String pageCursor) throws IOException {
        String url = String.format("campaigns/%s/pledges?page%%5Bcount%%5D=%s", campaignID, pageSize);
        if (pageCursor != null) {
            try {
                String escapedCursor = URLEncoder.encode(cursor, "UTF-8");
                url.concat(String.format("&page%%5Bcursor%%5D=%s", escapedCursor));
            } catch (java.io.UnsupportedEncodingException e) {
                System.err.println("UnsupportedEncodingException: " + e.getMessage());
            }
        }
        return converter.readDocumentCollection(
            getDataStream(url),
            Pledge.class
        );
    }


    private InputStream getDataStream(String suffix) {
        try {
            String prefix = "https://api.patreon.com/oauth2/api/";
            URL url = new URL(prefix.concat(suffix));
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestProperty("Authorization", "Bearer ".concat(this.accessToken));
            return connection.getInputStream();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
