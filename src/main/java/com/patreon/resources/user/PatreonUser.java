package com.patreon.resources.user;

import com.github.jasminb.jsonapi.annotations.Meta;
import com.github.jasminb.jsonapi.annotations.Type;
import com.patreon.resources.pledge.Pledge;
import com.patreon.resources.shared.RelationshipsModel;

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
