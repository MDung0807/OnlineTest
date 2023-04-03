package com.iotstar.onlinetest.services.subject.topic;

import com.iotstar.onlinetest.DTOs.requests.TopicRequest;
import com.iotstar.onlinetest.DTOs.responses.TopicResponse;
import com.iotstar.onlinetest.exceptions.ResourceNotFoundException;
import com.iotstar.onlinetest.models.Subject;
import com.iotstar.onlinetest.models.Topic;
import com.iotstar.onlinetest.repositories.subject.SubjectDAO;
import com.iotstar.onlinetest.repositories.subject.TopicDAO;
import com.iotstar.onlinetest.utils.AppConstant;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        subject = subjectDAO.findById(topicResponse.getSubjectId()).orElseThrow(()->
                new ResourceNotFoundException(AppConstant.SUBJECT_NOTFOUND+topicResponse.getSubjectId()));
        topic.setSubject(subject);
        topicDAO.save(topic);
    }


    @Override
    public void del(Long id) {
        topic = topicDAO.findById(id).orElseThrow(()->
                new ResourceNotFoundException(AppConstant.TOPIC_NOTFOUND+id));
        topic.setStatus(0);
        topic=topicDAO.save(topic);
    }

    @Override
    public void update(TopicRequest topicRequest) {
        topic = topicDAO.findById(topicRequest.getSubjectId()).orElseThrow(()->
                new ResourceNotFoundException(AppConstant.TOPIC_NOTFOUND+topicRequest.getId()));

        topic = mapper.map(topicRequest, Topic.class);
        topic.setStatus(1);
        topic=topicDAO.save(topic);
    }

    @Override
    public List<TopicResponse> getAllBySubject(Long subjectId) {
        subject = subjectDAO.findById(subjectId).orElseThrow(()->
                new ResourceNotFoundException(AppConstant.SUBJECT_NOTFOUND+subjectId));
        List<TopicResponse> topicResponses = new ArrayList<>();
        List<Topic> topics = subject.getTopics();
        for (Topic i: topics){
            topicResponses.add(mapper.map(i, TopicResponse.class));
        }
        return topicResponses;
    }
}
