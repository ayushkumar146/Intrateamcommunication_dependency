package com.dependency.matrix.repository;

import com.dependency.matrix.entity.TeamDependencyMatrix;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TeamDependencyMatrixRepository
        extends MongoRepository<TeamDependencyMatrix, String> {
    List<TeamDependencyMatrix>
    findByProjectName(String projectName);
}
