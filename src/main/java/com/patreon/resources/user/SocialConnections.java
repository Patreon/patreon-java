package com.patreon.resources.user;


public class SocialConnections {
    private UserIdObject youtube;
    private UserIdObject twitter;
    private UserIdObject deviantart;
    private UserIdObject discord;
    private UserIdObject twitch;
    private UserIdObject facebook;
    private UserIdObject spotify;

    public UserIdObject getYoutube() {
        return youtube;
    }

    public UserIdObject getTwitter() {
        return twitter;
    }

    public UserIdObject getDeviantart() {
        return deviantart;
    }

    public UserIdObject getDiscord() {
        return discord;
    }

    public UserIdObject getTwitch() {
        return twitch;
    }

    public UserIdObject getFacebook() {
        return facebook;
    }

    public UserIdObject getSpotify() {
        return spotify;
    }

    public static class UserIdObject {
        private String user_id;

        public String getUserId() {
            return user_id;
        }
    }
}
