package com.patreon.resources.campaign.misc.simple;

import com.github.jasminb.jsonapi.annotations.Meta;
import com.github.jasminb.jsonapi.annotations.Type;
import com.patreon.resources.shared.BaseIdModel;
import com.patreon.resources.shared.LinkedModel;

public class SimpleCreator extends LinkedModel {
    @Meta
    private SimpleCreatorData data;

    public SimpleCreatorData getData() {
        return data;
    }

    @Type("user")
    public static class SimpleCreatorData extends BaseIdModel {
        private String type;

        public String getType() {
            return type;
        }
    }
}
