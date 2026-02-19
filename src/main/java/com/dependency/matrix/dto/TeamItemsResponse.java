package com.dependency.matrix.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class TeamItemsResponse {

    private String teamName;
    private String teamType;
    private List<String> implementedItems;
}
