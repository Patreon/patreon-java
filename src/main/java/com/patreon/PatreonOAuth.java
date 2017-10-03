package com.patreon;

import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static com.patreon.PatreonAPI.toObject;

public class PatreonOAuth {
    private final String clientID;
    private final String clientSecret;

    public PatreonOAuth(String clientID, String clientSecret) {
        this.clientID = clientID;
        this.clientSecret = clientSecret;
    }

    public Token getToken(String code, String redirectURI) throws IOException {
        return toObject(Jsoup.connect("https://api.patreon.com/oauth2/token")
                .data("grant_type", "authorization_code")
                .data("code", code)
                .data("client_id", clientID)
                .data("client_secret", clientSecret)
                .data("redirect_uri", redirectURI).get().body().text(), Token.class);
    }

    public Token refreshToken(String refreshToken) throws IOException {
        return toObject(Jsoup.connect("https://api.patreon.com/oauth2/token")
                .data("grant_type", "authorization_code")
                .data("client_id", clientID)
                .data("client_secret", clientSecret)
                .data("refresh)token", refreshToken).get().body().text(), Token.class);
    }

    private static String createQuery(List<QueryParameter> parameters) {
        return parameters.stream().map(QueryParameter::getQueryString).collect(Collectors.joining("&"));
    }

    public static class QueryParameter {
        private String name;
        private String value;

        public QueryParameter(String name, String value) {
            this.name = name;
            this.value = value;
        }

        String getQueryString() {
            return name + "=" + value;
        }
    }

    public static class Token {
        private String access_token;
        private String refresh_token;
        private int expires_in;
        private String scope;
        private String token_type;

        public Token(String access_token, String refresh_token, int expires_in, String scope, String token_type) {
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
    }
}