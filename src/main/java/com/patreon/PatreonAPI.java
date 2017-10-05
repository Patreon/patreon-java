package com.patreon;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.patreon.models.campaign.PatreonCampaignResponse;
import com.patreon.models.campaign.pledge.PledgeResponse;
import com.patreon.models.user.PatreonUser;
import com.patreon.models.user.PatreonUserResponse;
import org.jsoup.Jsoup;

import java.io.IOException;

public class PatreonAPI {
    private final String accessToken;
    public static final Gson gson = new GsonBuilder().serializeNulls().enableComplexMapKeySerialization().create();

    /**
     * Create a new instance of the Patreon API. You only need <b>one</b> of these, unless you are using the API with multiple tokens
     * @param accessToken The "Creator's Access Token" found on <a href="https://www.patreon.com/platform/documentation/clients">the patreon client page</a>
     */
    public PatreonAPI(String accessToken) {
        this.accessToken = accessToken;
    }

    /**
     * Get the user object of the creator
     * @return Response containing all data pertaining to the current user
     * @throws IOException Thrown when the GET request failed
     */
    public PatreonUserResponse getUser() throws IOException {
        return toObject(getJson("current_user"), PatreonUserResponse.class);
    }

    /**
     * Get a list of campaigns the current creator is running - also contains other related data like Goals
     * Note: The first campaign data object is located at index 0 in the data list
     * @return Campaign Response containing the above-mentioned data
     * @throws IOException Thrown when the GET request failed
     */
    public PatreonCampaignResponse getCampaigns() throws IOException {
        return toObject(getJson("current_user/campaigns?include=rewards,creator,goals,pledges"), PatreonCampaignResponse.class);
    }

    /**
     * Retrieve pledges for the specified campaign
     *
     * @param campaignId id for campaign to retrieve
     * @param pageSize   how many pledges to return
     * @param pageCursor ignore, put null.
     * @return PledgeResponse containing pledges & associated data
     * @throws IOException Thrown when the GET request failed
     */
    public PledgeResponse getPledges(String campaignId, int pageSize, String pageCursor) throws IOException {
        String url = "campaigns/" + campaignId + "/pledges?page%5Bcount%5D=" + pageSize;
        if (pageCursor != null) url += "&page%5Bcursor%5D=" + pageCursor;
        return toObject(getJson(url), PledgeResponse.class);
    }

    /**
     * Uses Patreon's public endpoint to get data about any patreon user. <b>Some data will be null, especially sensitive data</b>
     *
     * @param id The patreon user id
     * @return A user response containing minimal public information about a user
     * @throws IOException Thrown when the GET request failed
     */
    public PatreonUserResponse getUser(String id) throws IOException {
        return toObject(Jsoup.connect("https://www.patreon.com/api/user/" + id)
                .ignoreContentType(true).header("Authorization", "Bearer " + accessToken).get().body().text(), PatreonUserResponse.class);
    }

    /**
     * @param suffix The Patreon endpoint AFTER the base URL of https://api.patreon.com/oauth2/api/
     * @return The text content of the request, which can then be deserialized
     * @throws IOException Thrown when the GET request failed
     */
    private String getJson(String suffix) throws IOException {
        return Jsoup.connect("https://api.patreon.com/oauth2/api/" + suffix)
                .ignoreContentType(true).header("Authorization", "Bearer " + accessToken).get().body().text();
    }

    public static <E> E toObject(String str, Class<E> clazz) {
        return gson.fromJson(str, clazz);
    }
}
