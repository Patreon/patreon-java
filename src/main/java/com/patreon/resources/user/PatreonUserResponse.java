package com.patreon.resources.user;

import com.patreon.resources.shared.BaseResource;

public class PatreonUserResponse extends BaseResource {
    private PatreonUser data;

    public PatreonUser getData() {
        return data;
    }

    public String getSelfUrl() {
        return getLink("self");
    }
}
