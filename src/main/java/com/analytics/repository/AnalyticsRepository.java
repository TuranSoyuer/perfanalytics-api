package com.analytics.repository;

import com.analytics.controller.input.AnalyticInput;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AnalyticsRepository extends MongoRepository<AnalyticInput, String> {
}
