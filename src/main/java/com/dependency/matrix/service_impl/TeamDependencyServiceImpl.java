package com.dependency.matrix.service_impl;

import com.dependency.matrix.dto.TeamDependencyRequest;
import com.dependency.matrix.entity.TeamDependencyMatrix;
import com.dependency.matrix.entity.TeamUpdate;
import com.dependency.matrix.repository.TeamDependencyMatrixRepository;
import com.dependency.matrix.repository.TeamUpdateRepository;
import com.dependency.matrix.service.TeamDependencyService;
import com.dependency.matrix.utils.TeamItemUsage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class TeamDependencyServiceImpl
        implements TeamDependencyService {

    private final TeamDependencyMatrixRepository depRepo;
    private final TeamUpdateRepository updateRepo;

    // ================= SAVE =================

    @Override
    public TeamDependencyMatrix saveMatrix(
            TeamDependencyRequest req,
            String username) {

        validateDependencies(req);

        TeamDependencyMatrix m =
                TeamDependencyMatrix.builder()
                        .projectName(req.getProjectName())
                        .consumerTeamName(req.getConsumerTeamName())
                        .consumerTeamType(req.getConsumerTeamType())
                        .dependencyMatrix(req.getDependencyMatrix())
                        .postedBy(username)
                        .createdAt(LocalDateTime.now())
                        .build();

        return depRepo.save(m);
    }

    // ================= VALIDATION =================

    private void validateDependencies(
            TeamDependencyRequest req) {

        for (List<TeamItemUsage> group :
                req.getDependencyMatrix()) {

            for (TeamItemUsage usage : group) {

                var team = usage.getTeam();

                TeamUpdate provider =
                        updateRepo
                                .findByProjectNameAndTeamNameAndTeamType(
                                        req.getProjectName(),
                                        team.getTeamName(),
                                        team.getTeamType())
                                .orElseThrow(() ->
                                        new RuntimeException(
                                                "Provider team not found"));

                if (!provider.getImplementedItems()
                        .contains(
                                usage.getImplementedItemUsed())) {

                    throw new RuntimeException(
                            "Item not implemented by provider: "
                                    + usage.getImplementedItemUsed());
                }
            }
        }
    }

    // ================= REVERSE LOOKUP =================

    public List<String> findConsumersOfTeam(
            String teamName) {

        List<String> result = new ArrayList<>();

        for (var d : depRepo.findAll()) {

            boolean used =
                    d.getDependencyMatrix()
                            .stream()
                            .flatMap(List::stream)
                            .anyMatch(u ->
                                    u.getTeam().getTeamName()
                                            .equals(teamName));

            if (used)
                result.add(d.getConsumerTeamName());
        }

        return result;
    }

    // ================= GRAPH =================

    public Map<String, Set<String>>
    buildGraph(String project) {

        Map<String, Set<String>> g = new HashMap<>();

        for (var d :
                depRepo.findByProjectName(project)) {

            for (var group :
                    d.getDependencyMatrix()) {

                for (var u : group) {

                    g.computeIfAbsent(
                                    d.getConsumerTeamName(),
                                    k -> new HashSet<>())
                            .add(u.getTeam()
                                    .getTeamName());
                }
            }
        }
        return g;
    }

    // ================= CYCLE =================

    public boolean hasCycle(String project) {
        return detect(buildGraph(project));
    }

    private boolean detect(
            Map<String,Set<String>> g) {

        Set<String> visiting = new HashSet<>();
        Set<String> done = new HashSet<>();

        for (String n : g.keySet())
            if (dfs(n,g,visiting,done))
                return true;

        return false;
    }

    private boolean dfs(String n,
                        Map<String,Set<String>> g,
                        Set<String> v,
                        Set<String> d) {

        if (d.contains(n)) return false;
        if (!v.add(n)) return true;

        for (String x :
                g.getOrDefault(n,Set.of()))
            if (dfs(x,g,v,d))
                return true;

        v.remove(n);
        d.add(n);
        return false;
    }
}
