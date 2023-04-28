package com.iotstar.onlinetest.services.test;

import com.iotstar.onlinetest.DTOs.requests.TestRequest;
import com.iotstar.onlinetest.DTOs.responses.TestResponse;
import com.iotstar.onlinetest.exceptions.ResourceNotFoundException;
import com.iotstar.onlinetest.models.Question;
import com.iotstar.onlinetest.models.Test;
import com.iotstar.onlinetest.models.Topic;
import com.iotstar.onlinetest.repositories.TestDAO;
import com.iotstar.onlinetest.services.question.QuestionService;
import com.iotstar.onlinetest.services.subject.topic.TopicService;
import com.iotstar.onlinetest.utils.AppConstant;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TestServiceImp implements TestService{

    @Autowired
    private TestDAO testDAO;
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private TopicService topicService;

    @Autowired
    private QuestionService questionService;

    private Test test;

    public Test getTestReturnTest(Long testId){
        return testDAO.findById(testId).orElseThrow(()->
                new ResourceNotFoundException(AppConstant.TEST_NOTFOUND+testId));
    }
    @Override
    public Test create(TestRequest testRequest) {
        List<Question> questions = new ArrayList<>();
        List<Topic> topics = new ArrayList<>();

        //Get question
        for (Long i: testRequest.getQuestionIds())
            questions.add(questionService.findById(i));

        //Get topic
        for (Long i: testRequest.getTopicIds())
            topics.add(topicService.findTopicById(i));

        test = mapper.map(testRequest, Test.class);
        test.setDateCreate(LocalDateTime.now());
        test.setStatus(1);
        test.setTopics(topics);
        test.setQuestions(questions);

        test = testDAO.save(test);
        return null;
    }

    @Override
    public TestResponse getById(Long testId){
        test = testDAO.findById(testId).orElseThrow(()->
                new ResourceNotFoundException(AppConstant.TEST_NOTFOUND+testId));

        TestResponse testResponse = mapper.map(test, TestResponse.class);

        return testResponse;
    }

    @Override
    public List<TestResponse> getByTopicId(Long topicId){
        Topic topic = topicService.findTopicById(topicId);
        List<Topic> topics = new ArrayList<>();
        topics.add(topic);
       List<Test> tests= testDAO.findByTopics(topic);
        List<TestResponse> responses = new ArrayList<>();
        for (Test i: tests){
            responses.add(mapper.map(i, TestResponse.class));
        }
        return responses;
    }
}
