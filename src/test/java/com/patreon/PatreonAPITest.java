package com.patreon;

import com.github.jasminb.jsonapi.JSONAPIDocument;
import com.patreon.resources.Campaign;
import com.patreon.resources.Pledge;
import com.patreon.resources.User;
import junit.framework.TestCase;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.List;

import static org.mockito.Matchers.anyString;

@RunWith(PowerMockRunner.class)
@PrepareForTest(PatreonAPI.class)
public class PatreonAPITest extends TestCase {
    private PatreonAPI api;

    @Override
    public void setUp() {
        api = PowerMockito.spy(new PatreonAPI("some token"));
    }

    public void testFetchCampaigns() throws Exception {
        PowerMockito.doReturn(PatreonAPITest.class.getResourceAsStream("/api/campaigns.json"))
            .when(api, "getDataStream", anyString());

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
        PowerMockito.doReturn(PatreonAPITest.class.getResourceAsStream("/api/campaign_pledges.json"))
            .when(api, "getDataStream", anyString());

        JSONAPIDocument<List<Pledge>> pledgeResponse = api.fetchPageOfPledges("70261", 10, null);
        assertEquals(12, pledgeResponse.getMeta().get("count"));
        List<Pledge> pledges = pledgeResponse.get();
        assertEquals(10, pledges.size());

        for (int i = 0; i < pledges.size(); i++) {
            Pledge pledge = pledges.get(i);
            assertTrue(pledge.getAmountCents() > 0);
            User patron = pledge.getPatron();
            assertNotNull(patron.getEmail());
        }
    }

    public void testFetchAllPledges() throws Exception {
        PowerMockito.doReturn(
            PatreonAPITest.class.getResourceAsStream("/api/campaign_pledges_page_1.json"),
            PatreonAPITest.class.getResourceAsStream("/api/campaign_pledges_page_2.json")
        ).when(api, "getDataStream", anyString());

        List<Pledge> pledges = api.fetchAllPledges("70261");
        assertEquals(11, pledges.size());

        for (int i = 0; i < pledges.size(); i++) {
            Pledge pledge = pledges.get(i);
            assertTrue(pledge.getAmountCents() > 0);
            User patron = pledge.getPatron();
            assertNotNull(patron.getEmail());
        }
    }

    public void testFetchUser() throws Exception {
        PowerMockito.doReturn(
            PatreonAPITest.class.getResourceAsStream("/api/current_user.json")
        ).when(api, "getDataStream", anyString());

        JSONAPIDocument<User> user = api.fetchUser();
        assertEquals("https://www.patreon.com/api/user/32187", user.getLinks().getSelf().toString());
        assertEquals(5, user.get().getPledges().size());
        assertEquals("corgi", user.get().getVanity());
    }
}