package com.patreon;

import com.patreon.models.campaign.PatreonCampaignData;
import com.patreon.models.campaign.PatreonCampaignResponse;
import com.patreon.models.campaign.pledge.PledgeResponse;
import com.patreon.models.user.PatreonUserResponse;
import junit.framework.TestCase;

import static com.patreon.PatreonAPI.gson;

public class PatreonAPITest extends TestCase {
    private PatreonAPI api = new PatreonAPI("");

    public void testGetUser() throws Exception {
        PatreonUserResponse response = api.getUser();
        PatreonCampaignData data = api.getCampaigns().getData().get(0).getAttributes();
        System.out.println(api.getUser("439234").getData().getAttributes().getEmail());
        System.out.println(api.getUser("439234").getData().getAttributes().getFullName());
        System.out.println(api.getUser().getData().getAttributes().hasPassword());
    }

    public void testGetCampaigns() throws Exception {
        PatreonCampaignResponse response = api.getCampaigns();
        //System.out.println(response.getCards().get(0).getAttributes().getExpirationDate());
    }

    public void testGetPledges() throws Exception {
        PledgeResponse pledges = api.getPledges("758508", 10, null);
        System.out.println(gson.toJson(pledges));
        System.out.println(pledges.getPledges().get(0).getSimplePatron().getLink("related"));
        System.out.println(pledges.getPatrons().size());
        System.out.println(pledges.getRewards().get(1).getSimpleCampaign().getLink("related"));
    }
}