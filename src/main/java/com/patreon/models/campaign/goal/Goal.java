package com.patreon.models.campaign.goal;

import com.github.jasminb.jsonapi.annotations.Type;
import com.patreon.models.common.BaseIdModel;

@Type("goal")
public class Goal extends BaseIdModel {
    private GoalData attributes;
    private String type;

    public String getType() {
        return type;
    }

    public GoalData getData() {
        return attributes;
    }
}
