package com.iotstar.onlinetest.DTOs.responses;


import com.iotstar.onlinetest.models.Answer;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class QuestionResponse {
    private Long questionId;
    private String question;
    private String image;
    private int status;
    private List<Answer> answers;

}
