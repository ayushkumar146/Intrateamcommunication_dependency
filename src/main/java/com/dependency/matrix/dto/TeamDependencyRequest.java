package com.dependency.matrix.dto;


import com.dependency.matrix.utils.TeamItemUsage;
import lombok.Data;

import java.util.List;

@Data
public class TeamDependencyRequest {

    private String projectName;
    private String consumerTeamName;
    private String consumerTeamType;

    private List<List<TeamItemUsage>> dependencyMatrix;
}
