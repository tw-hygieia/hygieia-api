package com.capitalone.dashboard.service;

import com.capitalone.dashboard.model.fourkeymetrics.*;
import com.capitalone.dashboard.service.client.CIClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FourKeyMetricsService {

    private CIClient ciClient;

    @Autowired
    public FourKeyMetricsService(CIClient ciClient) {
        this.ciClient = ciClient;
    }

    public FourKeyMetrics metrics(DurationWindow durationWindow) {
        Deployments deployments = ciClient.fetchDeployments(durationWindow);
        Commits commits = ciClient.fetchCommits(durationWindow);
        if (deployments.successCount() == 0) {
            return new FourKeyMetrics(deployments, commits);
        }

        Deployment earliestDeployment = deployments.earliestSuccessful();
        Deployment latestDeploymentOutsideWindow = ciClient.successfulDeploymentsPreceding(earliestDeployment)
                .latestSuccessful();
        Commits commitsOutsideWindow = ciClient.fetchCommitsBetween(latestDeploymentOutsideWindow.getCommitSha(),
                earliestDeployment.getCommitSha());

        Commits allCommits = commits.concat(commitsOutsideWindow);
        FourKeyMetrics fourKeyMetrics = new FourKeyMetrics(deployments, allCommits);
        return fourKeyMetrics;
    }
}
