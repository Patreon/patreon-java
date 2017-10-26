package com.patreon;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.client.utils.URIBuilder;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;

public class PatreonOAuth {
    private final String clientID;
    private final String clientSecret;
    private final String redirectUri;
    private static final Gson gson = new GsonBuilder().serializeNulls().enableComplexMapKeySerialization().create();


    public PatreonOAuth(String clientID, String clientSecret, String redirectUri) {
        this.clientID = clientID;
        this.clientSecret = clientSecret;
        this.redirectUri = redirectUri;
    }

    public String getAuthorizationURL() {
        URIBuilder builder = new URIBuilder("https://www.patreon.com/oauth2/authorize");
        builder.addParameter("response_type", "code");
        builder.addParameter("client_id", clientID);
        builder.addParameter("redirect_uri", redirectUri);
        return builder.toString();
    }

    public TokensResponse getTokens(String code) throws IOException {
        Connection requestInfo = Jsoup.connect("https://api.patreon.com/oauth2/token")
            .data("grant_type", "authorization_code")
            .data("code", code)
            .data("client_id", clientID)
            .data("client_secret", clientSecret)
            .data("redirect_uri", redirectUri)
            .ignoreContentType(true);
        String response = requestInfo.post().body().text();
        return toObject(response, TokensResponse.class);
    }

    public TokensResponse refreshTokens(String refreshToken) throws IOException {
        Connection requestInfo = Jsoup.connect("https://api.patreon.com/oauth2/token")
            .data("grant_type", "refresh_token")
            .data("client_id", clientID)
            .data("client_secret", clientSecret)
            .data("refresh_token", refreshToken)
            .ignoreContentType(true);
        String response = requestInfo.post().body().text();
        return toObject(response, TokensResponse.class);
    }

    public static class TokensResponse {
        private String access_token;
        private String refresh_token;
        private int expires_in;
        private String scope;
        private String token_type;

        public TokensResponse(String access_token, String refresh_token, int expires_in, String scope, String token_type) {
            this.access_token = access_token;
            this.refresh_token = refresh_token;
            this.expires_in = expires_in;
            this.scope = scope;
            this.token_type = token_type;
        }

        public String getAccessToken() {
            return access_token;
        }

        public String getRefreshToken() {
            return refresh_token;
        }

        public int getExpiresIn() {
            return expires_in;
        }

        public String getScope() {
            return scope;
        }

        public String getTokenType() {
            return token_type;
        }
    }

    private static <E> E toObject(String str, Class<E> clazz) {
        return gson.fromJson(str, clazz);
    }
}