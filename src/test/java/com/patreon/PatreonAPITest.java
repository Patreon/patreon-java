//package com.patreon;
//
//import com.github.jasminb.jsonapi.JSONAPIDocument;
//import com.patreon.PatreonAPI;
//import com.patreon.resources.Campaign;
//import com.patreon.resources.Pledge;
//import com.patreon.resources.User;
//import junit.framework.Assert;
//import junit.framework.TestCase;
//
//import java.util.List;
//
//public class PatreonAPITest extends TestCase {
//    private PatreonAPI api = new PatreonAPI("a token");
//
//    public void testFetchCampaigns() throws Exception {
//        JSONAPIDocument<List<Campaign>> campaignResponse = api.fetchCampaigns();
//        Assert.assertEquals(1, campaignResponse.get().size());
//        Campaign campaign = campaignResponse.get().get(0);
//        Assert.assertEquals("70261", campaign.getId());
//        Assert.assertEquals("/bePatron?c=70261", campaign.getPledgeUrl());
//        Assert.assertEquals(false, campaign.isChargedImmediately());
//        Assert.assertEquals("212633030584565760", campaign.getDiscordServerId());
//        Assert.assertEquals("32187", campaign.getCreator().getId());
//        Assert.assertEquals(3, campaign.getGoals().size());
//    }
//
//    public void testGetPledgesToMe() throws Exception {
//        JSONAPIDocument<List<Pledge>> pledgeResponse = api.fetchPageOfPledges("70261", 10, null);
//        Assert.assertEquals(14, pledgeResponse.getMeta().get("count"));
//        List<Pledge> pledges = pledgeResponse.get();
//        Assert.assertEquals(10, pledges.size());
//
//        for (int i = 0; i < pledges.size(); i++) {
//            Pledge pledge = pledges.get(i);
//            Assert.assertTrue(pledge.getAmountCents() > 0);
//            User patron = pledge.getPatron();
//            Assert.assertNotNull(patron.getEmail());
//        }
//    }
//
//    public void testFetchAllPledges() throws Exception {
//        List<Pledge> pledges = api.fetchAllPledges("70261");
//        Assert.assertEquals(14, pledges.size());
//
//        for (int i = 0; i < pledges.size(); i++) {
//            Pledge pledge = pledges.get(i);
//            Assert.assertTrue(pledge.getAmountCents() > 0);
//            User patron = pledge.getPatron();
//            Assert.assertNotNull(patron.getEmail());
//        }
//    }
//
//    public void testFetchUser() throws Exception {
//        JSONAPIDocument<User> user = api.fetchUser();
//        Assert.assertEquals("https://www.patreon.com/api/user/32187", user.getLinks().getSelf().toString());
//        Assert.assertEquals(5, user.get().getPledges().size());
//        Assert.assertEquals("corgi", user.get().getVanity());
//    }
//}