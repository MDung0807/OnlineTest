package com.iotstar.onlinetest.controllers.client;

import com.iotstar.onlinetest.DTOs.requests.SubjectRequest;
import com.iotstar.onlinetest.DTOs.requests.TopicRequest;
import com.iotstar.onlinetest.DTOs.responses.MessageResponse;
import com.iotstar.onlinetest.DTOs.responses.SubjectResponse;
import com.iotstar.onlinetest.security.services.AccountDetailsImpl;
import com.iotstar.onlinetest.services.subject.SubjectService;
import com.iotstar.onlinetest.services.subject.topic.TopicService;
import com.iotstar.onlinetest.utils.AuthUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/subject")
public class SubjectController {
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private TopicService topicService;
    @Autowired
    private AuthUtils authUtils;
    @Autowired
    private ModelMapper mapper;
    private SubjectResponse subjectResponse;
    @RequestMapping("/add/subject")

    public ResponseEntity<?> addSubject(@RequestBody SubjectRequest subjectRequest){
        Long userId = authUtils.getAccountDetail().getUserId();
        subjectService.createSubject(subjectRequest, userId);
        return ResponseEntity.ok(new MessageResponse("successfully"));
    }

    @RequestMapping("/add/topic")
    public ResponseEntity<?> addTopic (@RequestBody TopicRequest topicRequest){
        Long userId = authUtils.getAccountDetail().getUserId();
        if (subjectService.existByUserId(topicRequest.getSubjectId(), userId)){
            topicService.create(topicRequest);
            return ResponseEntity.ok( new MessageResponse("Topic is created success"));
        }

        return ResponseEntity.ok( new MessageResponse("Teacher is denied"));
    }
}
