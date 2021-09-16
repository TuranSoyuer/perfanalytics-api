package com.analytics.service;

import com.analytics.controller.input.AnalyticInput;
import com.analytics.repository.AnalyticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnalyticsService {

    private final AnalyticsRepository analyticsRepository;

    @Autowired
    public AnalyticsService(AnalyticsRepository analyticsRepository) {
        this.analyticsRepository = analyticsRepository;
    }

    public void createAnalytic(AnalyticInput analyticInput) {
        this.analyticsRepository.insert(analyticInput);
    }

}
