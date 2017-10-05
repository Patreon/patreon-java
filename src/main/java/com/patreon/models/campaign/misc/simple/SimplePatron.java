package com.patreon.models.campaign.misc.simple;

import com.github.jasminb.jsonapi.annotations.Meta;
import com.github.jasminb.jsonapi.annotations.Type;
import com.patreon.models.common.BaseIdModel;
import com.patreon.models.common.LinkedModel;

public class SimplePatron extends LinkedModel {
    @Meta
    private SimplePatronData data;

    public SimplePatronData getData() {
        return data;
    }

    @Type("user")
    public static class SimplePatronData extends BaseIdModel {
        private String type;

        public String getType() {
            return type;
        }
    }
}
