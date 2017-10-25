package com.patreon.resources.shared;

import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Links;

public class BaseResource {
    @Id
    private String id;

    @Links
    private com.github.jasminb.jsonapi.Links links;

    public String getId() {
        return id;
    }

    public com.github.jasminb.jsonapi.Links getLinks() {
        return links;
    }
}
