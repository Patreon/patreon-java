package com.patreon.resources.campaign.misc.simple;

import com.github.jasminb.jsonapi.annotations.Meta;
import com.github.jasminb.jsonapi.annotations.Type;
import com.patreon.resources.shared.BaseResource;

public class SimpleCard extends BaseResource {
    @Meta
    private SimpleCardData data;

    public SimpleCardData getData() {
        return data;
    }

    @Type("card")
    public static class SimpleCardData extends BaseResource {
        private String type;

        public String getType() {
            return type;
        }
    }
}
