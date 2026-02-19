package com.dependency.matrix.controller;

import com.dependency.matrix.dto.TeamItemsResponse;
import com.dependency.matrix.dto.TeamUpdateRequest;
import com.dependency.matrix.entity.TeamUpdate;
import com.dependency.matrix.service.TeamUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/team")
@RequiredArgsConstructor
public class TeamUpdateController {

    private final TeamUpdateService service;

    @PostMapping("/post")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public TeamUpdate postUpdate(
            @RequestBody TeamUpdateRequest request,
            Authentication authentication
    ) {
        String username = authentication.getName();
        return service.createUpdate(request, username);
    }

    @GetMapping("/catalog")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public List<TeamItemsResponse> getCatalog(
            @RequestParam String projectName) {

        return service.getItemsByProject(projectName);
    }
}
