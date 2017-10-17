package com.patreon.models.campaign.misc.simple;

import com.github.jasminb.jsonapi.annotations.Meta;
import com.patreon.models.common.LinkedModel;

public class SimpleReward extends LinkedModel {
    @Meta
    private SimpleRewards.SimpleRewardData data;
}
