package com.iotstar.onlinetest.services.question;

import com.iotstar.onlinetest.DTOs.requests.QuestionRequest;
import com.iotstar.onlinetest.models.Question;

import java.util.Optional;

public interface QuestionService {
    public Question createQuestion(QuestionRequest questionRequest, Long userId);

    public Question findById(Long id);
}
