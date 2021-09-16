package com.analytics.controller;

import com.analytics.controller.input.AnalyticInput;
import lombok.extern.java.Log;
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

    @PostMapping("/analytic")
    public ResponseEntity createAnalytic(@RequestBody AnalyticInput analyticInput) {
        log.info("createAnalytic HERE:" + analyticInput.toString());
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
