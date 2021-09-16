package com.analytics.controller;

import com.analytics.controller.input.AnalyticInput;
import com.analytics.service.AnalyticsService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log
@RestController
@RequestMapping("/api")
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    @Autowired
    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @PostMapping("/analytic")
    public ResponseEntity createAnalytic(@RequestBody AnalyticInput analyticInput) {
        this.analyticsService.createAnalytic(analyticInput);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
