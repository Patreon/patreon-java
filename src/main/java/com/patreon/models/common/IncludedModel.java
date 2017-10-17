package com.patreon.models.common;

import com.google.gson.JsonSyntaxException;
import com.patreon.models.campaign.misc.full.Reward;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.patreon.PatreonAPI.gson;
import static com.patreon.PatreonAPI.toObject;

public class IncludedModel {
    private List<Object> included;

    public List<Object> getIncluded() {
        return included;
    }

    public <E> E returnAt(int index, Class<E> clazz) {
        if (index < 0 || index >= included.size())
            throw new IllegalArgumentException("Invalid index provided. Size is " + included.size());
        return toObject(gson.toJson(included.get(index)), clazz);
    }

    public <E> List<E> getAll(Class<E> clazz) {
        List<E> eList = new ArrayList<>();
        included.forEach(obj -> {
            try {
                E e = toObject(gson.toJson(obj), clazz);
                if (e != null) eList.add(e);
            } catch (JsonSyntaxException | ClassCastException ignored) {
                ignored.printStackTrace();
            }
        });
        return eList;
    }

    @Override
    public String toString() {
        return gson.toJson(included);
    }
}
