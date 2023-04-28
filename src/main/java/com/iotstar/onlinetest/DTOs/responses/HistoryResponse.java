package com.iotstar.onlinetest.DTOs.responses;

import com.iotstar.onlinetest.models.Question;
import lombok.Data;

import java.util.List;

@Data
public class HistoryResponse {
    private Long id;
    private Long userId;
    private Long topicId;
    private List<QuestionResponse> questionResponses;
    private List<Long> answers;
}
