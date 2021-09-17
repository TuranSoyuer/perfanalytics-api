package com.analytics.service;

import com.analytics.controller.input.AnalyticFilterInput;
import com.analytics.controller.input.AnalyticInput;
import com.analytics.repository.AnalyticItem;
import com.analytics.repository.AnalyticsRepository;
import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Filters;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Log
@Service
public class AnalyticsService {

    private final AnalyticsRepository analyticsRepository;

    @Autowired
    public AnalyticsService(AnalyticsRepository analyticsRepository) {
        this.analyticsRepository = analyticsRepository;
    }

    public void createAnalytic(AnalyticInput input) {
        AnalyticItem analyticItem = new AnalyticItem(input.getSiteUrl(), input.getTtfb(), input.getFcp(),
                input.getDomLoad(), input.getWindowLoad(), input.getResourceMetrics(), new Date());

//        log.info("Analytic item:" + analyticItem.toString());
        this.analyticsRepository.save(analyticItem);
    }

    public List<AnalyticItem> getAnalytics(AnalyticFilterInput filterInput) {
        return analyticsRepository.findAll(filterInput.getStartDate());

    }

}
