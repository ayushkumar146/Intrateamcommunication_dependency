package com.dependency.matrix.repository;

import com.dependency.matrix.entity.TeamUpdate;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TeamUpdateRepository
        extends MongoRepository<TeamUpdate, String> {
    Optional<TeamUpdate>
    findByProjectNameAndTeamNameAndTeamType(
            String projectName,
            String teamName,
            String teamType);

}
