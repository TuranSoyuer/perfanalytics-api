package com.analytics.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Date;
import java.util.List;

public interface AnalyticsRepository extends MongoRepository<AnalyticItem, String> {

    @Query("{ 'createDate' : { $gt: ?0 } }")
    List<AnalyticItem> findAll(Date fromDate);
}
