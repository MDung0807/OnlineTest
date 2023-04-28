package com.iotstar.onlinetest.DTOs.responses;


import com.iotstar.onlinetest.models.Answer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionResponse {
    private Long questionId;
    private String question;
    private String image;
    private List<AnswerResponse> answers;
}
