package com.patreon.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.annotations.Type;
import com.patreon.resources.shared.BaseResource;

@Type("goal")
public class Goal extends BaseResource {
    private int amount_cents;
    private int completed_percentage;
    private String created_at;
    private String description;
    private String reached_at;
    private String title;

    public Goal(
        @JsonProperty("amount_cents") int amount_cents,
        @JsonProperty("completed_percentage") int completed_percentage,
        @JsonProperty("created_at") String created_at,
        @JsonProperty("description") String description,
        @JsonProperty("reached_at") String reached_at,
        @JsonProperty("title") String title
    ) {
        this.amount_cents = amount_cents;
        this.completed_percentage = completed_percentage;
        this.created_at = created_at;
        this.description = description;
        this.reached_at = reached_at;
        this.title = title;
    }

    public int getAmountCents() {
        return amount_cents;
    }

    public int getCompletedPercentage() {
        return completed_percentage;
    }

    public String getCreatedAt() {
        return created_at;
    }

    public String getDescription() {
        return description;
    }

    public String getReachedAt() {
        return reached_at;
    }

    public String getTitle() {
        return title;
    }
}
