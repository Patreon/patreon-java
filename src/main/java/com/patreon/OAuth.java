package com.patreon;

import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class OAuth {
    private final String clientID;
    private final String clientSecret;

    public OAuth(String clientID, String clientSecret) {
        this.clientID = clientID;
        this.clientSecret = clientSecret;
    }

    private static String createQueryStringForParameters(Map<String, String> parameters) {
        final StringBuilder parametersAsQueryString = new StringBuilder();
        if (parameters != null) {
            boolean firstParameter = true;
            for (final String parameterName : parameters.keySet()) {
                try {
                    parametersAsQueryString
                            .append(firstParameter ? "" : "&")
                            .append(parameterName)
                            .append("=")
                            .append(URLEncoder.encode(parameters.get(parameterName), "UTF-8"));
                    firstParameter = false;
                } catch (UnsupportedEncodingException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return parametersAsQueryString.toString();
    }

    public JSONObject getTokens(String code, String redirectURI) {
        final Map<String, String> params = new HashMap<String, String>();
        params.put("grant_type", "authorization_code");
        params.put("code", code);
        params.put("client_id", clientID);
        params.put("client_secret", clientSecret);
        params.put("redirect_uri", redirectURI);
        return this.updateToken(params);
    }

    public JSONObject refreshToken(String refreshToken) {
        final Map<String, String> params = new HashMap<String, String>();
        params.put("grant_type", "refresh_token");
        params.put("refresh_token", refreshToken);
        params.put("client_id", clientID);
        params.put("client_secret", clientSecret);
        return this.updateToken(params);
    }

    private JSONObject updateToken(Map<String, String> params) {
        try {
            final URL url = new URL("https://api.patreon.com/oauth2/token");
            final HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setDoOutput(true);
            final OutputStream os = connection.getOutputStream();
            final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(OAuth.createQueryStringForParameters(params));
            writer.flush();
            writer.close();
            os.close();

            final BufferedReader stream = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            final StringBuilder result = new StringBuilder();
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