package com.iotstar.onlinetest.controllers.client.questionEndpoint;

import com.iotstar.onlinetest.DTOs.requests.QuestionImageRequest;
import com.iotstar.onlinetest.DTOs.requests.QuestionRequest;
import com.iotstar.onlinetest.DTOs.responses.Response;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/question")
//@PreAuthorize("hasRole(@environment.getProperty('ROLE_TEACHER'))")
public interface IQuestionController {
    @PreAuthorize("hasAnyRole({@environment.getProperty('ROLE_STUDENT'),@environment.getProperty('ROLE_TEACHER')})")
    @GetMapping("/inTopic")
    ResponseEntity<Response> getQuestionByTopic(@RequestParam Long topicId);

    @GetMapping("/inUser")
    ResponseEntity<Response> getQuestionByUser();

    @PostMapping(value = "/add")
    ResponseEntity<Response> addQuestion(@Valid @ModelAttribute QuestionRequest param1,
                                                @ModelAttribute MultipartFile image,
                                                @Valid @RequestPart(value = "question", required = false) QuestionRequest param2);

    @PostMapping("/addImg")
    public ResponseEntity<?> addImg( @Valid @ModelAttribute QuestionImageRequest questionImageRequest);
}
