package com.analytics.service.input;

import lombok.Data;

import java.util.Date;

@Data
public class AnalyticFilterInput {
    Date startDate;
    Date endDate;
}
