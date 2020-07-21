package com.capitalone.dashboard.model.fourkeymetrics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Commits {
    private List<Commit> commits;

    public Commits(List<Commit> commits) {
        this.commits = commits;
    }

    public List<Commit> commitsUntil(Deployment latest) {
        if (latest == null) {
            return Collections.emptyList();
        }
        int firstMatchingIndex = IntStream.range(0, commits.size())
                .filter(i -> commits.get(i).getSha().equalsIgnoreCase(latest.getCommitSha()))
                .findFirst()
                .orElse(-1);
        if (firstMatchingIndex == -1)
            return Collections.emptyList();
        return commits.subList(firstMatchingIndex + 1, commits.size());
    }

    public Commits concat(Commits commits) {
        ArrayList<Commit> underlyingCommits = new ArrayList<>(this.commits);
        underlyingCommits.addAll(commits.commits);
        return new Commits(underlyingCommits);
    }
}
