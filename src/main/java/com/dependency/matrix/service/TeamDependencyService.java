package com.dependency.matrix.service;

import com.dependency.matrix.dto.TeamDependencyRequest;
import com.dependency.matrix.entity.TeamDependencyMatrix;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface TeamDependencyService {

    TeamDependencyMatrix saveMatrix(
            TeamDependencyRequest request,
            String username
    );
    List<String> findConsumersOfTeam(String teamName);

    Map<String, Set<String>> buildGraph(String project);

    boolean hasCycle(String project);
}
