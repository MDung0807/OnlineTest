package com.iotstar.onlinetest.DTOs.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.iotstar.onlinetest.models.HisItem;
import com.iotstar.onlinetest.models.Question;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class HistoryResponse {
    private Long hisId;
    private Long userId;
    private Long testId;
//    private List<QuestionResponse> questionResponses;
    private String score;
    private String totalCorrect;
//    private List<Long> answers;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime time;
    private List<HisItem> hisItems;
}
