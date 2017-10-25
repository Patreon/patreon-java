package com.patreon.resources.campaign.misc.simple;

import com.github.jasminb.jsonapi.annotations.Meta;
import com.patreon.resources.shared.BaseResource;

public class SimpleReward extends BaseResource {
    @Meta
    private SimpleRewards.SimpleRewardData data;
}
