package com.patreon.resources.pledge;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import com.patreon.resources.reward.Reward;
import com.patreon.resources.shared.RelationshipsModel;
import com.patreon.resources.user.PatreonUser;

@Type("pledge")
public class Pledge extends RelationshipsModel {
    private int amountCents;
    private String createdAt;
    private String declinedSince;
    private boolean patronPaysFees;
    private int pledgeCapCents;

    @Relationship("creator")
    private PatreonUser creator;

    @Relationship("patron")
    private PatreonUser patron;

    @Relationship("reward")
    private Reward reward;

    public Pledge(
        @JsonProperty("amount_cents") int amount_cents,
        @JsonProperty("created_at") String created_at,
        @JsonProperty("declined_since") String declined_since,
        @JsonProperty("patron_pays_fees") boolean patron_pays_fees,
        @JsonProperty("pledge_cap_cents") int pledge_cap_cents,
        @JsonProperty("creator") PatreonUser creator,
        @JsonProperty("patron") PatreonUser patron,
        @JsonProperty("reward") Reward reward
    ) {
        this.amountCents = amount_cents;
        this.createdAt = created_at;
        this.declinedSince = declined_since;
        this.patronPaysFees = patron_pays_fees;
        this.pledgeCapCents = pledge_cap_cents;
        this.creator = creator;
        this.patron = patron;
        this.reward = reward;
    }

    public int getAmountCents() {
        return amountCents;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getDeclinedSince() {
        return declinedSince;
    }

    public boolean getPatronPaysFees() {
        return patronPaysFees;
    }

    public int getPledgeCapCents() {
        return pledgeCapCents;
    }

    public PatreonUser getCreator() {
        return creator;
    }

    public PatreonUser getPatron() {
        return patron;
    }

    public Reward getReward() {
        return reward;
    }
}
