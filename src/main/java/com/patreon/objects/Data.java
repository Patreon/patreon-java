package com.patreon.objects;

public class Data {
    private String id;
    private String type;

    public Data(String id, String type) {
        this.id = id;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }
}