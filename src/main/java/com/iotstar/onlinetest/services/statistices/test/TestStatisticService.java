package com.iotstar.onlinetest.services.statistices.test;

import com.iotstar.onlinetest.DTOs.responses.statistices.TestStatisticResponse;

import java.util.List;

public interface TestStatisticService {
    List<TestStatisticResponse> getTestStatistic();
}
