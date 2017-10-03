package com.patreon;

import com.google.gson.Gson;
import com.patreon.objects.PatreonCampaigns;
import com.patreon.objects.PatreonPledges;
import com.patreon.objects.PatreonUser;
import org.json.JSONObject;
import org.jsoup.Jsoup;

import java.io.IOException;

public class PatreonAPI {
    private final String accessToken;
    public static final Gson gson = new Gson();

    public PatreonAPI(String accessToken) {
        this.accessToken = accessToken;
    }

    public PatreonUser getUser() throws IOException {
        return toObject(getJSON("current_user"), PatreonUser.class);
    }

    public PatreonCampaigns getCampaign() throws IOException {
        return toObject(getJSON("current_user/campaigns?include=rewards,creator,goals,pledges"), PatreonCampaigns.class);
    }

    public JSONObject getJson(String endpoint) throws IOException {
        return new JSONObject(getJSON(endpoint));
    }

    /**
     * Retrieve pledges for the specified campaign
     *
     * @param campaignId id for campaign to retrieve
     * @param pageSize   how many pledges to return
     * @param pageCursor UNKNOWN. After much research, specific syntax is still unknown
     * @return list of Patreon Pledges
     * @throws IOException if there's an exception connecting
     */
    public PatreonPledges getPledges(String campaignId, int pageSize, String pageCursor) throws IOException {
        String url = "campaigns/" + campaignId + "/pledges?page%5Bcount%5D=" + pageSize;
        if (pageCursor != null) url += "&page%5Bcursor%5D=" + pageCursor;
        return toObject(getJSON(url), PatreonPledges.class);
    }

    /*public JSONObject fetchPageOfPledges(String campaignID, int pageSize, String cursor) {
        String url = String.format("campaigns/%s/pledges?page%5Bcount%5D=%s", campaignID, pageSize);
        if (cursor != null) {
            try {
                String escapedCursor = URLEncoder.encode(cursor, "UTF-8");
                url.concat(String.format("&page%5Bcursor%5D=%s", escapedCursor));
            } catch (java.io.UnsupportedEncodingException e) {
                System.err.println("UnsupportedEncodingException: " + e.getMessage());
            }
        }
        return this.getJSON(url);
    }*/

    private String getJSON(String suffix) throws IOException {
        return Jsoup.connect("https://api.patreon.com/oauth2/api/" + suffix)
                .ignoreContentType(true).header("Authorization", "Bearer " + accessToken).get().body().text();
    }

    public static <E> E toObject(String str, Class<E> clazz) {
        return gson.fromJson(str, clazz);
    }
}
