package com.dependency.matrix.configs;

import com.mongodb.client.MongoClient;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoConnectionConfig {

    private final MongoClient mongoClient;

    public MongoConnectionConfig(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    @PostConstruct
    public void checkMongoConnection() {
        System.out.println("checked");
        try {
            mongoClient.listDatabaseNames().first(); // ping Mongo
            System.out.println("✅ MongoDB is CONNECTED");
        } catch (Exception e) {
            System.out.println("❌ MongoDB is NOT CONNECTED: " + e.getMessage());
        }
    }
}

