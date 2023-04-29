package com.iotstar.onlinetest.services.test;

import com.iotstar.onlinetest.DTOs.requests.TestRequest;
import com.iotstar.onlinetest.DTOs.responses.TestResponse;
import com.iotstar.onlinetest.exceptions.ResourceNotFoundException;
import com.iotstar.onlinetest.exceptions.UnKnownException;
import com.iotstar.onlinetest.models.Question;
import com.iotstar.onlinetest.models.Test;
import com.iotstar.onlinetest.models.Topic;
import com.iotstar.onlinetest.repositories.TestDAO;
import com.iotstar.onlinetest.services.question.QuestionService;
import com.iotstar.onlinetest.services.question.QuestionServiceImp;
import com.iotstar.onlinetest.services.subject.topic.TopicService;
import com.iotstar.onlinetest.utils.AppConstant;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
    @Autowired
    private QuestionServiceImp questionServiceImp;

    private Test test;

    public Test getTestReturnTest(Long testId){
        return testDAO.findById(testId).orElseThrow(()->
                new ResourceNotFoundException(AppConstant.TEST_NOTFOUND+testId));
    }
    public List<Question> randomQuestion(List<Question>questionsSelected, List<Question> questions, int quantity){
        if (quantity> questions.size())
            throw new UnKnownException(AppConstant.NOT_ENOUGH_QUESTION);
        Random random = new Random();
        int index = 0;
        while(quantity > questionsSelected.size()){
            questions.removeAll(questionsSelected);
            index = random.nextInt(questions.size());
            questionsSelected.add(questions.get(index));
        }
        return questionsSelected;
    }

    private List<Question> getQuestionInTest(int quantity, List<Long>topicIds, List<Long>questionIds){
        List<Question> questionsSelected = new ArrayList<>();
        List<Question> questions = new ArrayList<>();
        Question question;
        if (questionIds == null){
            questionIds = new ArrayList<>();
        }

        for (Long i: questionIds){
            questionsSelected.add(questionServiceImp.getQuestionReturnQuestion(i));
        }

        if (quantity < questionsSelected.size())
            throw new UnKnownException(AppConstant.USER_ERRORS);

        for (Long i: topicIds){
            questions.addAll(questionServiceImp.getQuestionsByTopicReturnList(i));
        }
        questionsSelected = randomQuestion(questionsSelected, questions, quantity);
        return questionsSelected;
    }
    @Override
    public Test create(TestRequest testRequest) {
        List<Topic> topics = new ArrayList<>();
        //Get topic
        int count= 0;
        for (Long i: testRequest.getTopicIds()){
            topics.add(topicService.findTopicById(i));
            if (topics.get(0).getSubject().getSubjectId()!= topics.get(count++).getSubject().getSubjectId())
                throw new UnKnownException(AppConstant.USER_ERRORS);
        }
        List<Question> questions = getQuestionInTest(
                testRequest.getQuantity(),
                testRequest.getTopicIds(),
                testRequest.getQuestionIds());

        test = mapper.map(testRequest, Test.class);
        test.setDateCreate(LocalDateTime.now());
        test.setStatus(1);
        test.setTopics(topics);
        test.setQuestions(questions);


        return testDAO.save(test);
    }

    @Override
    public TestResponse getById(Long testId){
        test = testDAO.findById(testId).orElseThrow(()->
                new ResourceNotFoundException(AppConstant.TEST_NOTFOUND+testId));

        return mapper.map(test, TestResponse.class);
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
