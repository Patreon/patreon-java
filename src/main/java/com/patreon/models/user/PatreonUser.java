package com.patreon.models.user;

import com.github.jasminb.jsonapi.annotations.Meta;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import com.patreon.models.campaign.pledge.Pledge;
import com.patreon.models.common.RelationshipsModel;

import java.util.List;

/**
 * Object representing a Patreon User - attributes are stored in the attributes field, while all other related data are located in the relationships object
 */
@Type("user")
public class PatreonUser extends RelationshipsModel {
    private String type;
    @Meta
    private PatreonUserAttributes attributes;

    @Relationship("pledges")
    private List<Pledge> pledges;

    public List<Pledge> getPledges() {
        return pledges;
    }

    public PatreonUserAttributes getAttributes() {
        return attributes;
    }

    public String getType() {
        return type;
    }
}
