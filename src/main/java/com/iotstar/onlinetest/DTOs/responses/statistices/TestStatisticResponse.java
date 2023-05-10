package com.iotstar.onlinetest.DTOs.responses.statistices;

import com.iotstar.onlinetest.DTOs.responses.TestResponse;
import lombok.Data;

@Data
public class TestStatisticResponse {
    private Long testId;
    private String testName;
    private Long numberUserTest;
}
