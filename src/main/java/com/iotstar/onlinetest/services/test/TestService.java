package com.iotstar.onlinetest.services.test;

import com.iotstar.onlinetest.DTOs.requests.TestRequest;
import com.iotstar.onlinetest.DTOs.responses.TestResponse;
import com.iotstar.onlinetest.models.Test;

import java.util.List;

public interface TestService {
    Test create(TestRequest testRequest);
    TestResponse getById(Long id);
    List<TestResponse> getByTopicId(Long topicId);


}
