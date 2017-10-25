package com.patreon.resources.user;

import com.patreon.resources.shared.LinkedModel;

public class PatreonUserResponse extends LinkedModel {
    private PatreonUser data;

    public PatreonUser getData() {
        return data;
    }

    public String getSelfUrl() {
        return getLink("self");
    }
}
