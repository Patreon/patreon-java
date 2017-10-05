package com.patreon.models.campaign.misc.simple;

import com.github.jasminb.jsonapi.annotations.Meta;
import com.github.jasminb.jsonapi.annotations.Type;
import com.patreon.models.common.BaseIdModel;
import com.patreon.models.common.LinkedModel;

import java.util.List;

public class SimplePledges extends LinkedModel {
    @Meta
    private List<SimplePledgeData> data;

    public List<SimplePledgeData> getData() {
        return data;
    }

    @Type("pledge")
    public static class SimplePledgeData extends BaseIdModel {
        private String type;

        public String getType() {
            return type;
        }
    }
}
