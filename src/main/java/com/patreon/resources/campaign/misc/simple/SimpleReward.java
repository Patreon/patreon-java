package com.patreon.resources.campaign.misc.simple;

import com.github.jasminb.jsonapi.annotations.Meta;
import com.patreon.resources.shared.LinkedModel;

public class SimpleReward extends LinkedModel {
    @Meta
    private SimpleRewards.SimpleRewardData data;
}
