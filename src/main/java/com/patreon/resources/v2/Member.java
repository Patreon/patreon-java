package com.patreon.resources.v2;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import com.patreon.resources.shared.BaseResource;
import com.patreon.resources.shared.Field;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Type("member")
public class Member extends BaseResource {
  public enum MemberField implements Field {
    CampaignLifetimeSupportCents("campaign_lifetime_support_cents", true),
    CurrentlyEntitledAmountCents("currently_entitled_amount_cents", true),
    Email("email", true),
    FullName("full_name", true),
    IsFollower("is_follower", true),
    LastChargeDate("last_charge_date", true),
    LastChargeStatus("last_charge_status", true),
    LifetimeSupportCents("lifetime_support_cents", true),
    NextChargeDate("next_charge_date", true),
    Note ("note", true),
    PatronStatus("patron_status", true),
    PledgeCadence("pledge_cadence", true),
    PledgeRelationshipStart("pledge_relationship_start", true),
    WillPayAmountCents("will_pay_amount_cents", true),
    ;


    /**
     * The field's name from the API in JSON
     */
    public final String propertyName;

    /**
     * Whether the field is included by default
     */
    public final boolean isDefault;
    MemberField(String propertyName, boolean isDefault) {
      this.propertyName = propertyName;
      this.isDefault = isDefault;
    }

    @Override
    public String getPropertyName() {
      return this.propertyName;
    }

    @Override
    public boolean isDefault() {
      return this.isDefault;
    }

    public static Collection<Member.MemberField> getDefaultFields() {
      return Arrays.stream(values()).filter(Field::isDefault).collect(Collectors.toList());
    }
  }

  private int campaign_lifetime_support_cents;
  private int currently_entitled_amount_cents;
  private String email;
  private String full_name;
  private boolean is_follower;
  private String last_charge_date;
  private String last_charge_status;
  private int lifetime_support_cents;
  private String next_charge_date;
  private String note;
  private String patron_status;
  private int pledge_cadence;
  private String pledge_relationship_start;
  private int will_pay_amount_cents;

  @Relationship("campaign")
  private CampaignV2 campaign;

  @Relationship("user")
  private UserV2 user;

  @JsonCreator
  public Member(
    @JsonProperty ("campaign_lifetime_support_cents") int campaign_lifetime_support_cents,
    @JsonProperty ("currently_entitled_amount_cents") int currently_entitled_amount_cents,
    @JsonProperty ("email") String email,
    @JsonProperty ("full_name") String full_name,
    @JsonProperty ("is_follower") boolean is_follower,
    @JsonProperty ("last_charge_date") String last_charge_date,
    @JsonProperty ("last_charge_status") String last_charge_status,
    @JsonProperty ("lifetime_support_cents") int lifetime_support_cents,
    @JsonProperty ("next_charge_date") String next_charge_date,
    @JsonProperty ("note") String note,
    @JsonProperty ("patron_status") String patron_status,
    @JsonProperty ("pledge_cadence") int pledge_cadence,
    @JsonProperty ("pledge_relationship_start") String pledge_relationship_start,
    @JsonProperty ("will_pay_amount_cents") int will_pay_amount_cents,
    @JsonProperty("campaign") CampaignV2 campaign,
    @JsonProperty("user") UserV2 user
  ) {
    this.campaign_lifetime_support_cents = campaign_lifetime_support_cents;
    this.currently_entitled_amount_cents = currently_entitled_amount_cents;
    this.email = email;
    this.full_name = full_name;
    this.is_follower = is_follower;
    this.last_charge_date = last_charge_date;
    this.last_charge_status = last_charge_status;
    this.lifetime_support_cents = lifetime_support_cents;
    this.next_charge_date = next_charge_date;
    this.note = note;
    this.patron_status = patron_status;
    this.pledge_cadence = pledge_cadence;
    this.pledge_relationship_start = pledge_relationship_start;
    this.will_pay_amount_cents = will_pay_amount_cents;
    this.campaign = campaign;
    this.user = user;
  }

  public int getCampaignLifetimeSupportCents() {
    return campaign_lifetime_support_cents;
  }

  public int getCurrentlyEntitledAmountCents() {
    return currently_entitled_amount_cents;
  }

  public String getEmail() {
    return email;
  }

  public String getFullName() {
    return full_name;
  }

  public boolean isFollower() {
    return is_follower;
  }

  public String getLastChargeDate() {
    return last_charge_date;
  }

  public String getLastChargeStatus() {
    return last_charge_status;
  }

  public int getLifetimeSupportCents() {
    return lifetime_support_cents;
  }

  public String getNextChargeDate() {
    return next_charge_date;
  }

  public String getNote() {
    return note;
  }

  public String getPatronStatus() {
    return patron_status;
  }

  public int getPledgeCadence() {
    return pledge_cadence;
  }

  public String getPledgeRelationshipStart() {
    return pledge_relationship_start;
  }

  public int getWillPayAmountCents() {
    return will_pay_amount_cents;
  }

  public CampaignV2 getCampaign() {
    return campaign;
  }

  public UserV2 getUser() {
    return user;
  }
}
