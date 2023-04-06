package com.iotstar.onlinetest.DTOs.responses;

import com.iotstar.onlinetest.models.Question;
import com.iotstar.onlinetest.models.Topic;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class TestResponse {
    private Long testId;
    private LocalDateTime dateCreate;
    private int quantity;
    private int status;
    private String testName;
    private int time;
    private List<QuestionResponse>questions;
}
