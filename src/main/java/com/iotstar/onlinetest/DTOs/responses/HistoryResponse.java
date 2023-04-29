package com.iotstar.onlinetest.DTOs.responses;

import com.iotstar.onlinetest.models.HisItem;
import com.iotstar.onlinetest.models.Question;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class HistoryResponse {
    private Long id;
    private Long userId;
    private Long testId;
//    private List<QuestionResponse> questionResponses;
    private String score;
//    private List<Long> answers;
    private LocalDateTime time;
    private List<HisItem> hisItems;
}
