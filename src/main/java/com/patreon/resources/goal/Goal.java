package com.patreon.resources.goal;

import com.github.jasminb.jsonapi.annotations.Type;
import com.patreon.resources.shared.BaseIdModel;

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
