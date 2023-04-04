package com.iotstar.onlinetest.DTOs.requests;

import com.iotstar.onlinetest.DTOs.AnswerDTO;
import com.iotstar.onlinetest.models.Answer;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@AllArgsConstructor
public class QuestionRequest {
    private Long questionId;
    private String question;
    private MultipartFile image;
    private int status;
    private Long topicId;
    private Long userId;
    private List<AnswerDTO> answerDTOs;
}
