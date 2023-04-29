package com.iotstar.onlinetest.DTOs.requests;

import com.iotstar.onlinetest.models.Question;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class TestRequest {
    private Long testId;
    @Size(max = 30, message = "Test name have length less then equal 30")
    private String testName;
    private int time;
    private int quantity;
    private List<Long> questionIds;
    private List<Long> topicIds;
}
