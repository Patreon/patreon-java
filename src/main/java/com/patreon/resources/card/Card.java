package com.patreon.resources.card;

import com.github.jasminb.jsonapi.annotations.Meta;
import com.github.jasminb.jsonapi.annotations.Type;
import com.patreon.resources.campaign.misc.simple.SimplePatron;
import com.patreon.resources.shared.RelationshipsModel;

@Type("card")
public class Card extends RelationshipsModel {
    @Meta
    private CardData attributes;
    private String type;

    public String getType() {
        return type;
    }

    public SimplePatron getUser() {
        return getRelationship("user", SimplePatron.class);
    }

    public CardData getAttributes() {
        return attributes;
    }
}
