package com.analytics.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import com.analytics.controller.input.AnalyticInput;
import com.analytics.controller.input.ResourceInput;
import com.analytics.repository.AnalyticItem;
import com.analytics.repository.AnalyticsRepository;
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
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.domain.Sort;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class AnalyticsServiceTest {

  private AnalyticInput analyticInput;
  private List<AnalyticItem> analyticItemList;
  private AnalyticItem analyticItem;

  @InjectMocks
  private AnalyticsService analyticsServiceUnderTest;

  @Mock
  private AnalyticsRepository mockAnalyticsRepository;

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
    when(mockAnalyticsRepository.save(any(AnalyticItem.class)))
        .thenReturn(analyticItem);

    final AnalyticItem response = analyticsServiceUnderTest
        .createAnalytic(analyticInput);

    Assert.assertEquals("sample.com", response.getSiteUrl());
    Assert.assertEquals(0.1, response.getTtfb(), 0);
    Assert.assertEquals(0.15, response.getFcp(), 0);
    Assert.assertEquals(0.2, response.getDomLoad(), 0);
    Assert.assertEquals(0.35, response.getWindowLoad(), 0);
  }

  @Test
  void getAnalytics() {
    when(mockAnalyticsRepository
        .findAll(any(Date.class), any(Date.class), eq(Sort.by(Sort.Direction.ASC, "createDate"))))
        .thenReturn(analyticItemList);

    final List<AnalyticItem> response = analyticsServiceUnderTest
        .getAnalytics(null, null);

    Assert.assertEquals(1, response.size());
    AnalyticItem responseItem = response.get(0);
    Assert.assertEquals("sample.com", responseItem.getSiteUrl());
    Assert.assertEquals(0.1, responseItem.getTtfb(), 0);
    Assert.assertEquals(0.15, responseItem.getFcp(), 0);
    Assert.assertEquals(0.2, responseItem.getDomLoad(), 0);
    Assert.assertEquals(0.35, responseItem.getWindowLoad(), 0);
  }

  @Test
  void getAnalyticsWithDate() {
    when(mockAnalyticsRepository
        .findAll(any(Date.class), any(Date.class), eq(Sort.by(Sort.Direction.ASC, "createDate"))))
        .thenReturn(analyticItemList);

    final List<AnalyticItem> response = analyticsServiceUnderTest
        .getAnalytics(1632331568976L, 1632331633403L);

    Assert.assertEquals(1, response.size());
    AnalyticItem responseItem = response.get(0);
    Assert.assertEquals("sample.com", responseItem.getSiteUrl());
    Assert.assertEquals(0.1, responseItem.getTtfb(), 0);
    Assert.assertEquals(0.15, responseItem.getFcp(), 0);
    Assert.assertEquals(0.2, responseItem.getDomLoad(), 0);
    Assert.assertEquals(0.35, responseItem.getWindowLoad(), 0);
  }
}