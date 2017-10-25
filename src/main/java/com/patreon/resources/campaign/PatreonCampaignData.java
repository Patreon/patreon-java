package com.patreon.resources.campaign;

public class PatreonCampaignData {
    private int pledge_sum;
    private String creation_name;
    private String discord_server_id;
    private String created_at;
    private boolean is_plural;
    private String main_video_url;
    private boolean is_nsfw;
    private boolean is_monthly;
    private String published_at;
    private String earnings_visibility;
    private int outstanding_payment_amount_cents;
    private String image_small_url;
    private String summary;
    private String thanks_msg;
    private String image_url;
    private int creation_count;
    private String one_liner;
    private boolean is_charged_immediately;
    private int patron_count;
    private boolean display_patron_goals;
    private String pledge_url;
    private String pay_per_name;
    private String thanks_embed;
    private String main_video_embed;
    private String thanks_video_url;
    private String about;


    public String getAbout() {
        return about;
    }

    public int getPledgeSum() {
        return pledge_sum;
    }

    public String getCreationName() {
        return creation_name;
    }

    public String getDiscordServerId() {
        return discord_server_id;
    }

    public String getCreatedAt() {
        return created_at;
    }

    public boolean isPlural() {
        return is_plural;
    }

    public String getMainVideoUrl() {
        return main_video_url;
    }

    public boolean isNsfw() {
        return is_nsfw;
    }

    public boolean isMonthly() {
        return is_monthly;
    }

    public String getPublishedAt() {
        return published_at;
    }

    public String getEarningsVisibility() {
        return earnings_visibility;
    }

    public int getOutstandingPaymentAmountCents() {
        return outstanding_payment_amount_cents;
    }

    public String getImageSmallUrl() {
        return image_small_url;
    }

    public String getSummary() {
        return summary;
    }

    public String getThanksMsg() {
        return thanks_msg;
    }

    public String getImageUrl() {
        return image_url;
    }

    public int getCreationCount() {
        return creation_count;
    }

    public String getOneLiner() {
        return one_liner;
    }

    public boolean isChargedImmediately() {
        return is_charged_immediately;
    }

    public int getPatronCount() {
        return patron_count;
    }

    public boolean doesDisplayPatronGoals() {
        return display_patron_goals;
    }

    public String getPledgeUrl() {
        return pledge_url;
    }

    public String getPayPerName() {
        return pay_per_name;
    }

    public String getThanksEmbed() {
        return thanks_embed;
    }

    public String getMainVideoEmbed() {
        return main_video_embed;
    }

    public String getThanksVideoUrl() {
        return thanks_video_url;
    }
}
