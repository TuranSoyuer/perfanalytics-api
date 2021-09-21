package com.analytics.service;

import com.analytics.controller.input.AnalyticInput;
import com.analytics.repository.AnalyticItem;
import com.analytics.repository.AnalyticsRepository;
import com.analytics.service.input.AnalyticFilterInput;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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

    public AnalyticItem createAnalytic(AnalyticInput input) {
        AnalyticItem analyticItem = new AnalyticItem(input.getSiteUrl(), input.getTtfb(), input.getFcp(),
                input.getDomLoad(), input.getWindowLoad(), input.getResourceMetrics(), new Date());
;
        return analyticsRepository.save(analyticItem);
    }

    public List<AnalyticItem> getAnalytics(Long startDate, Long endDate) {
        AnalyticFilterInput filterInput = new AnalyticFilterInput();
        if (startDate == null || endDate == null) {
            filterInput.setStartDate(new Date(System.currentTimeMillis() - 1800 * 1000));
            filterInput.setEndDate(new Date(System.currentTimeMillis()));
        } else {
            filterInput.setStartDate(new Date(startDate));
            filterInput.setEndDate(new Date(endDate));
        }

        return analyticsRepository.findAll(filterInput.getStartDate(), filterInput.getEndDate(),
                Sort.by(Sort.Direction.ASC, "createDate"));

    }

}
