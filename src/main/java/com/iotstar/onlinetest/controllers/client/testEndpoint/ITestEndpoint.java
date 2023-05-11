package com.iotstar.onlinetest.controllers.client.testEndpoint;

import com.iotstar.onlinetest.DTOs.requests.TestRequest;
import com.iotstar.onlinetest.DTOs.responses.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/test")
public interface ITestEndpoint {
    @PreAuthorize("hasRole(@environment.getProperty('ROLE_TEACHER'))")
    @PostMapping("/add")
    ResponseEntity<?> createTest (@RequestBody TestRequest testRequest);

    @GetMapping({"/", ""})
    ResponseEntity<?> getTest (@RequestParam Long testId);

    @GetMapping("/testInTopic")
    ResponseEntity<Response> getTestInTopic(@RequestParam("topicId") Long topicId,
                                            @RequestParam(required = false, defaultValue = "0") int index,
                                            @RequestParam(required = false, defaultValue = "10") int size);

    @GetMapping({"/del"})
    ResponseEntity<?> delTest (@RequestParam Long testId);
}
