package com.patreon;

import com.github.jasminb.jsonapi.JSONAPIDocument;
import com.patreon.models.Campaign;
import com.patreon.models.Pledge;
import com.patreon.models.RequestUtil;
import com.patreon.models.User;
import junit.framework.TestCase;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PatreonAPITest extends TestCase {
  private static final String MOCK_TOKEN = "some token";
  private PatreonAPI api;
  private RequestUtil requestUtil;

  @Override
  public void setUp() {
    requestUtil = mock(RequestUtil.class);
    api = Mockito.spy(new PatreonAPI(MOCK_TOKEN, requestUtil));
  }

  public void testFetchCampaigns() throws Exception {
    when(requestUtil.request(anyString(), eq(MOCK_TOKEN)))
      .thenReturn(PatreonAPITest.class.getResourceAsStream("/api/campaigns.json"));

    JSONAPIDocument<List<Campaign>> campaignResponse = api.fetchCampaigns();
    assertEquals(1, campaignResponse.get().size());
    Campaign campaign = campaignResponse.get().get(0);
    assertEquals("70261", campaign.getId());
    assertEquals("/bePatron?c=70261", campaign.getPledgeUrl());
    assertEquals(false, campaign.isChargedImmediately());
    assertEquals("212633030584565760", campaign.getDiscordServerId());
    assertEquals("32187", campaign.getCreator().getId());
    assertEquals(3, campaign.getGoals().size());
  }

  public void testGetPledgesToMe() throws Exception {
    when(requestUtil.request(anyString(), eq(MOCK_TOKEN)))
      .thenReturn(PatreonAPITest.class.getResourceAsStream("/api/campaign_pledges.json"));

    JSONAPIDocument<List<Pledge>> pledgeResponse = api.fetchPageOfPledges("70261", 10, null);
    assertEquals(12, pledgeResponse.getMeta().get("count"));
    List<Pledge> pledges = pledgeResponse.get();
    assertEquals(10, pledges.size());

    for (Pledge pledge : pledges) {
      assertTrue(pledge.getAmountCents() > 0);
      User patron = pledge.getPatron();
      assertNotNull(patron.getEmail());
    }
  }

  public void testFetchAllPledges() throws Exception {
    when(requestUtil.request(anyString(), eq(MOCK_TOKEN))).thenReturn(
      PatreonAPITest.class.getResourceAsStream("/api/campaign_pledges_page_1.json"),
      PatreonAPITest.class.getResourceAsStream("/api/campaign_pledges_page_2.json")
    );

    List<Pledge> pledges = api.fetchAllPledges("70261");
    assertEquals(11, pledges.size());

    for (Pledge pledge : pledges) {
      assertTrue(pledge.getAmountCents() > 0);
      User patron = pledge.getPatron();
      assertNotNull(patron.getEmail());
    }
  }

  public void testFetchUser() throws Exception {
    when(requestUtil.request(anyString(), eq(MOCK_TOKEN))).thenReturn(
      PatreonAPITest.class.getResourceAsStream("/api/current_user.json")
    );

    JSONAPIDocument<User> user = api.fetchUser();

    verify(requestUtil).request(eq("current_user?include=pledges"), eq(MOCK_TOKEN));
    assertEquals("https://www.patreon.com/api/user/32187", user.getLinks().getSelf().toString());
    assertEquals(5, user.get().getPledges().size());
    assertEquals("corgi", user.get().getVanity());
    assertNull(user.get().getLikeCount());
    assertNull(user.get().getCommentCount());
  }

  public void testFetchUserOptionalFields() throws Exception {
    when(requestUtil.request(anyString(), eq(MOCK_TOKEN))).thenReturn(
      PatreonAPITest.class.getResourceAsStream("/api/current_user_optional_fields.json")
    );

    JSONAPIDocument<User> user = api.fetchUser(Collections.singletonList(User.UserField.LikeCount));


    ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
    verify(requestUtil).request(captor.capture(), eq(MOCK_TOKEN));

    String arg = captor.getValue();
    assertTrue(arg.startsWith("current_user?"));

    //Extract and decode the query params from the URL
    List<NameValuePair> parsed = URLEncodedUtils.parse(arg.substring(arg.indexOf('?') + 1), Charset.forName("UTF-8"));
    assertEquals(2, parsed.size());
    NameValuePair first = parsed.get(0);
    assertEquals("include", first.getName());
    assertEquals("pledges", first.getValue());

    NameValuePair second = parsed.get(1);
    assertEquals("fields[user]", second.getName());

    //sort fields from call, compare to sorted list of fields.
    String[] fieldNames = second.getValue().split(",");
    Arrays.sort(fieldNames);


    assertEquals(Arrays.asList(
      "about",
      "created",
      "discord_id",
      "email",
      "facebook",
      "facebook_id",
      "full_name",
      "image_url",
      "is_email_verified",
      "like_count",
      "social_connections",
      "thumb_url",
      "twitch",
      "twitter",
      "url",
      "vanity",
      "youtube"), Arrays.asList(fieldNames));

    assertEquals("https://www.patreon.com/api/user/32187", user.getLinks().getSelf().toString());
    assertEquals(5, user.get().getPledges().size());
    assertEquals("corgi", user.get().getVanity());
    assertEquals("https://facebook.com/corgi", user.get().getSocialConnections().getFacebook().getUrl());
    assertEquals(Integer.valueOf(5), user.get().getLikeCount());
    assertNull(user.get().getCommentCount());

  }

  public void testFetchUserUnknownProperties() throws Exception {

    when(requestUtil.request(anyString(), eq(MOCK_TOKEN))).thenReturn(
      PatreonAPITest.class.getResourceAsStream("/api/current_user_unknown_properties.json")
    );

    JSONAPIDocument<User> user = api.fetchUser();
    verify(requestUtil).request(eq("current_user?include=pledges"), eq(MOCK_TOKEN));
    assertEquals("https://www.patreon.com/api/user/32187", user.getLinks().getSelf().toString());
  }
}
