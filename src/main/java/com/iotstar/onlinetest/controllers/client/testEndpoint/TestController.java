package com.iotstar.onlinetest.controllers.client.testEndpoint;


import com.iotstar.onlinetest.DTOs.requests.TestRequest;
import com.iotstar.onlinetest.DTOs.responses.MessageResponse;
import com.iotstar.onlinetest.DTOs.responses.Response;
import com.iotstar.onlinetest.DTOs.responses.TestResponse;
import com.iotstar.onlinetest.services.test.TestPaging;
import com.iotstar.onlinetest.services.test.TestService;
import com.iotstar.onlinetest.utils.AppConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class TestController implements ITestEndpoint{

    @Autowired
    private TestService testService;
    @Autowired
    private TestPaging paging;

    @Override
    public ResponseEntity<?> createTest (TestRequest testRequest){
        testService.create(testRequest);
        return ResponseEntity.ok(
                new Response(false, new MessageResponse( AppConstant.SUCCESS))
        );
    }

    @Override
    public ResponseEntity<?> getTest (Long testId){
        TestResponse testResponse = testService.getById(testId);
        return new ResponseEntity<>(
                new Response(false, testResponse),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response> getTestInTopic(Long topicId, int index, int size) {
        paging.setPageSize(size);
        paging.setPageIndex(index);
        List<TestResponse> testResponses = testService.getByTopicId(topicId);
        return new ResponseEntity<>(
                new Response(false, testResponses),
                HttpStatus.OK
        );
    }

}
