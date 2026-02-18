package com.dependency.matrix.controller;

import com.dependency.matrix.dto.TeamDependencyRequest;
import com.dependency.matrix.entity.TeamDependencyMatrix;
import com.dependency.matrix.service.TeamDependencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/dependencies")
@RequiredArgsConstructor
public class TeamDependencyController {

    private final TeamDependencyService service;

    // ===============================
    // SAVE DEPENDENCY MATRIX
    // ===============================
    @PostMapping("/matrix")
    public TeamDependencyMatrix saveMatrix(
            @RequestBody TeamDependencyRequest request,
            Authentication auth) {

        String username = auth.getName(); // from JWT
        return service.saveMatrix(request, username);
    }

    // ===============================
    // REVERSE LOOKUP
    // Which teams consume this team
    // ===============================
    @GetMapping("/consumers/{teamName}")
    public List<String> getConsumers(
            @PathVariable String teamName) {

        return service.findConsumersOfTeam(teamName);
    }

    // ===============================
    // BUILD GRAPH VIEW
    // ===============================
    @GetMapping("/graph/{project}")
    public Map<String, Set<String>> graph(
            @PathVariable String project) {

        return service.buildGraph(project);
    }

    // ===============================
    // CYCLE CHECK
    // ===============================
    @GetMapping("/cycle/{project}")
    public boolean hasCycle(
            @PathVariable String project) {

        return service.hasCycle(project);
    }
}
