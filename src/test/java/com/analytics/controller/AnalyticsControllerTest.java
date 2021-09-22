package com.analytics.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.analytics.controller.input.AnalyticInput;
import com.analytics.controller.input.ResourceInput;
import com.analytics.repository.AnalyticItem;
import com.analytics.service.AnalyticsService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class AnalyticsControllerTest {

  private AnalyticInput analyticInput;
  private List<AnalyticItem> analyticItemList;
  private AnalyticItem analyticItem;

  @InjectMocks
  private AnalyticsController analyticsControllerUnderTest;

  @Mock
  private AnalyticsService mockAnalyticsService;

  @BeforeEach
  void setUp() {
    analyticInput = new AnalyticInput();
    analyticInput.setSiteUrl("sample.com");
    analyticInput.setTtfb(0.1);
    analyticInput.setFcp(0.15);
    analyticInput.setDomLoad(0.2);
    analyticInput.setWindowLoad(0.35);

    List<ResourceInput> resourceInputList = new ArrayList<>();
    analyticItem = new AnalyticItem("sample.com", 0.1, 0.15, 0.2,
        0.35, resourceInputList, new Date(1632331568976L));

    analyticItemList = new ArrayList<>();
    analyticItemList.add(analyticItem);
  }

  @Test
  void createAnalytic() {
    when(mockAnalyticsService.createAnalytic(analyticInput))
        .thenReturn(analyticItem);

    final ResponseEntity<AnalyticItem> responseEntity = analyticsControllerUnderTest
        .createAnalytic(analyticInput);

    AnalyticItem responseItem = responseEntity.getBody();
    Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    Assert.assertEquals("sample.com", responseItem.getSiteUrl());
    Assert.assertEquals(0.1, responseItem.getTtfb(), 0);
    Assert.assertEquals(0.15, responseItem.getFcp(), 0);
    Assert.assertEquals(0.2, responseItem.getDomLoad(), 0);
    Assert.assertEquals(0.35, responseItem.getWindowLoad(), 0);
  }

  @Test
  void getAnalytic() {

    when(mockAnalyticsService.getAnalytics(any(Long.class), any(Long.class)))
        .thenReturn(analyticItemList);

    final ResponseEntity<List<AnalyticItem>> responseEntity = analyticsControllerUnderTest
        .getAnalytic(1632331568976L, 1632331633403L);

    Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    Assert.assertEquals(1, responseEntity.getBody().size());
    AnalyticItem response = responseEntity.getBody().get(0);
    Assert.assertEquals("sample.com", response.getSiteUrl());
    Assert.assertEquals(0.1, response.getTtfb(), 0);
    Assert.assertEquals(0.15, response.getFcp(), 0);
    Assert.assertEquals(0.2, response.getDomLoad(), 0);
    Assert.assertEquals(0.35, response.getWindowLoad(), 0);
  }
}