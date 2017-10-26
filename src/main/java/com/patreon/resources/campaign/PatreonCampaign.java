package com.patreon.resources.campaign;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.annotations.Type;
import com.patreon.resources.shared.RelationshipsModel;

@Type("campaign")
public class PatreonCampaign extends RelationshipsModel {
    private int pledgeSum;
    private String creationName;
    private String discordServerId;
    private String createdAt;
    private boolean isPlural;
    private String mainVideoUrl;
    private boolean isNsfw;
    private boolean isMonthly;
    private String publishedAt;
    private String earningsVisibility;
    private int outstandingPaymentAmountCents;
    private String imageSmallUrl;
    private String summary;
    private String thanksMsg;
    private String imageUrl;
    private int creationCount;
    private String oneLiner;
    private boolean isChargedImmediately;
    private int patronCount;
    private boolean displayPatronGoals;
    private String pledgeUrl;
    private String payPerName;
    private String thanksEmbed;
    private String mainVideoEmbed;
    private String thanksVideoUrl;
    private String about;

    public PatreonCampaign(
        @JsonProperty("pledge_sum") int pledgeSum,
        @JsonProperty("creation_name") String creationName,
        @JsonProperty("discor_server_id") String discordServerId,
        @JsonProperty("created_at") String createdAt,
        @JsonProperty("is_plural") boolean isPlural,
        @JsonProperty("main_video_url") String mainVideoUrl,
        @JsonProperty("is_nsfw") boolean isNsfw,
        @JsonProperty("is_monthly") boolean isMonthly,
        @JsonProperty("published_at") String publishedAt,
        @JsonProperty("earnings_visibility") String earningsVisibility,
        @JsonProperty("outstanding_payment_amount_cents") int outstandingPaymentAmountCents,
        @JsonProperty("image_small_url") String imageSmallUrl,
        @JsonProperty("summary") String summary,
        @JsonProperty("thanks_msg") String thanksMsg,
        @JsonProperty("image_url") String imageUrl,
        @JsonProperty("creation_count") int creationCount,
        @JsonProperty("one_liner") String oneLiner,
        @JsonProperty("is_charged_immediately") boolean isChargedImmediately,
        @JsonProperty("patron_count") int patronCount,
        @JsonProperty("display_patron_goals") boolean displayPatronGoals,
        @JsonProperty("pledge_url") String pledgeUrl,
        @JsonProperty("pay_per_name") String payPerName,
        @JsonProperty("thanks_embed") String thanksEmbed,
        @JsonProperty("main_video_embed") String mainVideoEmbed,
        @JsonProperty("thanks_video_url") String thanksVideoUrl,
        @JsonProperty("about") String about
    ) {
        this.pledgeSum = pledgeSum;
        this.creationName = creationName;
        this.discordServerId = discordServerId;
        this.createdAt = createdAt;
        this.isPlural = isPlural;
        this.mainVideoUrl = mainVideoUrl;
        this.isNsfw = isNsfw;
        this.isMonthly = isMonthly;
        this.publishedAt = publishedAt;
        this.earningsVisibility = earningsVisibility;
        this.outstandingPaymentAmountCents = outstandingPaymentAmountCents;
        this.imageSmallUrl = imageSmallUrl;
        this.summary = summary;
        this.thanksMsg = thanksMsg;
        this.imageUrl = imageUrl;
        this.creationCount = creationCount;
        this.oneLiner = oneLiner;
        this.isChargedImmediately = isChargedImmediately;
        this.patronCount = patronCount;
        this.displayPatronGoals = displayPatronGoals;
        this.pledgeUrl = pledgeUrl;
        this.payPerName = payPerName;
        this.thanksEmbed = thanksEmbed;
        this.mainVideoEmbed = mainVideoEmbed;
        this.thanksVideoUrl = thanksVideoUrl;
        this.about = about;
    }

    public int getPledgeSum() {
        return pledgeSum;
    }

    public String getCreationName() {
        return creationName;
    }

    public String getDiscordServerId() {
        return discordServerId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public boolean isPlural() {
        return isPlural;
    }

    public String getMainVideoUrl() {
        return mainVideoUrl;
    }

    public boolean isNsfw() {
        return isNsfw;
    }

    public boolean isMonthly() {
        return isMonthly;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public String getEarningsVisibility() {
        return earningsVisibility;
    }

    public int getOutstandingPaymentAmountCents() {
        return outstandingPaymentAmountCents;
    }

    public String getImageSmallUrl() {
        return imageSmallUrl;
    }

    public String getSummary() {
        return summary;
    }

    public String getThanksMsg() {
        return thanksMsg;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getCreationCount() {
        return creationCount;
    }

    public String getOneLiner() {
        return oneLiner;
    }

    public boolean isChargedImmediately() {
        return isChargedImmediately;
    }

    public int getPatronCount() {
        return patronCount;
    }

    public boolean isDisplayPatronGoals() {
        return displayPatronGoals;
    }

    public String getPledgeUrl() {
        return pledgeUrl;
    }

    public String getPayPerName() {
        return payPerName;
    }

    public String getThanksEmbed() {
        return thanksEmbed;
    }

    public String getMainVideoEmbed() {
        return mainVideoEmbed;
    }

    public String getThanksVideoUrl() {
        return thanksVideoUrl;
    }

    public String getAbout() {
        return about;
    }
}
