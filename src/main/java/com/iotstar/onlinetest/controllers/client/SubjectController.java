package com.iotstar.onlinetest.controllers.client;

import com.iotstar.onlinetest.DTOs.requests.SubjectRequest;
import com.iotstar.onlinetest.DTOs.requests.TopicRequest;
import com.iotstar.onlinetest.DTOs.responses.MessageResponse;
import com.iotstar.onlinetest.DTOs.responses.Response;
import com.iotstar.onlinetest.DTOs.responses.SubjectResponse;
import com.iotstar.onlinetest.security.services.AccountDetailsImpl;
import com.iotstar.onlinetest.services.subject.SubjectService;
import com.iotstar.onlinetest.services.subject.topic.TopicService;
import com.iotstar.onlinetest.utils.AppConstant;
import com.iotstar.onlinetest.utils.AuthUtils;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
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
        return ResponseEntity.ok(
                new Response(false,
                        new MessageResponse(AppConstant.SUCCESS))
        );
    }

    @RequestMapping("/add/topic")
    @PreAuthorize("hasRole(@environment.getProperty('ROLE_TEACHER'))")
    public ResponseEntity<?> addTopic (@RequestBody TopicRequest topicRequest){
        Long userId = authUtils.getAccountDetail().getUserId();
        if (subjectService.existByUserId(topicRequest.getSubjectId(), userId)){
            topicService.create(topicRequest);
            return ResponseEntity.ok(
                    new Response(false,
                            new MessageResponse(AppConstant.SUCCESS))
            );
        }

         throw new AccessDeniedException(AppConstant.ACCESS_DENIED);
    }

    @GetMapping("/id")
    public ResponseEntity<Response> getSubject (@RequestParam Long subjectId){
        SubjectResponse subjectResponse = subjectService.getSubject(subjectId);
        return ResponseEntity.ok(
                new Response(false, subjectResponse)
        );
    }

    @GetMapping({"/", ""})
    public ResponseEntity<Response> getAllSubject(){
        List<SubjectResponse> subjectResponses = subjectService.getAllSubject();
        return ResponseEntity.ok(
                new Response(false, subjectResponses)
        );
    }

    @GetMapping("/topicid")
    public ResponseEntity<?> getTopicBySubjectId(@RequestParam Long subjectId){
        SubjectResponse subjectResponse = subjectService.getSubject(subjectId);
        return ResponseEntity.ok(
                new Response(false, subjectResponse.getTopics())
        );
    }
}
