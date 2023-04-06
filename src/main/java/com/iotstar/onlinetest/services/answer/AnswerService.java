package com.iotstar.onlinetest.services.answer;

import com.iotstar.onlinetest.DTOs.AnswerDTO;
import com.iotstar.onlinetest.models.Question;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AnswerService {
    public void createAnswers(List<AnswerDTO> answerDTOs, Question question);
}