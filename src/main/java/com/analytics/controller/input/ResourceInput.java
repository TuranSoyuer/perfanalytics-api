package com.analytics.controller.input;

import lombok.Data;

@Data
public class ResourceInput {
    String name;
    double duration;
    String initiatorType;
    double transferSize;
}
