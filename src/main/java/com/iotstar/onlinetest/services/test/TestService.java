package com.iotstar.onlinetest.services.test;

import com.iotstar.onlinetest.DTOs.requests.TestRequest;
import com.iotstar.onlinetest.DTOs.responses.TestResponse;
import com.iotstar.onlinetest.models.Test;

import java.util.List;

public interface TestService {
    public Test create(TestRequest testRequest);
    public TestResponse getById(Long id);
    public List<TestResponse> getByTopicId(Long topicId);

}
