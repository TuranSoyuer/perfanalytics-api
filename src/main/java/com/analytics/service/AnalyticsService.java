package com.analytics.service;

import com.analytics.controller.input.AnalyticInput;
import com.analytics.repository.AnalyticItem;
import com.analytics.repository.AnalyticsRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Log
@Service
public class AnalyticsService {

    private final AnalyticsRepository analyticsRepository;

    @Autowired
    public AnalyticsService(AnalyticsRepository analyticsRepository) {
        this.analyticsRepository = analyticsRepository;
    }

    public void createAnalytic(AnalyticInput input) {
        log.info("Analytic input:" + input.toString());

        AnalyticItem analyticItem = new AnalyticItem(input.getSiteUrl(), input.getTtfb(), input.getFcp(),
                input.getDomLoad(), input.getWindowLoad(), input.getResourceMetrics(), new Date());

        log.info("Analytic item:" + analyticItem.toString());
        this.analyticsRepository.save(analyticItem);
    }

}
