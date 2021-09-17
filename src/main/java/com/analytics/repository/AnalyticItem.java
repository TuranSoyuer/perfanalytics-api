package com.analytics.repository;

import com.analytics.controller.input.ResourceInput;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document("analyticitems")
@Data
public class AnalyticItem {
    String siteUrl;
    double ttfb;
    double fcp;
    double domLoad;
    double windowLoad;
    List<ResourceInput> resourceMetrics;
    Date createDate;

    public AnalyticItem(String siteUrl, double ttfb, double fcp, double domLoad, double windowLoad, List<ResourceInput> resourceMetrics, Date createDate) {
        this.siteUrl = siteUrl;
        this.ttfb = ttfb;
        this.fcp = fcp;
        this.domLoad = domLoad;
        this.windowLoad = windowLoad;
        this.resourceMetrics = resourceMetrics;
        this.createDate = createDate;
    }
}
