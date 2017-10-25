package com.patreon.resources.campaign.misc.simple;

import com.github.jasminb.jsonapi.annotations.Meta;
import com.github.jasminb.jsonapi.annotations.Type;
import com.patreon.resources.shared.BaseResource;

public class SimpleCreator extends BaseResource {
    @Meta
    private SimpleCreatorData data;

    public SimpleCreatorData getData() {
        return data;
    }

    @Type("user")
    public static class SimpleCreatorData extends BaseResource {
        private String type;

        public String getType() {
            return type;
        }
    }
}
