package com.iotstar.onlinetest.controllers.client.questionEndpoint;

import com.iotstar.onlinetest.DTOs.requests.QuestionImageRequest;
import com.iotstar.onlinetest.DTOs.requests.QuestionRequest;
import com.iotstar.onlinetest.DTOs.responses.MessageResponse;
import com.iotstar.onlinetest.DTOs.responses.QuestionResponse;
import com.iotstar.onlinetest.DTOs.responses.Response;
import com.iotstar.onlinetest.services.question.QuestionService;
import com.iotstar.onlinetest.utils.AppConstant;
import com.iotstar.onlinetest.utils.AuthUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

//@CrossOrigin
@RestController
public class QuestionController implements IQuestionController{
    @Autowired
    private QuestionService questionService;
    @Autowired
    private AuthUtils authUtils;

    @Override
    public ResponseEntity<Response> getQuestionByTopic(Long topicId){
        List<QuestionResponse> questionResponses = questionService.getQuestionByTopicId(topicId);
        return new ResponseEntity<>(
                new Response(false, questionResponses),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response> getQuestionByUser(){
        Long userId= authUtils.getAccountDetail().getUserId();
        List<QuestionResponse> questionResponses = questionService.getQuestionByUserId(userId);
        return new ResponseEntity<>(
                new Response(false, questionResponses),
                HttpStatus.OK
        );
    }
    @Override
    public ResponseEntity<Response> addQuestion(QuestionRequest param1, MultipartFile image, QuestionRequest param2){
        QuestionRequest questionRequest;
        if (param2 == null){
            questionRequest = param1;
        }
        else{
            questionRequest = param2;
            questionRequest.setImage(image);
        }
        Long userId = authUtils.getAccountDetail().getUserId();
        questionService.createQuestion(questionRequest, userId);
        return new ResponseEntity<>(
                new Response(false, new MessageResponse(AppConstant.SUCCESS)),
                HttpStatus.OK
        );
    }

    @Override
    public ResponseEntity<?> addImg( @Valid @ModelAttribute QuestionImageRequest questionImageRequest){
        questionService.updateImg(questionImageRequest);
        return ResponseEntity.ok(new Response(false, new MessageResponse(AppConstant.SUCCESS)));
    }
}
