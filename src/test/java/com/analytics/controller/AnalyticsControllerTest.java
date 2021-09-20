package com.analytics.controller;

import com.analytics.controller.input.AnalyticFilterInput;
import com.analytics.controller.input.AnalyticInput;
import com.analytics.controller.input.ResourceInput;
import com.analytics.repository.AnalyticItem;
import com.analytics.service.AnalyticsService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AnalyticsControllerTest {

    @InjectMocks
    private AnalyticsController analyticsControllerUnderTest;

    @Mock
    private AnalyticsService mockAnalyticsService;

    @BeforeEach
    void setUp() {

    }

    @Test
    void createAnalytic() {
        AnalyticInput input = new AnalyticInput();
        input.setSiteUrl("sample.com");

        List<ResourceInput> resourceInputList = new ArrayList<>();
        AnalyticItem analyticItem = new AnalyticItem("sample.com", 0.1, 0.15, 0.2,
                0.35, resourceInputList, new Date(System.currentTimeMillis() - 1800 * 1000));
        when(mockAnalyticsService.createAnalytic(input))
                .thenReturn(analyticItem);

        final ResponseEntity<AnalyticItem> responseEntity = analyticsControllerUnderTest.createAnalytic(input);

        AnalyticItem responseItem = responseEntity.getBody();
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        Assert.assertEquals("sample.com", responseItem.getSiteUrl());
    }

    @Test
    void getAnalytic() {
        List<AnalyticItem> analyticItemList = new ArrayList<>();
        List<ResourceInput> resourceInputList = new ArrayList<>();
        AnalyticItem analyticItem = new AnalyticItem("sample.com", 0.1, 0.15, 0.2,
                0.35, resourceInputList, new Date(System.currentTimeMillis() - 1800 * 1000));
        analyticItemList.add(analyticItem);
        when(mockAnalyticsService.getAnalytics(any(AnalyticFilterInput.class)))
                .thenReturn(analyticItemList);

        final ResponseEntity<List<AnalyticItem>> responseEntity = analyticsControllerUnderTest.getAnalytic();
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assert.assertEquals(1, responseEntity.getBody().size());
        AnalyticItem item = responseEntity.getBody().get(0);
        Assert.assertEquals("sample.com", item.getSiteUrl());

    }
}