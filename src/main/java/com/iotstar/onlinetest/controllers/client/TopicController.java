package com.iotstar.onlinetest.controllers.client;

import com.iotstar.onlinetest.DTOs.requests.TopicRequest;
import com.iotstar.onlinetest.DTOs.responses.MessageResponse;
import com.iotstar.onlinetest.DTOs.responses.Response;
import com.iotstar.onlinetest.DTOs.responses.SubjectResponse;
import com.iotstar.onlinetest.services.subject.SubjectService;
import com.iotstar.onlinetest.services.subject.topic.TopicService;
import com.iotstar.onlinetest.services.user.UserService;
import com.iotstar.onlinetest.utils.AppConstant;
import com.iotstar.onlinetest.utils.AuthUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/topic")
public class TopicController {

    @Autowired
    private SubjectService subjectService;
    @Autowired
    private TopicService topicService;
    @Autowired
    private UserService userService;
    @Autowired
    private AuthUtils authUtils;

    @RequestMapping("/add")
    @PreAuthorize("hasRole(@environment.getProperty('ROLE_TEACHER'))")
    public ResponseEntity<?> addTopic (@ModelAttribute TopicRequest param1,
                                       @ModelAttribute MultipartFile image,
                                       @RequestPart(value = "topic", required = false) TopicRequest param2){
        Long userId = authUtils.getAccountDetail().getUserId();
        TopicRequest topicRequest;
        if(param2 == null)
            topicRequest = param1;
        else
            topicRequest = param2;
        topicRequest.setImage(image);

        if (userService.existsSubjectById(userId, topicRequest.getSubjectId())){
            topicService.create(topicRequest);
            return ResponseEntity.ok(
                    new Response(false,
                            new MessageResponse(AppConstant.SUCCESS))
            );
        }

        throw new AccessDeniedException(AppConstant.ACCESS_DENIED);
    }


    @GetMapping({"", "/"})
    public ResponseEntity<?> getTopicBySubjectId(@RequestParam Long subjectId){
        SubjectResponse subjectResponse = subjectService.getSubject(subjectId);
        return ResponseEntity.ok(
                new Response(false, subjectResponse.getTopics())
        );
    }

    @GetMapping("/del")
    public ResponseEntity<Response> delTopicById(@RequestParam Long topicId){
        Long userId = authUtils.getAccountDetail().getUserId();
        topicService.del(topicId, userId);
        return new ResponseEntity<>(
                new Response(false, new MessageResponse(AppConstant.SUCCESS))
                , HttpStatus.OK);
    }
}
