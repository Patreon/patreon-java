package com.patreon.resources.campaign.misc.simple;

import com.github.jasminb.jsonapi.annotations.Meta;
import com.github.jasminb.jsonapi.annotations.Type;
import com.patreon.resources.shared.BaseResource;

import java.util.List;

public class SimpleRewards extends BaseResource {
    @Meta
    private List<SimpleRewardData> data;

    public List<SimpleRewardData> getData() {
        return data;
    }

    @Type("reward")
    public static class SimpleRewardData extends BaseResource {
        private String type;

        public String getType() {
            return type;
        }
    }
}
