package com.analytics.controller.input;

import lombok.Data;

import java.util.List;

@Data
public class AnalyticInput {
    String siteId;
    double ttfb;
    double fcp;
    double domLoad;
    double windowLoad;
    List<ResourceInput> resourceMetrics;
}
