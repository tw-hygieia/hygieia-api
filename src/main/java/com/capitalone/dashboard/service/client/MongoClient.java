package com.capitalone.dashboard.service.client;

import com.capitalone.dashboard.model.fourkeymetrics.Commits;
import com.capitalone.dashboard.model.fourkeymetrics.Deployment;
import com.capitalone.dashboard.model.fourkeymetrics.Deployments;
import com.capitalone.dashboard.model.fourkeymetrics.DurationWindow;
import org.springframework.stereotype.Component;

@Component
public class MongoClient implements CIClient {
    @Override
    public Deployments fetchDeployments(DurationWindow durationWindow) {
        return null;
    }

    @Override
    public Commits fetchCommits(DurationWindow durationWindow) {
        return null;
    }

    @Override
    public Deployments successfulDeploymentsPreceding(Deployment deployment) {
        return null;
    }

    @Override
    public Commits fetchCommitsBetween(String sha1, String sha2) {
        return null;
    }
}
