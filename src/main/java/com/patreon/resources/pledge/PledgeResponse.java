package com.patreon.resources.pledge;

import com.github.jasminb.jsonapi.annotations.Meta;
import com.patreon.resources.campaign.PatreonCampaign;
import com.patreon.resources.reward.Reward;
import com.patreon.resources.shared.LinkedIncludedCombinedModel;
import com.patreon.resources.user.PatreonUser;

import java.util.List;

public class PledgeResponse extends LinkedIncludedCombinedModel {
    private List<Pledge> data;

    @Meta
    private PledgeMeta meta;

    public PledgeMeta getMeta() {
        return meta;
    }

    public List<Pledge> getPledges() {
        return data;
    }

    public List<PatreonCampaign> getCampaigns() {
        List<PatreonCampaign> campaigns = getAll(PatreonCampaign.class);
//        campaigns.removeIf(patreonCampaign -> !patreonCampaign.getType().equals("campaign"));
        return campaigns;
    }

    public List<Reward> getRewards() {
        List<Reward> rewards = getAll(Reward.class);
//        rewards.removeIf(reward -> !reward.getType().equals("reward"));
        return rewards;
    }

    /**
     * @return List of <b>paying</b> patrons <b>not</b> including the creator
     */
    public List<PatreonUser> getPatrons() {
        return getUsers();
    }

    /**
     * @return List of <b>paying</b> patrons <b>including the creator</b>
     */
    public List<PatreonUser> getUsers() {
        List<PatreonUser> users = getAll(PatreonUser.class);
//        users.removeIf(patreonUser -> !patreonUser.getType().equals("user"));
        return users;
    }


    public static class PledgeMeta {
        private int count;

        public int getPatronCount() {
            return count;
        }
    }
}
