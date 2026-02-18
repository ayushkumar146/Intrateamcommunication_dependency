package com.dependency.matrix.service;

import com.dependency.matrix.dto.TeamUpdateRequest;
import com.dependency.matrix.entity.TeamUpdate;

public interface TeamUpdateService {

    TeamUpdate createUpdate(
            TeamUpdateRequest request,
            String username
    );
}
