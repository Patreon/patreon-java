package com.patreon.resources.card;

public class CardData {
    private String card_type;
    private String created_at;
    private String expiration_date;
    private boolean has_a_failed_payment;
    private boolean is_verified;
    private String number;
    private String payment_token;
    private String payment_token_id;

    public String getPaymentTokenId() {
        return payment_token_id;
    }

    public String getPaymentToken() {
        return payment_token;
    }

    public String getNumber() {
        return number;
    }

    public boolean isVerified() {
        return is_verified;
    }

    public boolean hasFailedPayment() {
        return has_a_failed_payment;
    }

    public String getExpirationDate() {
        return expiration_date;
    }

    public String getCreatedAt() {
        return created_at;
    }

    public String getCardType() {
        return card_type;
    }
}
