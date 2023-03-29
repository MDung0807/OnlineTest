package com.iotstar.onlinetest.controllers.client;

import com.iotstar.onlinetest.DTOs.requests.SubjectRequest;
import com.iotstar.onlinetest.DTOs.requests.TopicRequest;
import com.iotstar.onlinetest.DTOs.responses.MessageResponse;
import com.iotstar.onlinetest.DTOs.responses.SubjectResponse;
import com.iotstar.onlinetest.DTOs.responses.TopicResponse;
import com.iotstar.onlinetest.models.Subject;
import com.iotstar.onlinetest.models.User;
import com.iotstar.onlinetest.security.services.AccountDetailsImpl;
import com.iotstar.onlinetest.services.subject.SubjectService;
import com.iotstar.onlinetest.services.subject.topic.TopicService;
import org.aspectj.bridge.Message;
import org.hibernate.annotations.Subselect;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;

@RestController
@RequestMapping("/subject")
public class SubjectController {
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private TopicService topicService;
    @Autowired
    private ModelMapper mapper;
    private SubjectResponse subjectResponse;
    @RequestMapping("/add/subject")

    public ResponseEntity<?> addSubject(@RequestBody SubjectRequest subjectRequest){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AccountDetailsImpl accountDetails = mapper.map(authentication.getPrincipal(), AccountDetailsImpl.class);
        Long userId = accountDetails.getUserId();
        subjectService.createSubject(subjectRequest, userId);
        return ResponseEntity.ok(new MessageResponse("successfully"));
    }

    @RequestMapping("/add/topic")
    public ResponseEntity<?> addTopic (@RequestBody TopicRequest topicRequest){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AccountDetailsImpl accountDetails = mapper.map(authentication.getPrincipal(), AccountDetailsImpl.class);
        Long userId = accountDetails.getUserId();
        if (subjectService.existByUserId(topicRequest.getSubjectId(), userId)){
            topicService.create(topicRequest);
            return ResponseEntity.ok( new MessageResponse("Topic is created success"));
        }

        return ResponseEntity.ok( new MessageResponse("Teacher is denied"));
    }
}
