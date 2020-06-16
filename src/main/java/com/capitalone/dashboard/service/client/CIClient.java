package com.capitalone.dashboard.service.client;

import com.capitalone.dashboard.model.fourkeymetrics.Commits;
import com.capitalone.dashboard.model.fourkeymetrics.Deployments;
import com.capitalone.dashboard.model.fourkeymetrics.Deployment;
import com.capitalone.dashboard.model.fourkeymetrics.DurationWindow;
import org.springframework.stereotype.Service;

@Service
public interface CIClient {

    Deployments fetchDeployments(DurationWindow durationWindow);

    Commits fetchCommits(DurationWindow durationWindow);

    Deployments successfulDeploymentsPreceding(Deployment deployment);

    Commits fetchCommitsBetween(String sha1, String sha2);
}
