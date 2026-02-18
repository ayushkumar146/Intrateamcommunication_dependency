package com.dependency.matrix.repository;

import com.dependency.matrix.entity.TeamUpdate;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TeamUpdateRepository
        extends MongoRepository<TeamUpdate, String> {
}
