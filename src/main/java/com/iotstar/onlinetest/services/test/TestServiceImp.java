package com.iotstar.onlinetest.services.test;

import com.iotstar.onlinetest.DTOs.requests.TestRequest;
import com.iotstar.onlinetest.DTOs.responses.TestResponse;
import com.iotstar.onlinetest.exceptions.DeprecatedException;
import com.iotstar.onlinetest.exceptions.ResourceNotFoundException;
import com.iotstar.onlinetest.exceptions.UnKnownException;
import com.iotstar.onlinetest.models.Question;
import com.iotstar.onlinetest.models.Test;
import com.iotstar.onlinetest.models.Topic;
import com.iotstar.onlinetest.repositories.TestDAO;
import com.iotstar.onlinetest.services.question.QuestionService;
import com.iotstar.onlinetest.services.question.QuestionServiceImp;
import com.iotstar.onlinetest.services.topic.TopicService;
import com.iotstar.onlinetest.services.user.UserServiceImp;
import com.iotstar.onlinetest.statval.ETest;
import com.iotstar.onlinetest.utils.AppConstant;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class TestServiceImp extends TestPaging implements TestService{

    @Autowired
    private TestDAO testDAO;
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private TopicService topicService;
    @Autowired
    private QuestionServiceImp questionServiceImp;
    @Autowired
    private UserServiceImp userServiceImp;

    private Test test;

    public void deleteTestInTopic(Long topicId){
        List<Test> tests = getTestsByTopicId(topicId);
        for(Test i: tests){
            delTest(test);
        }
    }

    public Test getTestReturnTest(Long testId){
        return testDAO.findById(testId).orElseThrow(()->
                new ResourceNotFoundException(ETest.TEST_NOTFOUND.getDes(testId)));
    }
    public List<Question> randomQuestion(List<Question>questionsSelected, List<Question> questions, int quantity){
        if (quantity> questions.size())
            throw new UnKnownException(ETest.NOT_ENOUGH_QUESTION.getDes());
        Random random = new Random();
        int index = 0;
        while(quantity > questionsSelected.size()){
            questions.removeAll(questionsSelected);
            index = random.nextInt(questions.size());
            questionsSelected.add(questions.get(index));
        }
        return questionsSelected;
    }

    private List<Question> createQuestionInTest(int quantity, List<Long>topicIds, List<Long>questionIds){
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
    @Transactional
    public Test create(TestRequest testRequest) {
        List<Topic> topics = new ArrayList<>();
        //Get topic
        int count= 0;
        for (Long i: testRequest.getTopicIds()){
            topics.add(topicService.findTopicById(i));
            if (topics.get(0).getSubject().getSubjectId()!= topics.get(count++).getSubject().getSubjectId())
                throw new UnKnownException(AppConstant.USER_ERRORS);
        }
        List<Question> questions = createQuestionInTest(
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
        test = getTestReturnTest(testId);
        if (test.getStatus()== 0){
            throw new DeprecatedException(ETest.TEST_IS_DEPRECATED.getDes());
        }
        return mapper.map(test, TestResponse.class);
    }

    @Override
    public List<TestResponse> getByTopicId(Long topicId){
       List<Test> tests= getTestsByTopicId(topicId);
        List<TestResponse> responses = new ArrayList<>();
        for (Test i: tests){
            if(i.getStatus()!=0)
                responses.add(mapper.map(i, TestResponse.class));
        }
        return responses;
    }

    public List<Test> getTestsByTopicId(Long topicId){
        Topic topic = topicService.findTopicById(topicId);
        return testDAO.findByTopics(topic, pageable());
    }

    public void delTest(Test request) {
        request.setStatus(0);
        testDAO.save(test);
    }

    @Override
    public void delTest(Long userId, Long testId){
        test = getTestReturnTest(testId);
        testDAO.save(test);
        if (!userServiceImp.existsSubjectById(userId, test.getTopics().get(0).getSubject().getSubjectId()))
            throw new AccessDeniedException(AppConstant.ACCESS_DENIED);
        delTest(test);
    }
}
