package com.patreon;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.patreon.OAuth;
import com.patreon.API;
import org.json.JSONObject;
import org.json.JSONArray;

public class PatreonTest extends TestCase {
    public PatreonTest(String testName) {
        super( testName );
    }

    public static Test suite() {
        return new TestSuite(PatreonTest.class);
    }

    public void testPatreon() {
        OAuth oauthClient = new OAuth("", "");
        API apiClient = new API("");
        /*
        String clientID = null;        // Replace with your data
        String clientSecret = null;    // Replace with your data
        String creatorID = null;       // Replace with your data
        String redirectURI = null;     // Replace with your data
        String code = null;            // get from inbound HTTP request

        OAuth oauthClient = new OAuth(clientID, clientSecret);
        JSONObject tokens = oauthClient.getTokens(code, redirectURI);
        String accessToken = tokens.getString("access_token");
        API apiClient = new API(accessToken);
        JSONObject userResponse = apiClient.fetchUser();
        JSONObject user = userResponse.getJSONObject("data");
        JSONArray included = userResponse.getJSONArray("included");
        JSONObject pledge = null;
        JSONObject campaign = null;
        if (included != null) {
            for (int i = 0; i < included.length(); i++) {
                JSONObject object = included.getJSONObject(i);
                if (object.getString("type").equals("pledge") && object.getJSONObject("relationships").getJSONObject("creator").getJSONObject("data").getString("id").equals(creatorID)) {
                    pledge = object;
                    break;
                }
            }
            for (int i = 0; i < included.length(); i++) {
                JSONObject object = included.getJSONObject(i);
                if (object.getString("type").equals("campaign") && object.getJSONObject("relationships").getJSONObject("creator").getJSONObject("data").getString("id").equals(creatorID)) {
                    campaign = object;
                    break;
                }
            }
        }
        */
    }
}
