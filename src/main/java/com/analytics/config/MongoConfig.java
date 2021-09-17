//package com.analytics.config;
//
//import com.mongodb.ConnectionString;
//import com.mongodb.MongoClientSettings;
//import com.mongodb.client.MongoClient;
//import com.mongodb.client.MongoClients;
//import lombok.extern.java.Log;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
//import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
//
//import java.util.Collection;
//import java.util.Collections;
//
//@Log
//@EnableMongoRepositories(basePackages = "com.analytics.repository")
//@Configuration
//public class MongoConfig extends AbstractMongoClientConfiguration {
//
//    @Override
//    protected String getDatabaseName() {
//        return "analyticDatabase";
//    }
//
//    @Override
//    public MongoClient mongoClient() {
//        ConnectionString connectionString = new ConnectionString(System.getenv("MONGODB_URI"));
//        log.info("Turan ENV :" + System.getenv("MONGODB_URI") );
//        log.info("Turan Mongo :" + connectionString);
//        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
//                .applyConnectionString(connectionString)
//                .build();
//
//        return MongoClients.create(mongoClientSettings);
//    }
//
//    @Override
//    public Collection getMappingBasePackages() {
//        return Collections.singleton("com.analytics");
//    }
//}
