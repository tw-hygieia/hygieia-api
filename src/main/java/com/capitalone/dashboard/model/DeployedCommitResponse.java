package com.capitalone.dashboard.model;

import java.time.Duration;
import java.util.Date;

public class DeployedCommitResponse {
    private final String commitSha;
    private final Date deploymentTime;
    private final Date commitedTime;
    private final Duration leadTime;

    public DeployedCommitResponse(String commitSha, Date deploymentTime, Date commitedTime, Duration leadTime) {

        this.commitSha = commitSha;
        this.deploymentTime = deploymentTime;
        this.commitedTime = commitedTime;
        this.leadTime = leadTime;
    }

    public Date getDeploymentTime() {
        return deploymentTime;
    }

    public String getCommitSha() {
        return commitSha;
    }

    public Date getCommitedTime() {
        return commitedTime;
    }

    public Duration getLeadTime() {
        return leadTime;
    }
}
