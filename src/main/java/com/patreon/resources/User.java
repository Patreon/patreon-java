package com.patreon.resources;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import com.patreon.resources.shared.BaseResource;
import com.patreon.resources.shared.SocialConnections;

import java.util.Date;
import java.util.List;

@Type("user")
public class User extends BaseResource {
  private String fullName;
  private String discordId;
  private String twitch;
  private String vanity;
  private String email;
  private String about;
  private String facebookId;
  private String imageUrl;
  private String thumbUrl;
  private String youtube;
  private String twitter;
  private String facebook;
  private Date created;
  private String url;
  private SocialConnections socialConnections;
  private boolean isEmailVerified;
  @Relationship("pledges")
  private List<Pledge> pledges;

  @JsonCreator
  public User(
               @JsonProperty("full_name") String fullName,
               @JsonProperty("discord_id") String discordId,
               @JsonProperty("twitch") String twitch,
               @JsonProperty("vanity") String vanity,
               @JsonProperty("email") String email,
               @JsonProperty("about") String about,
               @JsonProperty("facebook_id") String facebookId,
               @JsonProperty("image_url") String imageUrl,
               @JsonProperty("thumb_url") String thumbUrl,
               @JsonProperty("youtube") String youtube,
               @JsonProperty("twitter") String twitter,
               @JsonProperty("facebook") String facebook,
               @JsonProperty("created") Date created,
               @JsonProperty("url") String url,
               @JsonProperty("social_connections") SocialConnections socialConnections,
               @JsonProperty("is_email_verified") boolean isEmailVerified,
               @JsonProperty("pledges") List<Pledge> pledges
  ) {
    this.fullName = fullName;
    this.discordId = discordId;
    this.twitch = twitch;
    this.vanity = vanity;
    this.email = email;
    this.about = about;
    this.facebookId = facebookId;
    this.imageUrl = imageUrl;
    this.thumbUrl = thumbUrl;
    this.youtube = youtube;
    this.twitter = twitter;
    this.facebook = facebook;
    this.created = created;
    this.url = url;
    this.socialConnections = socialConnections;
    this.isEmailVerified = isEmailVerified;
    this.pledges = pledges;
  }

  public String getFullName() {
    return fullName;
  }

  public String getDiscordId() {
    return discordId;
  }

  public String getTwitch() {
    return twitch;
  }

  public String getVanity() {
    return vanity;
  }

  public String getEmail() {
    return email;
  }

  public String getAbout() {
    return about;
  }

  public String getFacebookId() {
    return facebookId;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public String getThumbUrl() {
    return thumbUrl;
  }

  public String getYoutube() {
    return youtube;
  }

  public String getTwitter() {
    return twitter;
  }

  public String getFacebook() {
    return facebook;
  }

  public Date getCreated() {
    return created;
  }

  public String getUrl() {
    return url;
  }

  public SocialConnections getSocialConnections() {
    return socialConnections;
  }

  public boolean getIsEmailVerified() {
    return isEmailVerified;
  }


  public List<Pledge> getPledges() {
    return pledges;
  }
}
