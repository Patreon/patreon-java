package com.patreon.models.campaign.pledge;

import com.github.jasminb.jsonapi.annotations.Meta;
import com.patreon.models.campaign.PatreonCampaign;
import com.patreon.models.campaign.misc.full.Reward;
import com.patreon.models.common.LinkedIncludedCombinedModel;
import com.patreon.models.user.PatreonUser;

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
        campaigns.removeIf(patreonCampaign -> !patreonCampaign.getType().equals("campaign"));
        return campaigns;
    }

    public List<Reward> getRewards() {
        List<Reward> rewards = getAll(Reward.class);
        rewards.removeIf(reward -> !reward.getType().equals("reward"));
        return rewards;
    }

    public List<PatreonUser> getPatrons() {
        return getUsers();
    }

    public List<PatreonUser> getUsers() {
        List<PatreonUser> users = getAll(PatreonUser.class);
        users.removeIf(patreonUser -> !patreonUser.getType().equals("user"));
        return users;
    }


    public static class PledgeMeta {
        private int count;

        public int getPatronCount() {
            return count;
        }
    }
}
