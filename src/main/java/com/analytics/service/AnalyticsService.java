package com.analytics.service;

import com.analytics.controller.input.AnalyticInput;
import com.analytics.repository.AnalyticItem;
import com.analytics.repository.AnalyticsRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log
@Service
public class AnalyticsService {

    private final AnalyticsRepository analyticsRepository;

    @Autowired
    public AnalyticsService(AnalyticsRepository analyticsRepository) {
        this.analyticsRepository = analyticsRepository;
    }

    public void createAnalytic(AnalyticInput analyticInput) {
        log.info(analyticInput.toString());
        AnalyticItem analyticItem = new AnalyticItem(analyticInput.getSiteUrl(), analyticInput.getTtfb());
        this.analyticsRepository.save(analyticItem);
    }

}
