package com.patreon;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class API {

    private final String accessToken;

    public API(String accessToken) {
        this.accessToken = accessToken;
    }

    public JSONObject fetchUser() {
        return this.getJSON("current_user");
    }

    public JSONObject fetchCampaignAndPatrons() {
        return this.getJSON("current_user/campaigns?include=rewards,creator,goals,pledges");
    }

    public JSONObject fetchCampaign() {
        return this.getJSON("current_user/campaigns?include=rewards,creator,goals");
    }

    public JSONObject fetchPageOfPledges(String campaignID, int pageSize, String cursor) {
        String url = String.format("campaigns/%s/pledges?page%5Bcount%5D=%s", campaignID, pageSize);
        if (cursor != null) {
            try {
                String escapedCursor = URLEncoder.encode(cursor, "UTF-8");
                url.concat(String.format("&page%5Bcursor%5D=%s", escapedCursor));
            } catch (UnsupportedEncodingException e) {
                System.err.println("UnsupportedEncodingException: " + e.getMessage());
            }
        }
        return this.getJSON(url);
    }

    private JSONObject getJSON(String suffix) {
        try {
            String prefix = "https://api.patreon.com/oauth2/api/";
            URL url = new URL(prefix.concat(suffix));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Authorization", "Bearer ".concat(accessToken));
            BufferedReader stream = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = stream.readLine()) != null) {
                result.append(line);
            }
            stream.close();
            return new JSONObject(result.toString());
        } catch (MalformedURLException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}