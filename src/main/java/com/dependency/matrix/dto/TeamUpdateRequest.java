package com.dependency.matrix.dto;

import lombok.Data;
import java.util.List;

@Data
public class TeamUpdateRequest {

    private String projectName;
    private String teamType;
    private String teamName;
    private List<String> implementedItems;
}
