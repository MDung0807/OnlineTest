package com.iotstar.onlinetest.controllers.client;

import com.iotstar.onlinetest.DTOs.requests.QuestionImageRequest;
import com.iotstar.onlinetest.DTOs.requests.QuestionRequest;
import com.iotstar.onlinetest.DTOs.responses.MessageResponse;
import com.iotstar.onlinetest.DTOs.responses.QuestionResponse;
import com.iotstar.onlinetest.services.question.QuestionService;
import com.iotstar.onlinetest.utils.AuthUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/question")
@PreAuthorize("hasRole(@environment.getProperty('ROLE_TEACHER'))")
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private AuthUtils authUtils;


    @GetMapping("/inTopic")
    @PreAuthorize("hasRole('student')")
    public ResponseEntity<List<QuestionResponse>> getQuestionByTopic(@RequestParam Long topicId){
        List<QuestionResponse> questionResponses = questionService.getQuestionByTopicId(topicId);
        return new ResponseEntity<>(questionResponses, HttpStatus.OK);
    }

    @GetMapping("/inUser")
    public ResponseEntity<List<QuestionResponse>> getQuestionByUser(){
        Long userId= authUtils.getAccountDetail().getUserId();
        List<QuestionResponse> questionResponses = questionService.getQuestionByUserId(userId);
        return new ResponseEntity<>(questionResponses, HttpStatus.OK);
    }
    @RequestMapping("/add")
    public ResponseEntity<?> addQuestion(@Valid @RequestBody QuestionRequest questionRequest, BindingResult result){
        if (result.hasErrors()){
             result.getFieldError().getDefaultMessage();
        }
        Long userId = authUtils.getAccountDetail().getUserId();
        questionService.createQuestion(questionRequest, userId);
        return ResponseEntity.ok("success");
    }

    @RequestMapping("/addImg")
    public ResponseEntity<?> addImg(@RequestBody @Valid @ModelAttribute QuestionImageRequest questionImageRequest,
                                         BindingResult result){
        if (result.hasErrors()){
            return new ResponseEntity<>(HttpStatusCode.valueOf(400));
        }

        questionService.updateImg(questionImageRequest);
        return ResponseEntity.ok(new MessageResponse("Success"));
    }
}
