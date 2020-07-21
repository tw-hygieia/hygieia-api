package com.capitalone.dashboard.rest;

import com.capitalone.dashboard.model.DeployedCommitResponse;
import com.capitalone.dashboard.model.FourKeyMetricsResponse;
import com.capitalone.dashboard.model.LeadTimeResponse;
import com.capitalone.dashboard.model.fourkeymetrics.FourKeyMetrics;
import com.capitalone.dashboard.model.fourkeymetrics.LeadTimeMetrics;
import com.capitalone.dashboard.service.FourKeyMetricsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class FourKeyMetricsController {
    private final FourKeyMetricsService fourKeyMetricsService;

    @Autowired
    public FourKeyMetricsController(FourKeyMetricsService fourKeyMetricsService) {
        this.fourKeyMetricsService = fourKeyMetricsService;
    }

    @RequestMapping(value = "/fourkeymetrics", method = GET, produces = APPLICATION_JSON_VALUE)
    public FourKeyMetricsResponse get() {
        FourKeyMetrics metrics = fourKeyMetricsService.metrics();
        return generateFourKeyMetricsResponse(metrics);
    }

    private FourKeyMetricsResponse generateFourKeyMetricsResponse(FourKeyMetrics fourKeyMetrics) {
        LeadTimeMetrics leadTimeMetrics = fourKeyMetrics.leadTime();
        List<DeployedCommitResponse> deployedCommitResponses = leadTimeMetrics.getDeployedCommits().stream()
                .map(dc -> new DeployedCommitResponse(dc.getCommitSha(), dc.getDeploymentTime(), dc.getCommitedTime(), dc.getLeadTime()))
                .collect(Collectors.toList());

        return new FourKeyMetricsResponse(
                new LeadTimeResponse(leadTimeMetrics.mean(), deployedCommitResponses));
    }

}
