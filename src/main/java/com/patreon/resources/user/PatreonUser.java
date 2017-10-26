package com.patreon.resources.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import com.patreon.resources.pledge.Pledge;
import com.patreon.resources.shared.BaseResource;

import java.util.Date;
import java.util.List;

@Type("user")
public class PatreonUser extends BaseResource {
    private String firstName;
    private String lastName;
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
    private int gender;
    private SocialConnections socialConnections;
    private boolean isNuked;
    private boolean isEmailVerified;
    private boolean isSuspended;
    private boolean isDeleted;
    private boolean hasPassword;

    @JsonCreator
    public PatreonUser(
        @JsonProperty("first_name") String firstName,
        @JsonProperty("last_name") String lastName,
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
        @JsonProperty("gender") int gender,
        @JsonProperty("social_connections") SocialConnections socialConnections,
        @JsonProperty("is_nuked") boolean isNuked,
        @JsonProperty("is_email_verified") boolean isEmailVerified,
        @JsonProperty("is_suspended") boolean isSuspended,
        @JsonProperty("is_deleted") boolean isDeleted,
        @JsonProperty("has_password") boolean hasPassword,
        @JsonProperty("pledges") List<Pledge> pledges
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
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
        this.gender = gender;
        this.socialConnections = socialConnections;
        this.isNuked = isNuked;
        this.isEmailVerified = isEmailVerified;
        this.isSuspended = isSuspended;
        this.isDeleted = isDeleted;
        this.hasPassword = hasPassword;
        this.pledges = pledges;
    }

    @Relationship("pledges")
    private List<Pledge> pledges;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
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

    public int getGender() {
        return gender;
    }

    public SocialConnections getSocialConnections() {
        return socialConnections;
    }

    public boolean getIsNuked() {
        return isNuked;
    }

    public boolean getIsEmailVerified() {
        return isEmailVerified;
    }

    public boolean getIsSuspended() {
        return isSuspended;
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public boolean getHasPassword() {
        return hasPassword;
    }

    public List<Pledge> getPledges() {
        return pledges;
    }
}
