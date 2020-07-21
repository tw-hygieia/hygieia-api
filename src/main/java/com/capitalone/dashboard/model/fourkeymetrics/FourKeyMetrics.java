package com.capitalone.dashboard.model.fourkeymetrics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class FourKeyMetrics {

    private Deployments deployments;
    private List<DeployedCommit> deployedCommits;

    public FourKeyMetrics(Deployments deployments, Commits commits) {
        this(deployments, getDeployedCommits(deployments, commits));
    }

    public FourKeyMetrics(Deployments deployments, List<DeployedCommit> deployedCommits) {
        this.deployments = deployments;
        this.deployedCommits = deployedCommits;
    }

    public long deploymentFrequency() {
        return deployments.uniqueCountByCommit();
    }

    public LeadTimeMetrics leadTime() {
        return new LeadTimeMetrics(deployedCommits);
    }

    private static List<DeployedCommit> getDeployedCommits(Deployments deployments, Commits commits) {
        List<DeployedCommit> deployedCommits = new ArrayList<>();
        Deployment latestDeployment = deployments.latestSuccessful();
        if (latestDeployment == null) {
            deployedCommits = Collections.emptyList();
        } else {
            List<Commit> commitsWithDeployments = commits.commitsUntil(latestDeployment);
            Date deploymentTime = latestDeployment.timeDeployed();
            for (Commit commit :
                    commitsWithDeployments) {
                Deployment deployment = deployments.latestSuccessfulForCommit(commit);
                if (deployment != null) {
                    deploymentTime = deployment.timeDeployed();
                }
                deployedCommits.add(new DeployedCommit(commit, deploymentTime));
            }
        }
        return deployedCommits;
    }

    public double changeFailureRate() {
        return ((double)deployments.failureCount()) / (deployments.failureCount() + deployments.successCount());
    }
}
