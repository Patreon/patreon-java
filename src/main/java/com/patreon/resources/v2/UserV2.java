package com.patreon.resources.v2;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import com.patreon.resources.shared.BaseResource;
import com.patreon.resources.shared.Field;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Type("user")
public class UserV2 extends BaseResource {
  public enum UserField implements Field {
    About("about", true),
    CanSeeNsfw("can_see_nsfw", true),
    Created("created", true),
    Email("email", true),
    FirstName("first_name", true),
    FullName("full_name", true),
    HidePledges("hide_pledges", true),
    ImageUrl("image_url", true),
    IsEmailVerified("is_email_verified", true),
    LastName("last_name", true),
    LikeCount("like_count", true),
    //    TODO: add this
//    SocialConnections("social_connections", true),
    ThumbUrl("thumb_url", true),
    Url("url", true),
    ;

    /**
     * The field's name from the API in JSON
     */
    public final String propertyName;

    /**
     * Whether the field is included by default
     */
    public final boolean isDefault;

    UserField(String propertyName, boolean isDefault) {
      this.propertyName = propertyName;
      this.isDefault = isDefault;
    }

    @Override
    public String getPropertyName() {
      return this.propertyName;
    }

    @Override
    public boolean isDefault() {
      return this.isDefault;
    }

    public static Collection<UserField> getDefaultFields() {
      return Arrays.stream(values()).filter(Field::isDefault).collect(Collectors.toList());
    }
  }

  private String about;
  private boolean can_see_nsfw;
  private String created;
  private String email;
  private String first_name;
  private String full_name;
  private boolean hide_pledges;
  private String image_url;
  private boolean is_email_verified;
  private String last_name;
  private int like_count;
  private String thumb_url;
  private String url;

  @Relationship("campaign")
  private CampaignV2 campaign;

  @JsonCreator
  public UserV2(
    @JsonProperty("about") String about,
    @JsonProperty("can_see_nsfw") boolean can_see_nsfw,
    @JsonProperty("created") String created,
    @JsonProperty("email") String email,
    @JsonProperty("first_name") String first_name,
    @JsonProperty("full_name") String full_name,
    @JsonProperty("hide_pledges") boolean hide_pledges,
    @JsonProperty("image_url") String image_url,
    @JsonProperty("is_email_verified") boolean is_email_verified,
    @JsonProperty("last_name") String last_name,
    @JsonProperty("like_count") int like_count,
    @JsonProperty("thumb_url") String thumb_url,
    @JsonProperty("url") String url,
    @JsonProperty("campaign") CampaignV2 campaign
  ) {
    this.about = about;
    this.can_see_nsfw = can_see_nsfw;
    this.created = created;
    this.email = email;
    this.first_name = first_name;
    this.full_name = full_name;
    this.hide_pledges = hide_pledges;
    this.image_url = image_url;
    this.is_email_verified = is_email_verified;
    this.last_name = last_name;
    this.like_count = like_count;
    this.thumb_url = thumb_url;
    this.url = url;
    this.campaign = campaign;
  }

  public String getAbout() {
    return about;
  }

  public boolean isCanSeeNsfw() {
    return can_see_nsfw;
  }

  public String getCreated() {
    return created;
  }

  public String getEmail() {
    return email;
  }

  public String getFirstName() {
    return first_name;
  }

  public String getFullName() {
    return full_name;
  }

  public boolean isHide_pledges() {
    return hide_pledges;
  }

  public String getImage_url() {
    return image_url;
  }

  public boolean isEmailVerified() {
    return is_email_verified;
  }

  public String getLastName() {
    return last_name;
  }

  public int getLikeCount() {
    return like_count;
  }

  public String getThumbUrl() {
    return thumb_url;
  }

  public String getUrl() {
    return url;
  }
  public CampaignV2 getCampaign() {
    return campaign;
  }
}
