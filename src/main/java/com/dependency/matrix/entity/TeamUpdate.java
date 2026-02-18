package com.dependency.matrix.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "team_updates")
public class TeamUpdate {

    @Id
    private String id;

    private String projectName;
    private String teamType;
    private String teamName;

    private List<String> implementedItems;

    private String postedBy;
    private LocalDateTime createdAt;
}

