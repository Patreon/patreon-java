package com.patreon;

import junit.framework.TestCase;

public class PatreonOAuthTest extends TestCase {
    PatreonOAuth oauth = new PatreonOAuth(
        "a client id",
        "your secret",
        "your redirect URI"
    );
    public void testGetToken() throws Exception {
        oauth.getAuthorizationURL("https://patreon-example-oauth.com");
        PatreonOAuth.TokensResponse token = oauth.getTokens("a code");
        token.getAccessToken();
        token.getRefreshToken();
        PatreonOAuth.TokensResponse refreshed = oauth.refreshTokens(token.getRefreshToken());
        refreshed.getAccessToken();
        refreshed.getRefreshToken();
        PatreonAPI api = new PatreonAPI(refreshed.getAccessToken());
        api.fetchUser().get().getEmail();
    }


    public void testRefreshToken() throws Exception {
    }

}