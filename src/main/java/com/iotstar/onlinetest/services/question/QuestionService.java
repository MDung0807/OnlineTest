package com.iotstar.onlinetest.services.question;

import com.iotstar.onlinetest.DTOs.requests.QuestionImageRequest;
import com.iotstar.onlinetest.DTOs.requests.QuestionRequest;
import com.iotstar.onlinetest.DTOs.responses.QuestionResponse;
import com.iotstar.onlinetest.models.Question;

import java.util.List;
import java.util.Optional;

public interface QuestionService {

    public QuestionResponse getQuestionById(Long id);

    public List<QuestionResponse> getQuestionByTopicId(Long id);
    public List<QuestionResponse> getQuestionByUserId(Long id);

    public Question createQuestion(QuestionRequest questionRequest, Long userId);

    public Question updateImg(QuestionImageRequest questionImageRequest);

    public Question findById(Long id);
}
