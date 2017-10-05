package com.patreon.models.campaign.pledge;

import com.github.jasminb.jsonapi.annotations.Meta;
import com.github.jasminb.jsonapi.annotations.Type;
import com.patreon.models.campaign.misc.simple.SimpleCard;
import com.patreon.models.campaign.misc.simple.SimpleCreator;
import com.patreon.models.campaign.misc.simple.SimpleReward;
import com.patreon.models.campaign.misc.simple.SimpleRewards;
import com.patreon.models.common.RelationshipsModel;

@Type("pledge")
public class Pledge extends RelationshipsModel {
    private String type;
    @Meta
    private PledgeData attributes;

    public SimpleCreator getSimpleCreator() {
        return getRelationship("creator", SimpleCreator.class);
    }

    public SimpleCreator getSimplePatron() {
        return getRelationship("patron", SimpleCreator.class);
    }

    public SimpleReward getSimpleReward() {
        return getRelationship("reward", SimpleReward.class);
    }

    public String getType() {
        return type;
    }
}
