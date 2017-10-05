package com.patreon.models.user;

public class PatreonUserAttributes {
    private String first_name;
    private String last_name;
    private String full_name;
    private String discord_id;
    private String twitch;
    private String vanity;
    private String email;
    private String about;
    private String facebook_id;
    private String image_url;
    private String thumb_url;
    private String youtube;
    private String twitter;
    private String facebook;
    private String created;
    private String url;
    private int gender;
    private SocialConnections social_connections;
    private boolean is_nuked;
    private boolean is_email_verified;
    private boolean is_suspended;
    private boolean is_deleted;
    private boolean has_password;

    public boolean isSuspended() {
        return is_suspended;
    }

    public boolean hasPassword() {
        return has_password;
    }

    public boolean isDeleted() {
        return is_deleted;
    }

    public boolean isNuked() {
        return is_nuked;
    }

    public boolean isEmailVerified() {
        return is_email_verified;
    }

    public String getFirstName() {
        return first_name;
    }

    public String getLastName() {
        return last_name;
    }

    public String getFullName() {
        return full_name;
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
        return facebook_id;
    }

    public String getImageUrl() {
        return image_url;
    }

    public String getThumbUrl() {
        return thumb_url;
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

    public String getCreated() {
        return created;
    }

    public String getUrl() {
        return url;
    }

    public SocialConnections getSocialConnections() {
        return social_connections;
    }
}
