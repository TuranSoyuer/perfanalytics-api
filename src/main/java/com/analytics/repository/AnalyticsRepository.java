package com.analytics.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface AnalyticsRepository extends MongoRepository<AnalyticItem, String> {
}
