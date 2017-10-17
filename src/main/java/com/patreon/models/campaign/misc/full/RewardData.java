package com.patreon.models.campaign.misc.full;

import java.util.List;

public class RewardData {
    private int amount;
    private int amount_cents;
    private String created_at;
    private String description;
    private float remaining;
    private boolean requires_shipping;
    private String url;
    private Integer user_limit;
    private String edited_at;
    private String published_at;
    private String image_url;
    private List<String> discord_roles;
    private String title;
    private String unpublished_at;

    public int getAmount() {
        return amount;
    }

    public int getAmountCents() {
        return amount_cents;
    }

    public String getCreatedAt() {
        return created_at;
    }

    public String getDescription() {
        return description;
    }

    public float getRemaining() {
        return remaining;
    }

    public String getUrl() {
        return url;
    }

    public Integer getUserLimit() {
        return user_limit;
    }

    public String getEditedAt() {
        return edited_at;
    }

    public String getUnpublishedAt() {
        return unpublished_at;
    }

    public String getPublishedAt() {
        return published_at;
    }

    public String getImageUrl() {
        return image_url;
    }

    public List<String> getDiscordRoles() {
        return discord_roles;
    }

    public boolean doesRequireShipping() {
        return requires_shipping;
    }

    public String getTitle() {
        return title;
    }
}
