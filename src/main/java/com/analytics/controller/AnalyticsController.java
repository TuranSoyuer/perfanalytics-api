package com.analytics.controller;

import com.analytics.controller.input.AnalyticInput;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AnalyticsController {

    @PostMapping("/analytic")
    public ResponseEntity createAnalytic(@RequestBody AnalyticInput analyticInput) {
        analyticInput.toString();


        return ResponseEntity.ok(HttpStatus.OK);
    }
}
