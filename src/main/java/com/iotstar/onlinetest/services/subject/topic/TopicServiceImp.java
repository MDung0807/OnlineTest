package com.iotstar.onlinetest.services.subject.topic;

import com.iotstar.onlinetest.DTOs.requests.TopicRequest;
import com.iotstar.onlinetest.DTOs.responses.TopicResponse;
import com.iotstar.onlinetest.exceptions.ResourceNotFoundException;
import com.iotstar.onlinetest.models.Subject;
import com.iotstar.onlinetest.models.Topic;
import com.iotstar.onlinetest.repositories.subject.SubjectDAO;
import com.iotstar.onlinetest.repositories.subject.TopicDAO;
import com.iotstar.onlinetest.services.user.UserService;
import com.iotstar.onlinetest.utils.AppConstant;
import com.iotstar.onlinetest.utils.FileUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
    @Autowired
    private UserService userService;

    @Autowired
    private FileUtils fileUtils;
    private Topic topic;
    private Subject subject;

    private String uploadImage(MultipartFile fileImage, String fileName){
        String urlImage = null;
        if(fileImage !=  null){
            urlImage = fileUtils.upload(fileImage, AppConstant.IMG_NAME_TOPIC+fileName);
        }
        return urlImage;
    }

    private Topic uploadImage(MultipartFile fileInput, Topic topic){
        topic.setImage(uploadImage(fileInput, topic.getName()));
        return topicDAO.save(topic);
    }
    @Override
    public void create(TopicRequest topicRequest) {

        subject =subjectDAO.findById(topicRequest.getSubjectId()).orElseThrow(()->
                new ResourceNotFoundException(AppConstant.SUBJECT_NOTFOUND+topicRequest.getSubjectId()));

        mapper.typeMap(TopicRequest.class, Topic.class).addMappings(mapper -> mapper.skip(Topic::setImage));
        topic = mapper.map(topicRequest, Topic.class);


        topic.setSubject(subject);
        topic.setStatus(1);
        topic =topicDAO.save(topic);
        if(topicRequest.getImage()!=null){
            topic = uploadImage(topicRequest.getImage(), topic);
        }
    }


    @Override
    public void del(Long topicId, Long userId) {
        topic = topicDAO.findById(topicId).orElseThrow(()->
                new ResourceNotFoundException(AppConstant.TOPIC_NOTFOUND+topicId));
        if (!userService.existsSubjectById(1L, topic.getSubject().getSubjectId()))
            throw new AccessDeniedException(AppConstant.ACCESS_DENIED);
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

    @Override
    public Topic findTopicById(Long id){
        return topicDAO.findById(id).orElseThrow(()->
                new ResourceNotFoundException(AppConstant.TOPIC_NOTFOUND+id));
    }
}
