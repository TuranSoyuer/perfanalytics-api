package com.analytics.controller;

import com.analytics.controller.input.AnalyticFilterInput;
import com.analytics.controller.input.AnalyticInput;
import com.analytics.repository.AnalyticItem;
import com.analytics.service.AnalyticsService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
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

    @CrossOrigin(origins = "https://performanceanalytics-app.herokuapp.com")
    @PostMapping("/analytics")
    public ResponseEntity createAnalytic(@RequestBody AnalyticInput analyticInput) {
        this.analyticsService.createAnalytic(analyticInput);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @CrossOrigin(origins = "https://performanceanalytics-app.herokuapp.com")
    @GetMapping("/analytics")
    public ResponseEntity<List<AnalyticItem>> getAnalytic() {
//        log.info("filter: " + filterInput.toString());

        Date date = new Date(System.currentTimeMillis() - 1800 * 1000);
        AnalyticFilterInput input = new AnalyticFilterInput();
        input.setStartDate(date);
        return ResponseEntity.ok(analyticsService.getAnalytics(input));
    }
}
