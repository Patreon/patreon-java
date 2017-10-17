package com.patreon.models.campaign.misc.simple;

import com.github.jasminb.jsonapi.annotations.Meta;
import com.github.jasminb.jsonapi.annotations.Type;
import com.patreon.models.common.BaseIdModel;
import com.patreon.models.common.LinkedModel;

import java.util.List;

public class SimpleGoals extends LinkedModel {
    @Meta
    private List<SimpleGoalData> data;

    public List<SimpleGoalData> getData() {
        return data;
    }

    @Type("goal")
    public static class SimpleGoalData extends BaseIdModel {
        private String type;

        public String getType() {
            return type;
        }
    }
}
