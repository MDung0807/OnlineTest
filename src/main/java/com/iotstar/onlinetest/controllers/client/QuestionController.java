package com.iotstar.onlinetest.controllers.client;

import com.iotstar.onlinetest.DTOs.requests.QuestionRequest;
import com.iotstar.onlinetest.services.question.QuestionService;
import com.iotstar.onlinetest.utils.AuthUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private AuthUtils authUtils;

    @RequestMapping("/add")
    public ResponseEntity<?> addQuestion(@ModelAttribute QuestionRequest questionRequest){
        Long userId = authUtils.getAccountDetail().getUserId();
        questionService.createQuestion(questionRequest, userId);
        return ResponseEntity.ok("success");
    }
}
