package com.patreon.resources.v2;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.annotations.Type;
import com.patreon.resources.shared.BaseResource;
import com.patreon.resources.shared.Field;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Attribute	Type	Description
 * amount_cents	integer	Monetary amount associated with this tier (in U.S. cents).
 * created_at	string (UTC ISO format)	Datetime this tier was created.
 * description	string	Tier display description.
 * discord_role_ids	object	The discord role IDs granted by this tier. Can be null.
 * edited_at	string (UTC ISO format)	Datetime tier was last modified.
 * image_url	string	Full qualified image URL associated with this tier. Can be null.
 * patron_count	integer	Number of patrons currently registered for this tier.
 * post_count	integer	Number of posts published to this tier. Can be null.
 * published	boolean	true if the tier is currently published.
 * published_at	string (UTC ISO format)	Datetime this tier was last published. Can be null.
 * remaining	integer	Remaining number of patrons who may subscribe, if there is a user_limit. Can be null.
 * requires_shipping	boolean	true if this tier requires a shipping address from patrons.
 * title	string	Tier display title.
 * unpublished_at	string (UTC ISO format)	Datetime tier was unpublished, while applicable. Can be null.
 * url	string	Fully qualified URL associated with this tier.
 * user_limit	integer	Maximum number of patrons this tier is limited to, if applicable. Can be null.
 */
@Type("tier")
public class Tier extends BaseResource {
  public enum TierField implements Field {
    AmountCents("amount_cents", true),
    CreatedAt("created_at", true),
    Description("description", true),
    DiscordRoleIds("discord_role_ids", true),
    EditedAt("edited_at", true),
    ImageUrl("image_url", true),
    PatronCount("patron_count", true),
    PostCount("post_count", true),
    Published("published", true),
    PublishedAt("published_at", true),
    Remaining("remaining", true),
    RequiresShipping("requires_shipping", true),
    Title("title", true),
    UnpublishedAt("unpublished_at", true),
    Url("url", true),
    UserLimit("user_limit", true),
    ;

    /**
     * The field's name from the API in JSON
     */
    public final String propertyName;

    /**
     * Whether the field is included by default
     */
    public final boolean isDefault;

    TierField(String propertyName, boolean isDefault) {
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

    public static Collection<Tier.TierField> getDefaultFields() {
      return Arrays.stream(values()).filter(Field::isDefault).collect(Collectors.toList());
    }
  }

  private int amount_cents;
  private String created_at;
  private String description;
  private Object discord_role_ids;
  private String edited_at;
  private String image_url;
  private int patron_count;
  private int post_count;
  private boolean published;
  private String published_at;
  private int remaining;
  private boolean requires_shipping;
  private String title;
  private String unpublished_at;
  private String url;
  private int user_limit;

  @JsonCreator
  public Tier(
    @JsonProperty("amount_cents") int amount_cents,
    @JsonProperty("created_at") String created_at,
    @JsonProperty("description") String description,
    @JsonProperty("discord_role_ids") Object discord_role_ids,
    @JsonProperty("edited_at") String edited_at,
    @JsonProperty("image_url") String image_url,
    @JsonProperty("patron_count") int patron_count,
    @JsonProperty("post_count") int post_count,
    @JsonProperty("published") boolean published,
    @JsonProperty("published_at") String published_at,
    @JsonProperty("remaining") int remaining,
    @JsonProperty("requires_shipping") boolean requires_shipping,
    @JsonProperty("title") String title,
    @JsonProperty("unpublished_at") String unpublished_at,
    @JsonProperty("url") String url,
    @JsonProperty("user_limit") int user_limit
  ) {
    this.amount_cents = amount_cents;
    this.created_at = created_at;
    this.description = description;
    this.discord_role_ids = discord_role_ids;
    this.edited_at = edited_at;
    this.image_url = image_url;
    this.patron_count = patron_count;
    this.post_count = post_count;
    this.published = published;
    this.published_at = published_at;
    this.remaining = remaining;
    this.requires_shipping = requires_shipping;
    this.title = title;
    this.unpublished_at = unpublished_at;
    this.url = url;
    this.user_limit = user_limit;
  }

  public int getAmountCents() {
    return amount_cents;
  }

  public String getCreatedAt() {
    return created_at;
  }

  public String getDescription() {
    return description;
  }

  public Object getDiscordRoleIds() {
    return discord_role_ids;
  }

  public String getEditedAt() {
    return edited_at;
  }

  public String getImageUrl() {
    return image_url;
  }

  public int getPatronCount() {
    return patron_count;
  }

  public int getPostCount() {
    return post_count;
  }

  public boolean isPublished() {
    return published;
  }

  public String getPublishedAt() {
    return published_at;
  }

  public int getRemaining() {
    return remaining;
  }

  public boolean isRequiresShipping() {
    return requires_shipping;
  }

  public String getTitle() {
    return title;
  }

  public String getUnpublishedAt() {
    return unpublished_at;
  }

  public String getUrl() {
    return url;
  }

  public int getUserLimit() {
    return user_limit;
  }
}
