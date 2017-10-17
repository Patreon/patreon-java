package com.patreon.models.common;

import com.github.jasminb.jsonapi.annotations.Links;

import java.util.HashMap;

public class LinkedIncludedCombinedModel extends IncludedModel {
    @Links
    private HashMap<String, String> links;

    public HashMap<String, String> getLinks() {
        return links;
    }

    public String getLink(String key) {
        return links.get(key);
    }
}
