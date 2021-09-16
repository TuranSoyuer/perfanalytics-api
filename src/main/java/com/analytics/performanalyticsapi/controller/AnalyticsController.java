package com.analytics.performanalyticsapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/analytics-api")
public class AnalyticsController {

    @GetMapping("/greeting")
    public String greeting() {
        return "greeting";
    }
}
