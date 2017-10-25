package com.patreon.resources.campaign.misc.simple;

import com.github.jasminb.jsonapi.annotations.Meta;
import com.github.jasminb.jsonapi.annotations.Type;
import com.patreon.resources.shared.BaseIdModel;
import com.patreon.resources.shared.LinkedModel;

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
