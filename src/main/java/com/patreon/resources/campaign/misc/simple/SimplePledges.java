package com.patreon.resources.campaign.misc.simple;

import com.github.jasminb.jsonapi.annotations.Meta;
import com.github.jasminb.jsonapi.annotations.Type;
import com.patreon.resources.shared.BaseResource;

import java.util.List;

public class SimplePledges extends BaseResource {
    @Meta
    private List<SimplePledgeData> data;

    public List<SimplePledgeData> getData() {
        return data;
    }

    @Type("pledge")
    public static class SimplePledgeData extends BaseResource {
        private String type;

        public String getType() {
            return type;
        }
    }
}
