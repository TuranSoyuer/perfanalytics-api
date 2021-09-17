package com.analytics.repository;

import lombok.Data;

@Data
public class ResourceItem {
    String name;
    double duration;
    String initiatorType;
    double transferSize;
}
