package com.patreon;

import junit.framework.TestCase;

public class PatreonAPITest extends TestCase {
    private PatreonAPI api = new PatreonAPI("");

    public void testGetUser() throws Exception {
        api.getCampaigns().getUsersWithSocialConnections().forEach(System.out::println);
        api.getCampaigns().getGoals().forEach(goal -> System.out.println(goal.getAttributes().getAmount_cents() / 100));
        api.getCampaigns().getRewards().forEach(reward -> System.out.println(reward.getAttributes().getDescription()));
        api.getCampaigns().getCards().forEach(card -> System.out.println(card.getAttributes().getPaymentToken()));
        api.getCampaigns().getGoals().forEach(goal -> System.out.println(goal.getAttributes().getAmount_cents()));
    }

    public void testFetchCampaign() throws Exception {
    }

    public void testToObject() throws Exception {
    }
}