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


        public PatreonUserData(String type, String id, Attributes attributes, Relationships relationships) {
            this.type = type;
            this.id = id;
            this.attributes = attributes;
            this.relationships = relationships;
        }

        public String getId() {
            return id;
        }

        public Attributes getAttributes() {
            return attributes;
        }

        public Relationships getRelationships() {
            return relationships;
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


        public Attributes(String first_name, String last_name, String full_name, String vanity, String email, String about,
                          String facebook_id, String image_url, String thumb_url, String youtube, String twitter, String facebook,
                          String created, String url) {
            this.first_name = first_name;
            this.last_name = last_name;
            this.full_name = full_name;
            this.vanity = vanity;
            this.email = email;
            this.about = about;
            this.facebook_id = facebook_id;
            this.image_url = image_url;
            this.thumb_url = thumb_url;
            this.youtube = youtube;
            this.twitter = twitter;
            this.facebook = facebook;
            this.created = created;
            this.url = url;
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
    }

    public static class Relationships {
        private PatreonCampaigns campaign;

        public Relationships(PatreonCampaigns campaign) {
            this.campaign = campaign;
        }

        public PatreonCampaigns getCampaign() {
            return campaign;
        }
    }
}