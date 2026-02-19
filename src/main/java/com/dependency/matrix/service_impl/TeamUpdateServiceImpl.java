package com.dependency.matrix.service_impl;

import com.dependency.matrix.dto.TeamItemsResponse;
import com.dependency.matrix.dto.TeamUpdateRequest;
import com.dependency.matrix.entity.TeamUpdate;
import com.dependency.matrix.repository.TeamUpdateRepository;
import com.dependency.matrix.service.TeamUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamUpdateServiceImpl implements TeamUpdateService {

    private final TeamUpdateRepository repository;

    @Override
    public TeamUpdate createUpdate(
            TeamUpdateRequest request,
            String username
    ) {

        TeamUpdate update = TeamUpdate.builder()
                .projectName(request.getProjectName())
                .teamType(request.getTeamType())
                .teamName(request.getTeamName())
                .implementedItems(request.getImplementedItems())
                .postedBy(username)
                .createdAt(LocalDateTime.now())
                .build();

        return repository.save(update);
    }

    @Override
    public List<TeamItemsResponse> getItemsByProject(String projectName) {

        return repository
                .findByProjectName(projectName)
                .stream()
                .map(t -> new TeamItemsResponse(
                        t.getTeamName(),
                        t.getTeamType(),
                        t.getImplementedItems()
                ))
                .toList();
    }

}
