package com.patreon.models.user;

import com.github.jasminb.jsonapi.annotations.Meta;
import com.github.jasminb.jsonapi.annotations.Type;
import com.patreon.models.campaign.pledge.Pledge;
import com.patreon.models.common.RelationshipsModel;

import java.util.HashMap;

import static com.patreon.PatreonAPI.gson;
import static com.patreon.PatreonAPI.toObject;

/**
 * Object representing a Patreon User - attributes are stored in the attributes field, while all other related data are located in the relationships object
 */
@Type("user")
public class PatreonUser extends RelationshipsModel {
    private String type;
    @Meta
    private PatreonUserAttributes attributes;

    public Pledge[] getPledges() {
        return toObject(gson.toJson(getRelationship("pledges", HashMap.class).get("data")), Pledge[].class);
    }

    public PatreonUserAttributes getAttributes() {
        return attributes;
    }

    public String getType() {
        return type;
    }
}
