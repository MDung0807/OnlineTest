package com.iotstar.onlinetest.DTOs.requests;

import lombok.Data;

import java.util.List;

@Data
public class HistoryRequest {
    private Long userId;
    private Long testId;
    private List<Long> questionIds;
    private List<Long> answerIds;
}
