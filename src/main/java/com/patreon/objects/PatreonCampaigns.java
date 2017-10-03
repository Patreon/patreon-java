package com.patreon.objects;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.patreon.PatreonAPI.gson;
import static com.patreon.PatreonAPI.toObject;

public class PatreonCampaigns {
    private List<CampaignData> data;
    private JsonArray included /* Unable to determine a consistent response for this */;
    private String type;

    public PatreonCampaigns(List<CampaignData> data, JsonArray included) {
        this.data = data;
        this.included = included;
    }

    public List<PatreonUser.PatreonUserData> getUsersWithSocialConnections() {
        List<PatreonUser.PatreonUserData> users = new ArrayList<>();
        included.forEach(obj -> {
            try {
                PatreonUser.PatreonUserData user = toObject(gson.toJson(obj), PatreonUser.PatreonUserData.class);
                Integer.valueOf(user.getId());
                if (user.getType().equals("user")) users.add(user);
            } catch (JsonSyntaxException | NumberFormatException ignored) {
            }
        });
        System.out.println(users.size());
        return users;
    }

    public List<Goal> getGoals() {
        List<Goal> goals = new ArrayList<>();
        included.forEach(obj -> {
            try {
                Goal goal = toObject(gson.toJson(obj), Goal.class);
                System.out.println(goal.getType());
                if (goal.getType().equals("goal")) goals.add(goal);
            } catch (JsonSyntaxException | NumberFormatException ignored) {
            }
        });
       return goals;
    }

    public List<Reward> getRewards() {
        List<Reward> rewards = new ArrayList<>();
        included.forEach(obj -> {
            try {
                Reward reward = toObject(gson.toJson(obj), Reward.class);
                if (reward.getType().equals("reward")) rewards.add(reward);
            } catch (JsonSyntaxException | NumberFormatException ignored) {
            }
        });
        return rewards;
    }

    public List<Card> getCards() {
        List<Card> cards = new ArrayList<>();
        included.forEach(obj -> {
            try {
                Card card = toObject(gson.toJson(obj), Card.class);
                if (card.getType().equals("card")) cards.add(card);
            } catch (JsonSyntaxException | NumberFormatException ignored) {
            }
        });
        return cards;
    }

    public List<CampaignData> getCampaigns() {
        return data;
    }

    public static class Card {
        private CardAttributes attributes;
        private String id;
        private String type;
        private CardRelationships relationships;

        public String getType() {
            return type;
        }

        public CardAttributes getAttributes() {
            return attributes;
        }

        public String getId() {
            return id;
        }

        public CardRelationships getRelationships() {
            return relationships;
        }
    }

    public static class CardRelationships {
        private CardUserLink user;

        public CardUserLink getUser() {
            return user;
        }
    }

    public static class CardUserLink {
        private Data data;
        private HashMap<String, String> links;

        public HashMap<String, String> getLinks() {
            return links;
        }

        public Data getData() {
            return data;
        }
    }

    public static class CardAttributes {
        private String card_type;
        private String created_at;
        private String expiration_date;
        private boolean has_a_failed_payment;
        private boolean is_verified;
        private String number;
        private String payment_token;
        private int payment_token_id;

        public String getCardType() {
            return card_type;
        }

        public String getCreatedAt() {
            return created_at;
        }

        public String getExpirationDate() {
            return expiration_date;
        }

        public boolean hasAFailedPayment() {
            return has_a_failed_payment;
        }

        public boolean isVerified() {
            return is_verified;
        }

        public String getNumber() {
            return number;
        }

        public String getPaymentToken() {
            return payment_token;
        }

        public int getPaymentTokenId() {
            return payment_token_id;
        }
    }

    public static class Reward {
        private String id;
        private RewardAttributes attributes;
        private RewardRelationships relationships;
        private String type;

        public String getId() {
            return id;
        }

        public RewardAttributes getAttributes() {
            return attributes;
        }

        public RewardRelationships getRelationships() {
            return relationships;
        }

        public String getType() {
            return type;
        }
    }

    public static class RewardAttributes {
        private int amount;
        private int amount_cents;
        private String description;
        private String id;
        private int remaining;
        private boolean requires_shipping;
        private String type;

        public int getAmount() {
            return amount;
        }

        public int getAmountCents() {
            return amount_cents;
        }

        public String getDescription() {
            return description;
        }

        public String getId() {
            return id;
        }

        public int getRemaining() {
            return remaining;
        }

        public boolean doesRequireShipping() {
            return requires_shipping;
        }

        public String getType() {
            return type;
        }
    }

    public static class RewardRelationships {
        private Creator creator;

        public Creator getCreator() {
            return creator;
        }
    }

    public static class Goal {
        private String id;
        private String type;
        private GoalAttributes attributes;

        public GoalAttributes getAttributes() {
            return attributes;
        }

        public String getType() {
            return type;
        }

        public String getId() {
            return id;
        }
    }

    public static class GoalAttributes {
        private int amount_cents;
        private String reached_at;
        private String created_at;
        private String description;
        private String title;
        private int completed_percentage;

        public int getCompleted_percentage() {
            return completed_percentage;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        public String getCreated_at() {
            return created_at;
        }

        public String getReached_at() {
            return reached_at;
        }

        public int getAmount_cents() {
            return amount_cents;
        }
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
            private SimpleGoalList goals;
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


    public static class SimpleGoalList {
        private List<Data> data;

        public SimpleGoalList(List<Data> data) {
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