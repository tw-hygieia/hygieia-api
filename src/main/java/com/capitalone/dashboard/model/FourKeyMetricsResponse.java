package com.capitalone.dashboard.model;

public class FourKeyMetricsResponse {
    private LeadTimeResponse leadTime;

    public FourKeyMetricsResponse(LeadTimeResponse leadTime) {

        this.leadTime = leadTime;
    }

    public LeadTimeResponse getLeadTime() {
        return leadTime;
    }
}
