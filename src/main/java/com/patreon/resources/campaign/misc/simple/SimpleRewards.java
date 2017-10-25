package com.patreon.resources.campaign.misc.simple;

import com.github.jasminb.jsonapi.annotations.Meta;
import com.github.jasminb.jsonapi.annotations.Type;
import com.patreon.resources.shared.BaseIdModel;
import com.patreon.resources.shared.LinkedModel;

import java.util.List;

public class SimpleRewards extends LinkedModel {
    @Meta
    private List<SimpleRewardData> data;

    public List<SimpleRewardData> getData() {
        return data;
    }

    @Type("reward")
    public static class SimpleRewardData extends BaseIdModel {
        private String type;

        public String getType() {
            return type;
        }
    }
}
