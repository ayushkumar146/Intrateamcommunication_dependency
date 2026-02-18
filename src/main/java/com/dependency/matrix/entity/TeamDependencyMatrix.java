package com.dependency.matrix.entity;

import com.dependency.matrix.utils.TeamItemUsage;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document("team_dependency_matrix")
public class TeamDependencyMatrix {

    @Id
    private String id;

    private String projectName;
    private String consumerTeamName;
    private String consumerTeamType;

    // List<List<pair<pair<string,string>,string>>>
    private List<List<TeamItemUsage>> dependencyMatrix;

    private String postedBy;
    private LocalDateTime createdAt;
}
