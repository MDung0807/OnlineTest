package com.iotstar.onlinetest.controllers.client;

import com.iotstar.onlinetest.DTOs.requests.SubjectRequest;
import com.iotstar.onlinetest.DTOs.requests.TopicRequest;
import com.iotstar.onlinetest.DTOs.responses.MessageResponse;
import com.iotstar.onlinetest.DTOs.responses.SubjectResponse;
import com.iotstar.onlinetest.security.services.AccountDetailsImpl;
import com.iotstar.onlinetest.services.subject.SubjectService;
import com.iotstar.onlinetest.services.subject.topic.TopicService;
import com.iotstar.onlinetest.utils.AuthUtils;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @RequestMapping("/add/subject")
    @PreAuthorize("hasRole(@environment.getProperty('ROLE_TEACHER'))")
    public ResponseEntity<?> addSubject(@Valid @ModelAttribute SubjectRequest subjectRequest){
        Long userId = authUtils.getAccountDetail().getUserId();
        subjectService.createSubject(subjectRequest, userId);
        return ResponseEntity.ok(new MessageResponse("successfully"));
    }

    @RequestMapping("/add/topic")
    @PreAuthorize("hasRole(@environment.getProperty('ROLE_TEACHER'))")
    public ResponseEntity<?> addTopic (@RequestBody TopicRequest topicRequest){
        Long userId = authUtils.getAccountDetail().getUserId();
        if (subjectService.existByUserId(topicRequest.getSubjectId(), userId)){
            topicService.create(topicRequest);
            return ResponseEntity.ok( new MessageResponse("Topic is created success"));
        }

        return ResponseEntity.ok( new MessageResponse("Teacher is denied"));
    }

    @GetMapping("/id")
    public ResponseEntity<SubjectResponse> getSubject (@RequestParam Long idSubject){
        SubjectResponse subjectResponse = subjectService.getSubject(idSubject);
        return ResponseEntity.ok(subjectResponse);
    }

    @GetMapping({"/", ""})
    public ResponseEntity<List<SubjectResponse>> getAllSubject(){
        List<SubjectResponse> subjectResponses = subjectService.getAllSubject();
        return ResponseEntity.ok(subjectResponses);
    }
}
