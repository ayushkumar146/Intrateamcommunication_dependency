package com.dependency.matrix.service;

import com.dependency.matrix.dto.TeamItemsResponse;
import com.dependency.matrix.dto.TeamUpdateRequest;
import com.dependency.matrix.entity.TeamUpdate;

import java.util.List;
import java.util.Optional;

public interface TeamUpdateService {

    TeamUpdate createUpdate(
            TeamUpdateRequest request,
            String username
    );
    List<TeamItemsResponse> getItemsByProject(String projectName);

}

