package com.patreon.resources.shared;

import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Links;
import com.github.jasminb.jsonapi.annotations.Type;

public class BaseResource {
    @Id
    private String id;

    private String type;

    @Links
    private com.github.jasminb.jsonapi.Links links;

    public String getId() {
        return id;
    }

    public String getType() {
        Type type = this.getClass().getAnnotation(Type.class);
        return type.value();
    }

    public com.github.jasminb.jsonapi.Links getLinks() {
        return links;
    }

    @Override
    public int hashCode() {
        String typeAndId = getType().concat(getId());
        return typeAndId.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return this.hashCode() == o.hashCode();
    }
}
