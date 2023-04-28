package com.iotstar.onlinetest.DTOs.requests;

import com.iotstar.onlinetest.models.Question;
import lombok.Data;

import java.util.List;

@Data
public class TestRequest {
    private Long testId;
    private String testName;
    private int time;
    private int quantity;
    private List<Long> questionIds;
    private List<Long> topicIds;
}
