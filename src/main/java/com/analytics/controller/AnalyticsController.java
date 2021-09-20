package com.analytics.controller;

import com.analytics.controller.input.AnalyticInput;
import com.analytics.repository.AnalyticItem;
import com.analytics.service.AnalyticsService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log
@RestController
@RequestMapping("/api")
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    @Autowired
    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @PostMapping("/analytics")
    public ResponseEntity<AnalyticItem> createAnalytic(@RequestBody AnalyticInput analyticInput) {
        AnalyticItem item = this.analyticsService.createAnalytic(analyticInput);
        return ResponseEntity.ok(item);
    }

    @GetMapping("/analytics")
    public ResponseEntity<List<AnalyticItem>> getAnalytic(
            @RequestParam(value = "startDate", required = false) Long startDate,
            @RequestParam(value = "endDate", required = false) Long endDate) {

        return ResponseEntity.ok(analyticsService.getAnalytics(startDate, endDate));
    }
}
