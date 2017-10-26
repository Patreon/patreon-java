package com.patreon;

import junit.framework.TestCase;

import static com.patreon.PatreonAPITest.p;

public class PatreonOAuthTest extends TestCase {
    PatreonOAuth oauth = new PatreonOAuth(
        "a client id",
        "your secret",
        "your redirect URI"
    );
    public void testGetToken() throws Exception {
        p(oauth.getAuthorizationURL("https://ardentbot.com"));
        PatreonOAuth.TokensResponse token = oauth.getTokens("a code");
        p(token.getAccessToken());
        p(token.getRefreshToken());
        PatreonOAuth.TokensResponse refreshed = oauth.refreshTokens(token.getRefreshToken());
        p(refreshed.getAccessToken());
        p(refreshed.getRefreshToken());
        PatreonAPI api = new PatreonAPI(refreshed.getAccessToken());
        p(api.getMyUser().getData().getAttributes().getEmail());
    }


    public void testRefreshToken() throws Exception {
    }

}