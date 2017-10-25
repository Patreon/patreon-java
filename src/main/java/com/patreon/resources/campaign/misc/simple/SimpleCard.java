package com.patreon.resources.campaign.misc.simple;

import com.github.jasminb.jsonapi.annotations.Meta;
import com.github.jasminb.jsonapi.annotations.Type;
import com.patreon.resources.shared.BaseIdModel;
import com.patreon.resources.shared.LinkedModel;

public class SimpleCard extends LinkedModel {
    @Meta
    private SimpleCardData data;

    public SimpleCardData getData() {
        return data;
    }

    @Type("card")
    public static class SimpleCardData extends BaseIdModel {
        private String type;

        public String getType() {
            return type;
        }
    }
}
