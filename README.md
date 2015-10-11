# patreon-java
Interact with the Patreon API via OAuth.

Get the artifact from [Maven](http://mvnrepository.com/artifact/com.patreon/patreon)

Step 1. Get your client_id and client_secret
---
Visit the [OAuth Documentation Page](https://www.patreon.com/oauth2/documentation)
while logged in as a Patreon creator to register your client.

This will provide you with a `client_id` and a `client_secret`.

Step 2. Use this library
---
```javatSuite;
    import com.patreon.OAuth;
    import com.patreon.API;
    import org.json.JSONObject;
    import org.json.JSONArray;

    ...

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

    // use the user, pledge, and campaign objects as you desire
```