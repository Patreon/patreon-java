package com.patreon.resources.pledge;

import com.github.jasminb.jsonapi.annotations.Meta;
import com.github.jasminb.jsonapi.annotations.Type;
import com.patreon.resources.campaign.misc.simple.SimpleCreator;
import com.patreon.resources.campaign.misc.simple.SimpleReward;
import com.patreon.resources.shared.RelationshipsModel;

@Type("pledge")
public class Pledge extends RelationshipsModel {
    private String type;
    @Meta
    private PledgeData attributes;

    /**
     * Convenience method to get the creator of the campaign where this user pledged
     * @return Simple creator object, use to lookup or store ID
     */
    public SimpleCreator getSimpleCreator() {
        return getRelationship("creator", SimpleCreator.class);
    }

    /**
     * Convenience method to get the patron of the campaign where this user pledged
     * @return Simple patron object, convenient to quickly get their id
     */
    public SimpleCreator getSimplePatron() {
        return getRelationship("patron", SimpleCreator.class);
    }

    /**
     * Convenience method to get the reward this patron has
     * @return Simple reward object - use with PatreonCampaign object to get pledge amount and rewards
     */
    public SimpleReward getSimpleReward() {
        return getRelationship("reward", SimpleReward.class);
    }

    public String getType() {
        return type;
    }

    public PledgeData getAttributes() {
        return attributes;
    }
}
