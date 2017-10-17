package com.patreon;

import junit.framework.TestCase;

import static com.patreon.PatreonAPITest.p;

public class PatreonOAuthTest extends TestCase {
    PatreonOAuth oauth = new PatreonOAuth("a client id",
            "your secret");
    public void testGetToken() throws Exception {
        p(oauth.getDefaultOauthUrl("https://ardentbot.com"));
        PatreonOAuth.Token token = oauth.getToken("a code", "a redirect");
        p(token.getAccessToken());
        p(token.getRefreshToken());
        PatreonOAuth.Token refreshed = oauth.refreshToken(token.getRefreshToken());
        p(refreshed.getAccessToken());
        p(refreshed.getRefreshToken());
        PatreonAPI api = new PatreonAPI(refreshed.getAccessToken());
        p(api.getMyUser().getData().getAttributes().getEmail());
    }


    public void testRefreshToken() throws Exception {
    }

}