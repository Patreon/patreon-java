package com.patreon.objects;

import java.util.HashMap;
import java.util.List;

public class PatreonCampaigns {
    private List<CampaignData> data;
    private List<Object> included /* Unable to determine a consistent response for this */;

    public PatreonCampaigns(List<CampaignData> data, List<Object> included) {
        this.data = data;
        this.included = included;
    }

    public List<Object> getIncluded() {
        return included;
    }

    public List<CampaignData> getCampaigns() {
        return data;
    }


    public static class CampaignData {
        private String type;
        private String id;
        private Attributes attributes;
        private Relationships relationships;

        public CampaignData(String type, String id, Attributes attributes, Relationships relationships) {
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

        public static class Attributes {
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

            public Attributes(int pledge_sum, String creation_name, String discord_server_id, String created_at, boolean is_plural, String main_video_url, boolean is_nsfw,
                              boolean is_monthly, String published_at, String earnings_visibility, int outstanding_payment_amount_cents, String image_small_url, String summary, String thanks_msg,
                              String image_url, int creation_count, String one_liner, boolean is_charged_immediately, int patron_count, boolean display_patron_goals, String pledge_url, String pay_per_name,
                              String thanks_embed, String main_video_embed, String thanks_video_url) {
                this.pledge_sum = pledge_sum;
                this.creation_name = creation_name;
                this.discord_server_id = discord_server_id;
                this.created_at = created_at;
                this.is_plural = is_plural;
                this.main_video_url = main_video_url;
                this.is_nsfw = is_nsfw;
                this.is_monthly = is_monthly;
                this.published_at = published_at;
                this.earnings_visibility = earnings_visibility;
                this.outstanding_payment_amount_cents = outstanding_payment_amount_cents;
                this.image_small_url = image_small_url;
                this.summary = summary;
                this.thanks_msg = thanks_msg;
                this.image_url = image_url;
                this.creation_count = creation_count;
                this.one_liner = one_liner;
                this.is_charged_immediately = is_charged_immediately;
                this.patron_count = patron_count;
                this.display_patron_goals = display_patron_goals;
                this.pledge_url = pledge_url;
                this.pay_per_name = pay_per_name;
                this.thanks_embed = thanks_embed;
                this.main_video_embed = main_video_embed;
                this.thanks_video_url = thanks_video_url;
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

            public boolean isIsChargedImmediately() {
                return is_charged_immediately;
            }

            public int getPatronCount() {
                return patron_count;
            }

            public boolean isDisplayPatronGoals() {
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

        public static class Relationships {
            private Creator creator;
            private RewardList rewards;
            private GoalList goals;
        }
    }

    public static class RewardList {
        private List<Data> data;
        public RewardList(List<Data> data) {
            this.data = data;
        }

        public List<Data> getData() {
            return data;
        }
    }


    public static class GoalList {
        private List<Data> data;
        public GoalList(List<Data> data) {
            this.data = data;
        }

        public List<Data> getData() {
            return data;
        }
    }

    public static class Creator {
        private Data data;
        private HashMap<String, String> links;

        public Creator(Data data, HashMap<String, String> links) {
            this.data = data;
            this.links = links;
        }

        public Data getData() {
            return data;
        }
    }

}