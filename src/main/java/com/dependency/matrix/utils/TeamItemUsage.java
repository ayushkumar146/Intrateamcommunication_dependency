package com.dependency.matrix.utils;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamItemUsage {

    private TeamRef team;
    private String implementedItemUsed;
}