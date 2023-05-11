package com.iotstar.onlinetest.services.topic;

import com.iotstar.onlinetest.DTOs.requests.TopicRequest;
import com.iotstar.onlinetest.DTOs.responses.TopicResponse;
import com.iotstar.onlinetest.models.Topic;

import java.util.List;

public interface TopicService {
    void create(TopicRequest topicResponse);

    void del(Long topicId, Long userId);

    void update(TopicRequest topicResponse);

    List<TopicResponse> getAllBySubject(Long subjectId);

    Topic findTopicById(Long id);
}
