package com.iotstar.onlinetest.services.question;

import com.iotstar.onlinetest.DTOs.requests.QuestionImageRequest;
import com.iotstar.onlinetest.DTOs.requests.QuestionRequest;
import com.iotstar.onlinetest.DTOs.responses.QuestionResponse;
import com.iotstar.onlinetest.models.Question;

import java.util.List;
import java.util.Optional;

public interface QuestionService {

    QuestionResponse getQuestionById(Long id);

    List<QuestionResponse> getQuestionByTopicId(Long id);
    List<QuestionResponse> getQuestionByUserId(Long id);

    Question createQuestion(QuestionRequest questionRequest, Long userId);

    Question updateImg(QuestionImageRequest questionImageRequest);

    Question findById(Long id);
    void deleteQuestion(Long questionId);
}
