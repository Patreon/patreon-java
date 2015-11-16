package com.patreon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;

import org.json.JSONException;
import org.json.JSONObject;

public class API {
  private final String _accessToken;

  public API(String accessToken) {
    _accessToken = accessToken;
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

  private JSONObject getJSON(String suffix) {
    try {
      String prefix = "https://api.patreon.com/oauth2/api/";
      URL url = new URL(prefix.concat(suffix));
      HttpURLConnection connection = (HttpURLConnection)url.openConnection();
      connection.setRequestProperty("Authorization", "Bearer ".concat(_accessToken));
      BufferedReader stream = new BufferedReader(new InputStreamReader(connection.getInputStream()));
      StringBuilder result = new StringBuilder(); String line;
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
