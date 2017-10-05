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

    public PatreonAPI(String accessToken) {
        this.accessToken = accessToken;
    }

    public PatreonUserResponse getUser() throws IOException {
        System.out.println(getJson("current_user"));
        return toObject(getJson("current_user"), PatreonUserResponse.class);
    }

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
     * @throws IOException if there's an exception connecting
     */
    public PledgeResponse getPledges(String campaignId, int pageSize, String pageCursor) throws IOException {
        String url = "campaigns/" + campaignId + "/pledges?page%5Bcount%5D=" + pageSize;
        if (pageCursor != null) url += "&page%5Bcursor%5D=" + pageCursor;
        return toObject(getJson(url), PledgeResponse.class);
    }

    public PatreonUserResponse getUser(String id) throws IOException {
        return toObject(Jsoup.connect("https://www.patreon.com/api/user/" + id)
                .ignoreContentType(true).header("Authorization", "Bearer " + accessToken).get().body().text(), PatreonUserResponse.class);
    }

    private String getJson(String suffix) throws IOException {
        return Jsoup.connect("https://api.patreon.com/oauth2/api/" + suffix)
                .ignoreContentType(true).header("Authorization", "Bearer " + accessToken).get().body().text();
    }

    public static <E> E toObject(String str, Class<E> clazz) {
        return gson.fromJson(str, clazz);
    }
}
