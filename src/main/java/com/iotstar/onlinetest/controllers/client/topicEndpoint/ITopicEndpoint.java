package com.iotstar.onlinetest.controllers.client.topicEndpoint;

import com.iotstar.onlinetest.DTOs.requests.TopicRequest;
import com.iotstar.onlinetest.DTOs.responses.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
@RequestMapping("/topic")
public interface ITopicEndpoint {
    @RequestMapping("/add")
    @PreAuthorize("hasRole(@environment.getProperty('ROLE_TEACHER'))")
    ResponseEntity<?> addTopic (@ModelAttribute TopicRequest param1,
                                       @ModelAttribute MultipartFile image,
                                       @RequestPart(value = "topic", required = false) TopicRequest param2);

    @GetMapping({"", "/"})
    ResponseEntity<?> getTopicBySubjectId(@RequestParam Long subjectId,
                                                 @RequestParam(required = false, defaultValue = "0") int index,
                                                 @RequestParam(required = false, defaultValue = "1") int size);

    @GetMapping("/del")
    ResponseEntity<Response> delTopicById(@RequestParam Long topicId);
}
