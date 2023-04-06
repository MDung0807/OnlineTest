package com.iotstar.onlinetest.controllers.client;


import com.iotstar.onlinetest.DTOs.requests.TestRequest;
import com.iotstar.onlinetest.DTOs.responses.MessageResponse;
import com.iotstar.onlinetest.DTOs.responses.TestResponse;
import com.iotstar.onlinetest.services.test.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;



    @RequestMapping("/add")
    public ResponseEntity<?> createTest (@RequestBody TestRequest testRequest){
        testService.create(testRequest);
        return ResponseEntity.ok(new MessageResponse("Success"));
    }

    @GetMapping("/")
    public ResponseEntity<?> createTest (@RequestParam Long testId){
        TestResponse testResponse = testService.getById(testId);
        return new ResponseEntity<>(testResponse, HttpStatus.OK);
    }


}
