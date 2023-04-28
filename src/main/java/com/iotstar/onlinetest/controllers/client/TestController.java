package com.iotstar.onlinetest.controllers.client;


import com.iotstar.onlinetest.DTOs.requests.TestRequest;
import com.iotstar.onlinetest.DTOs.responses.MessageResponse;
import com.iotstar.onlinetest.DTOs.responses.Response;
import com.iotstar.onlinetest.DTOs.responses.TestResponse;
import com.iotstar.onlinetest.services.test.TestService;
import com.iotstar.onlinetest.utils.AppConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/test")

public class TestController {

    @Autowired
    private TestService testService;

    @PreAuthorize("hasRole(@environment.getProperty('ROLE_TEACHER'))")
    @PostMapping("/add")
    public ResponseEntity<?> createTest (@RequestBody TestRequest testRequest){
        testService.create(testRequest);
        return ResponseEntity.ok(
                new Response(false, new MessageResponse( AppConstant.SUCCESS))
        );
    }

    @GetMapping({"/", ""})
    public ResponseEntity<?> getTest (@RequestParam Long testId){
        TestResponse testResponse = testService.getById(testId);
        return new ResponseEntity<>(
                new Response(false, testResponse),
                HttpStatus.OK);
    }

    @GetMapping("/testInTopic")
    public ResponseEntity<Response> getTestInTopic(@RequestParam("topicId") Long topicId){
        List<TestResponse> testResponses = testService.getByTopicId(topicId);
        return new ResponseEntity<>(
                new Response(false, testResponses),
                HttpStatus.OK
        );
    }

}
