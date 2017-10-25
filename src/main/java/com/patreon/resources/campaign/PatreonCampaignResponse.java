package com.patreon.resources.campaign;

import com.github.jasminb.jsonapi.annotations.Meta;
import com.patreon.resources.goal.Goal;
import com.patreon.resources.campaign.misc.full.Reward;
import com.patreon.resources.pledge.Pledge;
import com.patreon.resources.shared.IncludedModel;
import com.patreon.resources.user.PatreonUser;

import java.util.List;

public class PatreonCampaignResponse extends IncludedModel {
    @Meta
    private List<PatreonCampaign> data;

    /**
     * @return List of campaigns this creator runs - CAN be empty
     */
    public List<PatreonCampaign> getData() {
        return data;
    }

    public List<Goal> getGoals() {
        List<Goal> goals = getAll(Goal.class);
        goals.removeIf(goal -> !goal.getType().equals("goal"));
        return goals;
    }

    public List<Reward> getRewards() {
        List<Reward> rewards = getAll(Reward.class);
        rewards.removeIf(reward -> !reward.getType().equals("reward"));
        return rewards;
    }

    public List<Pledge> getPledges() {
        List<Pledge> pledges = getAll(Pledge.class);
        pledges.removeIf(pledge -> !pledge.getType().equals("pledge"));
        return pledges;
    }

    public List<PatreonUser> getPatrons() {
        List<PatreonUser> patrons = getAll(PatreonUser.class);
        patrons.removeIf(patreonUser -> !patreonUser.getType().equals("user"));
        return patrons;
    }
}
