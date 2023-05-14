package com.iotstar.onlinetest.services.topic;

import com.iotstar.onlinetest.DTOs.requests.TopicRequest;
import com.iotstar.onlinetest.DTOs.responses.TopicResponse;
import com.iotstar.onlinetest.common.paging.PagingRequest;
import com.iotstar.onlinetest.exceptions.DeprecatedException;
import com.iotstar.onlinetest.exceptions.ResourceNotFoundException;
import com.iotstar.onlinetest.models.Question;
import com.iotstar.onlinetest.models.Subject;
import com.iotstar.onlinetest.models.Topic;
import com.iotstar.onlinetest.repositories.TopicDAO;
import com.iotstar.onlinetest.services.subject.SubjectServiceImp;
import com.iotstar.onlinetest.services.test.TestServiceImp;
import com.iotstar.onlinetest.services.user.UserServiceImp;
import com.iotstar.onlinetest.statval.EQuestion;
import com.iotstar.onlinetest.statval.ETopic;
import com.iotstar.onlinetest.utils.AppConstant;
import com.iotstar.onlinetest.utils.FileUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TopicServiceImp extends PagingRequest implements TopicService{
    @Autowired
    private TopicDAO topicDAO;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private SubjectServiceImp subjectServiceImp;
    @Autowired
    private UserServiceImp userServiceImp;
    @Autowired
    @Lazy
    private TestServiceImp testServiceImp;

    @Autowired
    private FileUtils fileUtils;
    private Topic topic;
    private Subject subject;

    public Topic getTopicReturnTopic(Long topicId){
        return topicDAO.findById(topicId).orElseThrow(()->
                new ResourceNotFoundException(ETopic.TOPIC_NOTFOUND.getDes(topicId)));
    }

    public List<Question> getQuestionsInTopic(Long topicId){
        topic = getTopicReturnTopic(topicId);
        if (topic.getStatus()==0) {
            throw new DeprecatedException(ETopic.TOPIC_DEPRECATED.getDes());
        }
        return topic.getQuestions();
    }
    @Override
    @Transactional
    public void create(TopicRequest topicRequest) {

        subject =subjectServiceImp.getSubjectReturnSubject(topicRequest.getSubjectId());

        mapper.typeMap(TopicRequest.class, Topic.class).addMappings(mapper -> mapper.skip(Topic::setImage));
        topic = mapper.map(topicRequest, Topic.class);


        topic.setSubject(subject);
        topic.setStatus(1);
        topic =topicDAO.save(topic);
        topic.setImage(
                fileUtils.upload(topicRequest.getImage(),
                        ETopic.IMG_NAME_TOPIC.getDes(topic.getId())));
        topic = topicDAO.save(topic);
    }

    @Override
    public void del(Long topicId, Long userId) {
        topic = getTopicReturnTopic(topicId);
        if (!userServiceImp.existsSubjectById(userId, topic.getSubject().getSubjectId()))
            throw new AccessDeniedException(AppConstant.ACCESS_DENIED);
        topic.setStatus(0);
        topic=topicDAO.save(topic);
        testServiceImp.deleteTestInTopic(topicId);
    }

    @Override
    public void update(TopicRequest topicRequest) {
        topic = getTopicReturnTopic(topicRequest.getId());

        topic = mapper.map(topicRequest, Topic.class);
        topic.setStatus(1);
        topic=topicDAO.save(topic);
    }

    @Override
    public List<TopicResponse> getAllBySubject(Long subjectId) {
        subject = subjectServiceImp.getSubjectReturnSubject(subjectId);
        List<TopicResponse> topicResponses = new ArrayList<>();
        List<Topic> topics = topicDAO.findBySubject(subject, pageable());
        for (Topic i: topics){
            if (i.getStatus()!= 0)
                topicResponses.add(mapper.map(i, TopicResponse.class));
        }
        return topicResponses;
    }

    @Override
    public Topic findTopicById(Long id){
        return getTopicReturnTopic(id);
    }
}
