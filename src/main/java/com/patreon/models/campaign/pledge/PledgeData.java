package com.patreon.models.campaign.pledge;

public class PledgeData {
    private int amount_cents;
    private String created_at;
    private String declined_since;
    private boolean patron_pays_fees;
    private int pledge_cap_cents;

    public int getPledgeCapCents() {
        return pledge_cap_cents;
    }

    /**
     * Seems to always return false...
     * @return false
     */
    private boolean doesPatronPayFees() {
        return patron_pays_fees;
    }

    public String getDeclinedSince() {
        return declined_since;
    }

    public String getCreatedAt() {
        return created_at;
    }

    public int getAmountCents() {
        return amount_cents;
    }
}
