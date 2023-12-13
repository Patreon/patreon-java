package com.patreon.resources.v2;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import com.patreon.resources.shared.BaseResource;
import com.patreon.resources.shared.Field;
import com.patreon.resources.v1.Goal;
import com.patreon.resources.v1.Pledge;
import com.patreon.resources.v1.Reward;
import com.patreon.resources.v1.User;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Type("campaign")
public class CampaignV2 extends BaseResource {
  
  public enum CampaignField implements Field {
    CreatedAt("created_at", true),
    CreationName("creation_name", true),
//    Idk why but 'discord_server_id' gives 500 from server side if we request /identity
//    DiscordServerId("discord_server_id", true),
    GoogleAnalyticsId("google_analytics_id", true),
    HasRss("has_rss", true),
    HasSentRssNotify("has_sent_rss_notify", true),
    ImageSmallUrl("image_small_url", true),
    ImageUrl("image_url", true),
    IsChargedImmediately("is_charged_immediately", true),
    IsMonthly("is_monthly", true),
    IsNsfw("is_nsfw", true),
    MainVideoEmbed("main_video_embed", true),
    MainVideoUrl("main_video_url", true),
    OneLiner("one_liner", true),
    PatronCount("patron_count", true),
    PayPerName("pay_per_name", true),
    PledgeUrl("pledge_url", true),
    PublishedAt("published_at", true),
    RssArtworkUrl("rss_artwork_url", true),
    RssFeedTitle("rss_feed_title", true),
    ShowEarnings("show_earnings", true),
    Summary("summary", true),

//    Idk why but 'thanks_*' gives 500 from server side if we request /identity
//    ThanksEmbed("thanks_embed", true),
//    ThanksMsg("thanks_msg", true),
//    ThanksVideoUrl("thanks_video_url", true),
    Url("url", true),
    Vanity("vanity", true),
    ;

    private final String propertyName;
    private final boolean isDefault;

    CampaignField(String propertyName, boolean isDefault) {
      this.propertyName = propertyName;
      this.isDefault = isDefault;
    }

    public static Collection<CampaignField> getDefaultFields() {
      return Arrays.stream(values()).filter(Field::isDefault).collect(Collectors.toList());
    }

    @Override
    public String getPropertyName() {
      return this.propertyName;
    }

    @Override
    public boolean isDefault() {
      return this.isDefault;
    }
  }

  @JsonProperty("created_at") private String createdAt;
  @JsonProperty("creation_name") private String creationName;
  @JsonProperty("discord_server_id") private String discordServerId;
  @JsonProperty("google_analytics_id") private String googleAnalyticsId;
  @JsonProperty("has_rss") private Boolean hasRss;
  @JsonProperty("has_sent_rss_notify") private Boolean hasSentRssNotify;
  @JsonProperty("image_small_url") private String imageSmallUrl;
  @JsonProperty("image_url") private String imageUrl;
  @JsonProperty("is_charged_immediately") private Boolean isChargedImmediately;
  @JsonProperty("is_monthly") private Boolean isMonthly;
  @JsonProperty("is_nsfw") private Boolean isNsfw;
  @JsonProperty("main_video_embed") private String mainVideoEmbed;
  @JsonProperty("main_video_url") private String mainVideoUrl;
  @JsonProperty("one_liner") private String oneLiner;
  @JsonProperty("patron_count") private Integer patronCount;
  @JsonProperty("pay_per_name") private String payPerName;
  @JsonProperty("pledge_url") private String pledgeUrl;
  @JsonProperty("published_at") private String publishedAt;
  @JsonProperty("rss_artwork_url") private String rssArtworkUrl;
  @JsonProperty("rss_feed_title") private String rssFeedTitle;
  @JsonProperty("show_earnings") private Boolean showEarnings;
  @JsonProperty("summary") private String summary;
  @JsonProperty("thanks_embed") private String thanksEmbed;
  @JsonProperty("thanks_msg") private String thanksMsg;
  @JsonProperty("thanks_video_url") private String thanksVideoUrl;
  @JsonProperty("url") private String url;
  @JsonProperty("vanity") private String vanity;

  public CampaignV2(
    @JsonProperty ("created_at") String createdAt,
    @JsonProperty ("creation_name") String creationName,
    @JsonProperty ("discord_server_id") String discordServerId,
    @JsonProperty ("google_analytics_id") String googleAnalyticsId,
    @JsonProperty ("has_rss") Boolean hasRss,
    @JsonProperty ("has_sent_rss_notify") Boolean hasSentRssNotify,
    @JsonProperty ("image_small_url") String imageSmallUrl,
    @JsonProperty ("image_url") String imageUrl,
    @JsonProperty ("is_charged_immediately") Boolean isChargedImmediately,
    @JsonProperty ("is_monthly") Boolean isMonthly,
    @JsonProperty ("is_nsfw") Boolean isNsfw,
    @JsonProperty ("main_video_embed") String mainVideoEmbed,
    @JsonProperty ("main_video_url") String mainVideoUrl,
    @JsonProperty ("one_liner") String oneLiner,
    @JsonProperty ("patron_count") Integer patronCount,
    @JsonProperty ("pay_per_name") String payPerName,
    @JsonProperty ("pledge_url") String pledgeUrl,
    @JsonProperty ("published_at") String publishedAt,
    @JsonProperty ("rss_artwork_url") String rssArtworkUrl,
    @JsonProperty ("rss_feed_title") String rssFeedTitle,
    @JsonProperty ("show_earnings") Boolean showEarnings,
    @JsonProperty ("summary") String summary,
    @JsonProperty ("thanks_embed") String thanksEmbed,
    @JsonProperty ("thanks_msg") String thanksMsg,
    @JsonProperty ("thanks_video_url") String thanksVideoUrl,
    @JsonProperty ("url") String url,
    @JsonProperty ("vanity") String vanity
  ) {
    this.createdAt = createdAt;
    this.creationName = creationName;
    this.discordServerId = discordServerId;
    this.googleAnalyticsId = googleAnalyticsId;
    this.hasRss = hasRss;
    this.hasSentRssNotify = hasSentRssNotify;
    this.imageSmallUrl = imageSmallUrl;
    this.imageUrl = imageUrl;
    this.isChargedImmediately = isChargedImmediately;
    this.isMonthly = isMonthly;
    this.isNsfw = isNsfw;
    this.mainVideoEmbed = mainVideoEmbed;
    this.mainVideoUrl = mainVideoUrl;
    this.oneLiner = oneLiner;
    this.patronCount = patronCount;
    this.payPerName = payPerName;
    this.pledgeUrl = pledgeUrl;
    this.publishedAt = publishedAt;
    this.rssArtworkUrl = rssArtworkUrl;
    this.rssFeedTitle = rssFeedTitle;
    this.showEarnings = showEarnings;
    this.summary = summary;
    this.thanksEmbed = thanksEmbed;
    this.thanksMsg = thanksMsg;
    this.thanksVideoUrl = thanksVideoUrl;
    this.url = url;
    this.vanity = vanity;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public String getCreationName() {
    return creationName;
  }

  public String getDiscordServerId() {
    return discordServerId;
  }

  public String getGoogleAnalyticsId() {
    return googleAnalyticsId;
  }

  public Boolean getHasRss() {
    return hasRss;
  }

  public Boolean getHasSentRssNotify() {
    return hasSentRssNotify;
  }

  public String getImageSmallUrl() {
    return imageSmallUrl;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public Boolean getChargedImmediately() {
    return isChargedImmediately;
  }

  public Boolean getMonthly() {
    return isMonthly;
  }

  public Boolean getNsfw() {
    return isNsfw;
  }

  public String getMainVideoEmbed() {
    return mainVideoEmbed;
  }

  public String getMainVideoUrl() {
    return mainVideoUrl;
  }

  public String getOneLiner() {
    return oneLiner;
  }

  public Integer getPatronCount() {
    return patronCount;
  }

  public String getPayPerName() {
    return payPerName;
  }

  public String getPledgeUrl() {
    return pledgeUrl;
  }

  public String getPublishedAt() {
    return publishedAt;
  }

  public String getRssArtworkUrl() {
    return rssArtworkUrl;
  }

  public String getRssFeedTitle() {
    return rssFeedTitle;
  }

  public Boolean getShowEarnings() {
    return showEarnings;
  }

  public String getSummary() {
    return summary;
  }

  public String getThanksEmbed() {
    return thanksEmbed;
  }

  public String getThanksMsg() {
    return thanksMsg;
  }

  public String getThanksVideoUrl() {
    return thanksVideoUrl;
  }

  public String getUrl() {
    return url;
  }

  public String getVanity() {
    return vanity;
  }

}
