package com.patreon.objects;

import java.util.HashMap;
import java.util.List;

public class PatreonPledges {
    private List<PatreonPledge> data;
    private List<Object> included;
    private HashMap<String, String> links;
    private HashMap<String, Object> meta;

    public PatreonPledges(List<PatreonPledge> data, List<Object> included, HashMap<String, String> links, HashMap<String, Object> meta) {
        this.data = data;
        this.included = included;
        this.links = links;
        this.meta = meta;
    }

    public HashMap<String, Object> getMeta() {
        return meta;
    }

    public HashMap<String, String> getLinks() {
        return links;
    }

    public List<Object> getIncluded() {
        return included;
    }

    public List<PatreonPledge> getData() {
        return data;
    }
}
