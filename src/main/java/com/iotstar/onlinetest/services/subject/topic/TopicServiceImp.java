package com.iotstar.onlinetest.services.subject.topic;

import com.iotstar.onlinetest.DTOs.requests.TopicRequest;
import com.iotstar.onlinetest.DTOs.responses.TopicResponse;
import com.iotstar.onlinetest.models.Subject;
import com.iotstar.onlinetest.models.Topic;
import com.iotstar.onlinetest.repositories.subject.SubjectDAO;
import com.iotstar.onlinetest.repositories.subject.TopicDAO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicServiceImp implements TopicService{
    @Autowired
    private TopicDAO topicDAO;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private SubjectDAO subjectDAO;
    private Topic topic;
    private Subject subject;

    @Override
    public void create(TopicRequest topicResponse) {
        topic = mapper.map(topicResponse, Topic.class);
        subject = subjectDAO.findById(topicResponse.getSubjectId()).get();
        topic.setSubject(subject);
        topicDAO.save(topic);
    }


    @Override
    public void del(Long id) {

    }

    @Override
    public void update(TopicRequest topicResponse) {

    }

    @Override
    public TopicResponse getAllBySubject(int subjectId) {
        return null;
    }
}
