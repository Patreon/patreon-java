package com.patreon.resources.campaign.misc.simple;

import com.github.jasminb.jsonapi.annotations.Meta;
import com.github.jasminb.jsonapi.annotations.Type;
import com.patreon.resources.shared.BaseResource;

public class SimplePatron extends BaseResource {
    @Meta
    private SimplePatronData data;

    public SimplePatronData getData() {
        return data;
    }

    @Type("user")
    public static class SimplePatronData extends BaseResource {
        private String type;

        public String getType() {
            return type;
        }
    }
}
