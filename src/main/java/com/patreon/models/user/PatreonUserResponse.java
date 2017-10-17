package com.patreon.models.user;

import com.patreon.models.common.LinkedModel;

public class PatreonUserResponse extends LinkedModel {
    private PatreonUser data;

    public PatreonUser getData() {
        return data;
    }

    public String getSelfUrl() {
        return getLink("self");
    }
}
