package com.patreon.models.campaign.misc.simple;

import com.github.jasminb.jsonapi.annotations.Meta;
import com.github.jasminb.jsonapi.annotations.Type;
import com.patreon.models.common.BaseIdModel;
import com.patreon.models.common.LinkedModel;

public class SimpleCampaign extends LinkedModel {
    @Meta
    private SimpleCampaignData data;

    public SimpleCampaignData getData() {
        return data;
    }

    @Type("campaign")
    public static class SimpleCampaignData extends BaseIdModel {
        private String type;

        public String getType() {
            return type;
        }
    }
}
