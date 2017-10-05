package com.patreon.models.campaign.goal;

public class GoalData {
    private int amount_cents;
    private int completed_percentage;
    private String created_at;
    private String description;
    private String reached_at;
    private String title;

    public String getTitle() {
        return title;
    }

    public String getReachedAt() {
        return reached_at;
    }

    public String getDescription() {
        return description;
    }

    public String getCreatedAt() {
        return created_at;
    }

    public int getCompleted_percentage() {
        return completed_percentage;
    }

    public int getAmountCents() {
        return amount_cents;
    }
}
