package com.patreon.resources.campaign.misc.simple;

import com.github.jasminb.jsonapi.annotations.Meta;
import com.github.jasminb.jsonapi.annotations.Type;
import com.patreon.resources.shared.BaseResource;

import java.util.List;

public class SimpleGoals extends BaseResource {
    @Meta
    private List<SimpleGoalData> data;

    public List<SimpleGoalData> getData() {
        return data;
    }

    @Type("goal")
    public static class SimpleGoalData extends BaseResource {
        private String type;

        public String getType() {
            return type;
        }
    }
}
