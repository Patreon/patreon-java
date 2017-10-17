# patreon-java
Interact with the Patreon API via OAuth.

Get the artifact from [Maven](http://search.maven.org/#search|ga|1|g%3A%22com.patreon%22%20AND%20a%3A%22patreon%22)
```
<dependency>
    <groupId>com.patreon</groupId>
    <artifactId>patreon</artifactId>
    <version>0.0.3</version>
</dependency>
```


Step 1. Get your client_id and client_secret
---
Visit the [OAuth Documentation Page](https://www.patreon.com/oauth2/documentation)
while logged in as a Patreon creator to register your client.

This will provide you with a `client_id` and a `client_secret`.

Step 2. Use this library
---
```java
import com.patreon.PatreonAPI;
import com.patreon.models.campaign.PatreonCampaignData;
import com.patreon.models.campaign.PatreonCampaignResponse;
import com.patreon.models.user.PatreonUserResponse;

    ...

PatreonAPI patreonAPI = new PatreonAPI("accessToken (via PatreonOAuth obj or creator token)");
    
// Getting your own user
PatreonUser user = patreonAPI.getUser();

// Example of getting attributes for your first campaign
PatreonCampaignData data = api.getCampaigns().getData().get(0).getAttributes();
    
// use the objects as you desire
```

For Patreon Developers Wishing to Release Updates
---
1. Get settings.xml
2. Get GPG keypair
3. `mvn clean deploy -s settings.xml -P release`
4. visit https://oss.sonatype.org/#stagingRepositories, find the latest repository, close it, then release it