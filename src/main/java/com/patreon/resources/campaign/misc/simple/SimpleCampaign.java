package com.patreon.resources.campaign.misc.simple;

import com.github.jasminb.jsonapi.annotations.Meta;
import com.github.jasminb.jsonapi.annotations.Type;
import com.patreon.resources.shared.BaseIdModel;
import com.patreon.resources.shared.LinkedModel;

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
