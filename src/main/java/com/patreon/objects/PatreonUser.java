package com.patreon.objects;

import java.util.HashMap;

public class PatreonUser {
    private PatreonUserData data;
    private HashMap<String, String> links;

    public PatreonUser(PatreonUserData data, HashMap<String, String> links) {
        this.data = data;
        this.links = links;
    }

    public HashMap<String, String> getLinks() {
        return links;
    }

    public PatreonUserData getData() {
        return data;
    }

    public static class PatreonUserData {
        private String type;
        private String id;
        private Attributes attributes;
        private Relationships relationships;

        public String getId() {
            return id;
        }

        public Attributes getAttributes() {
            return attributes;
        }

        public Relationships getRelationships() {
            return relationships;
        }

        public String getType() {
            return type;
        }
    }

    public static class SocialConnections {
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

    public static class Attributes {
        private String first_name;
        private String last_name;
        private String full_name;
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
    }

    public static class Relationships {
        private PatreonCampaigns campaign;

        public PatreonCampaigns getCampaign() {
            return campaign;
        }
    }
}