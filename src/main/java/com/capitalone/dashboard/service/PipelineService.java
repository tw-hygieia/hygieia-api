package com.capitalone.dashboard.service;

import com.capitalone.dashboard.model.PipelineCommit;
import com.capitalone.dashboard.model.PipelineResponse;
import com.capitalone.dashboard.request.PipelineSearchRequest;
import org.bson.types.ObjectId;

import java.util.Collection;

public interface PipelineService {

    /**
     * Retrieves all pipeline objects based on the provided search criteria.
     *
     * @param searchRequest search request
     * @return all pipelines for team dashboards
     */
    Iterable<PipelineResponse> search(PipelineSearchRequest searchRequest);

    Collection<PipelineCommit> fetchProdPipelineCommits(ObjectId collectorItemId);
}
