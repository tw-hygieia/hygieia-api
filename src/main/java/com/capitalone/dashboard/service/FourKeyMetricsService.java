package com.capitalone.dashboard.service;

import com.capitalone.dashboard.model.PipelineCommit;
import com.capitalone.dashboard.model.fourkeymetrics.Commit;
import com.capitalone.dashboard.model.fourkeymetrics.DeployedCommit;
import com.capitalone.dashboard.model.fourkeymetrics.Deployments;
import com.capitalone.dashboard.model.fourkeymetrics.FourKeyMetrics;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FourKeyMetricsService {

    private PipelineService pipelineService;

    @Autowired
    public FourKeyMetricsService(PipelineService pipelineService) {
        this.pipelineService = pipelineService;
    }

    public FourKeyMetrics metrics() {
        Collection<PipelineCommit> pipelineCommits = pipelineService
                .fetchProdPipelineCommits(new ObjectId("5f15d63e4e169aa2d266045f"));

        List<DeployedCommit> deployedCommits = pipelineCommits.stream().map(pc -> {
            String[] parentCommits = new String[pc.getScmParentRevisionNumbers().size()];
            pc.getScmParentRevisionNumbers().toArray(parentCommits);
            return new DeployedCommit(new Commit(pc.getScmRevisionNumber(), new Date(pc.getScmCommitTimestamp()), parentCommits),
                    new Date(pc.getTimestamp()));
        }).collect(Collectors.toList());
        return new FourKeyMetrics(new Deployments(Collections.emptyList()), deployedCommits);
    }
}
