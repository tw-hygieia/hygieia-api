package com.capitalone.dashboard.model;

import java.time.Duration;
import java.util.List;

public class LeadTimeResponse {
    private final Duration mean;
    private final List<DeployedCommitResponse> deployedCommits;

    public LeadTimeResponse(Duration mean, List<DeployedCommitResponse> deployedCommits) {

        this.mean = mean;
        this.deployedCommits = deployedCommits;
    }

    public Duration getMean() {
        return mean;
    }

    public List<DeployedCommitResponse> getDeployedCommits() {
        return deployedCommits;
    }
}
