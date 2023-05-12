package com.iotstar.onlinetest.services.statistices.test;

import com.iotstar.onlinetest.DTOs.responses.statistices.TestStatisticResponse;
import com.iotstar.onlinetest.DTOs.responses.statistices.TestStatisticResponseUser;

import java.util.List;

public interface TestStatisticService {
    List<TestStatisticResponse> getTestStatistic();
    List<TestStatisticResponseUser> getUserFinishTest(Long testId);

}
