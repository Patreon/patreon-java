package com.patreon.resources.campaign;


import com.github.jasminb.jsonapi.annotations.Meta;
import com.github.jasminb.jsonapi.annotations.Type;
import com.patreon.resources.campaign.misc.simple.SimpleCreator;
import com.patreon.resources.campaign.misc.simple.SimpleGoals;
import com.patreon.resources.campaign.misc.simple.SimplePledges;
import com.patreon.resources.campaign.misc.simple.SimpleRewards;
import com.patreon.resources.shared.RelationshipsModel;

@Type("campaign")
public class PatreonCampaign extends RelationshipsModel {
    private String type;
    @Meta
    private PatreonCampaignData attributes;

    public PatreonCampaignData getAttributes() {
        return attributes;
    }

    public SimpleGoals getSimpleGoals() {
        return getRelationship("goals", SimpleGoals.class);
    }

    public SimpleRewards getSimpleRewards() {
        return getRelationship("rewards", SimpleRewards.class);
    }

    public SimpleCreator getSimpleCreator() {
        return getRelationship("creator", SimpleCreator.class);
    }

    public SimplePledges getSimplePledges() {
        return getRelationship("pledges", SimplePledges.class);
    }

    public String getType() {
        return type;
    }
}
