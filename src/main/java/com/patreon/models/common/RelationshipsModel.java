package com.patreon.models.common;

import java.util.HashMap;

import static com.patreon.PatreonAPI.gson;
import static com.patreon.PatreonAPI.toObject;

public class RelationshipsModel extends BaseIdModel {
    private HashMap<String, Object> relationships;

    public HashMap<String, Object> getRelationships() {
        return relationships;
    }

    protected <E> E getRelationship(String str, Class<E> clazz) {
        return toObject(gson.toJson(relationships.get(str)), clazz);
    }

    @Override
    public String toString() {
        return gson.toJson(relationships);
    }
}
