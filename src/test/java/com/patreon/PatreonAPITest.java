package com.patreon;

import com.patreon.models.campaign.PatreonCampaign;
import com.patreon.models.campaign.PatreonCampaignResponse;
import com.patreon.models.campaign.pledge.PledgeResponse;
import com.patreon.models.user.PatreonUserResponse;
import junit.framework.Assert;
import junit.framework.TestCase;

public class PatreonAPITest extends TestCase {
    PatreonAPI api = new PatreonAPI("a token");

    public void testGetUser() throws Exception {
    }

    public void testGetCampaigns() throws Exception {
        PatreonCampaignResponse campaignResponse = api.getCampaigns();
        Assert.assertEquals(13, campaignResponse.getPledges().size());
        Assert.assertEquals(1, campaignResponse.getData().size());
        PatreonCampaign campaign = campaignResponse.getData().get(0);
        Assert.assertEquals("5140688", campaign.getSimpleCreator().getData().getId());
        Assert.assertEquals("/bePatron?c=758508", campaign.getAttributes().getPledgeUrl());
        Assert.assertEquals(false, campaign.getAttributes().isChargedImmediately());
        Assert.assertEquals("260841592070340609", campaign.getAttributes().getDiscordServerId());
        Assert.assertEquals("758508", campaign.getId());
        // Assert.assertEquals(2, campaignResponse.getGoals().size()); Asked in issue
    }

    public void testGetPledgesToMe() throws Exception {
        PledgeResponse pledgeResponse = api.getPledgesToMe("758508", 10, null);
        Assert.assertEquals(13, pledgeResponse.getMeta().getPatronCount());
        Assert.assertEquals(11, pledgeResponse.getUsers().size());
        pledgeResponse.getUsers().forEach(patreonUser -> p(patreonUser.getAttributes().getEmail()));
        Assert.assertEquals(11, pledgeResponse.getPatrons().size());
        // Assert.assertEquals(1, pledgeResponse.getCampaigns().size()); Asked
    }

    public void testGetMyUser() throws Exception {
        PatreonUserResponse user = api.getMyUser();
        Assert.assertEquals("https://www.patreon.com/api/user/5140688", user.getSelfUrl());
        Assert.assertEquals(0, user.getData().getPledges().length);
        Assert.assertEquals("ardent", user.getData().getAttributes().getVanity());
    }

    public static void p(Object o) {
        System.out.println(o);
    }
}