package com.analytics.repository;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("analyticitems")
public class AnalyticItem {
    String siteUrl;
    double ttfb;

    public AnalyticItem(String siteUrl, double ttfb) {
        this.siteUrl = siteUrl;
        this.ttfb = ttfb;
    }
}
