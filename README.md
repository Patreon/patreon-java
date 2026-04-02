# patreon-java

> [!CAUTION]
> This package is deprecated. It uses Patreon API v1, which is deprecated and no longer maintained. This SDK will not receive further updates.
>
> We recommend migrating to the Patreon API v2 by integrating directly using your own HTTP client. See the [API documentation](https://docs.patreon.com) and [v1 to v2 migration overview](https://docs.patreon.com/#migrating-from-api-v1-to-api-v2) for details.

---

Interact with the Patreon API via OAuth.

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.patreon/patreon/badge.svg)]

Get the artifact from [Maven](http://search.maven.org/#search|ga|1|g%3A%22com.patreon%22%20AND%20a%3A%22patreon%22)
```xml
<dependency>
    <groupId>com.patreon</groupId>
    <artifactId>patreon</artifactId>
    <version>0.4.2</version>
</dependency>
```

Step 1. Get your client_id and client_secret
---
Visit the [Patreon Platform Page](https://www.patreon.com/platform)
while logged in as a Patreon creator to register your client.

This will provide you with a `client_id` and a `client_secret`.

Step 2. Use this library
---

## For the Log In with Patreon flow

```java
import com.github.jasminb.jsonapi.JSONAPIDocument;
import com.patreon.PatreonAPI;
import com.patreon.PatreonOAuth;
import com.patreon.PatreonOAuth;
import com.patreon.resources.v1.User;
import com.patreon.resources.v1.Pledge;

    ...

String clientId = null; // Get this when you set up your client
String clientSecret = null; // Get this when you set up your client
String redirectUri = null; // Provide this to set up your client

String code = null; // Get this from the query parameter `code`

PatreonOAuth oauthClient = new PatreonOAuth(clientId, clientSecret, redirectUri);
PatreonOAuth.TokensResponse tokens = oauthClient.getTokens(code);
//Store the refresh TokensResponse in your data store
String accessToken = tokens.getAccessToken();

PatreonAPI apiClient = new PatreonAPI(accessToken);
JSONAPIDocument<User> userResponse = apiClient.fetchUser();
User user = userResponse.get();
Log.

i(user.getFullName());
List<Pledge> pledges = user.getPledges()
if(pledges !=null&&pledges.

size() >0){
Pledge pledge = pledges.get(0);
    Log.

i(pledge.getAmountCents());
        }
// You should save the user's PatreonOAuth.TokensResponse in your database
// (for refreshing their Patreon data whenever you like),
// along with any relevant user info or pledge info you want to store.
```
