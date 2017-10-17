package com.patreon.models.campaign.misc.simple;

import com.github.jasminb.jsonapi.annotations.Meta;
import com.github.jasminb.jsonapi.annotations.Type;
import com.patreon.models.common.BaseIdModel;
import com.patreon.models.common.LinkedModel;

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
