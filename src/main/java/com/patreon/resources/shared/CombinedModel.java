package com.patreon.resources.shared;

import com.github.jasminb.jsonapi.annotations.Id;

public class CombinedModel extends LinkedModel {
    @Id
    private String id;

    public String getId() {
        return id;
    }
}
