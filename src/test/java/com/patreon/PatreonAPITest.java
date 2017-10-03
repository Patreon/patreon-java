package com.patreon;

import com.patreon.objects.PatreonCampaigns;
import com.patreon.objects.PatreonUser;
import junit.framework.TestCase;

public class PatreonAPITest extends TestCase {
    public void testGetUser() throws Exception {
        PatreonAPI patreonAPI = new PatreonAPI("accessToken");
        PatreonUser.PatreonUserData userData = patreonAPI.getUser().getData();
        PatreonCampaigns.CampaignData campaignData = patreonAPI.getCampaignInformation().getCampaigns().get(0);
    }

    public void testGetCampaign() throws Exception {
    }

    public void testGetPledges() throws Exception {
    }

}