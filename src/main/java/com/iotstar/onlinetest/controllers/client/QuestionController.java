package com.iotstar.onlinetest.controllers.client;

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

@CrossOrigin
@RestController
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private AuthUtils authUtils;

    @PreAuthorize("hasAnyRole({@environment.getProperty('ROLE_STUDENT'),@environment.getProperty('ROLE_TEACHER')})")
    @GetMapping("/inTopic")
    public ResponseEntity<Response> getQuestionByTopic(@RequestParam Long topicId){
        List<QuestionResponse> questionResponses = questionService.getQuestionByTopicId(topicId);
        return new ResponseEntity<>(
                new Response(false, questionResponses),
                HttpStatus.OK);
    }

    @GetMapping("/inUser")
    public ResponseEntity<Response> getQuestionByUser(){
        Long userId= authUtils.getAccountDetail().getUserId();
        List<QuestionResponse> questionResponses = questionService.getQuestionByUserId(userId);
        return new ResponseEntity<>(
                new Response(false, questionResponses),
                HttpStatus.OK
        );
    }
    @RequestMapping(value = "/add", produces = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> addQuestion(@Valid @ModelAttribute QuestionRequest param1,
                                         @ModelAttribute MultipartFile image,
                                         @Valid @RequestPart(value = "question", required = false) QuestionRequest param2){
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
        return ResponseEntity.ok(
                new Response(false,
                        new MessageResponse(AppConstant.SUCCESS))
        );
    }

    @RequestMapping("/addImg")
    public ResponseEntity<?> addImg( @Valid @ModelAttribute QuestionImageRequest questionImageRequest){
        questionService.updateImg(questionImageRequest);
        return ResponseEntity.ok(new Response(false, new MessageResponse(AppConstant.SUCCESS)));
    }
}
