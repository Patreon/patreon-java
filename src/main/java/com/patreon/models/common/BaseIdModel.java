package com.patreon.models.common;

import com.github.jasminb.jsonapi.annotations.Id;

public class BaseIdModel {
    @Id
    private String id;

    public String getId() {
        return id;
    }
}
