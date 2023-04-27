package com.iotstar.onlinetest.services.subject.topic;

import com.iotstar.onlinetest.DTOs.requests.TopicRequest;
import com.iotstar.onlinetest.DTOs.responses.TopicResponse;
import com.iotstar.onlinetest.models.Topic;

import java.util.List;

public interface TopicService {
    public void create(TopicRequest topicResponse);

    void del(Long topicId, Long userId);

    public void update(TopicRequest topicResponse);

    public List<TopicResponse> getAllBySubject(Long subjectId);

    public Topic findTopicById(Long id);
}
