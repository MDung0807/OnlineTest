package com.iotstar.onlinetest.services.subject.topic;

import com.iotstar.onlinetest.DTOs.requests.TopicRequest;
import com.iotstar.onlinetest.DTOs.responses.TopicResponse;

public interface TopicService {
    public void create(TopicRequest topicResponse);

    public void del(Long id);

    public void update(TopicRequest topicResponse);

    public TopicResponse getAllBySubject(int subjectId);
}
