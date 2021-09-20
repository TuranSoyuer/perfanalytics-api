package com.analytics.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Date;
import java.util.List;

public interface AnalyticsRepository extends MongoRepository<AnalyticItem, String> {

    @Query("{ 'createDate' : { $gte: ?0, $lte: ?1 } }")
    List<AnalyticItem> findAll(Date startDate, Date endDate);
}
