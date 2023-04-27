package com.iotstar.onlinetest.controllers.client;

import com.iotstar.onlinetest.DTOs.requests.SubjectRequest;
import com.iotstar.onlinetest.DTOs.requests.TopicRequest;
import com.iotstar.onlinetest.DTOs.responses.MessageResponse;
import com.iotstar.onlinetest.DTOs.responses.Response;
import com.iotstar.onlinetest.DTOs.responses.SubjectResponse;
import com.iotstar.onlinetest.exceptions.ResourceExistException;
import com.iotstar.onlinetest.security.services.AccountDetailsImpl;
import com.iotstar.onlinetest.services.subject.SubjectService;
import com.iotstar.onlinetest.services.subject.topic.TopicService;
import com.iotstar.onlinetest.services.user.UserService;
import com.iotstar.onlinetest.utils.AppConstant;
import com.iotstar.onlinetest.utils.AuthUtils;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/subject")
public class SubjectController {
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private TopicService topicService;
    @Autowired
    private UserService userService;
    @Autowired
    private AuthUtils authUtils;
    @Autowired
    private ModelMapper mapper;

    @RequestMapping("/add/subject")
    @PreAuthorize("hasRole(@environment.getProperty('ROLE_TEACHER'))")
    public ResponseEntity<?> addSubject(@Valid @ModelAttribute SubjectRequest subjectRequest){
        Long userId = authUtils.getAccountDetail().getUserId();
        //Check user created the subject
        if (userService.existsSubject(userId))
            throw new ResourceExistException(AppConstant.USER_HAVEN_SUBJECT);
        subjectService.createSubject(subjectRequest, userId);
        return ResponseEntity.ok(
                new Response(false,
                        new MessageResponse(AppConstant.SUCCESS))
        );
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

    @GetMapping("/del")
    public ResponseEntity<Response> delSubject(@RequestParam Long subjectId){
        Long userId = authUtils.getAccountDetail().getUserId();
        if (userService.existsSubjectById(userId, subjectId)){
            subjectService.delSubject(subjectId);
            return new ResponseEntity<>(
                    new Response(false, new MessageResponse(AppConstant.SUCCESS)),
                    HttpStatus.OK
            );
        }
        throw new AccessDeniedException(AppConstant.ACCESS_DENIED);

    }
}
