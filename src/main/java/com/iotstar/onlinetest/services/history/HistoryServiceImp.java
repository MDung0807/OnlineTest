package com.iotstar.onlinetest.services.history;

import com.iotstar.onlinetest.DTOs.requests.HistoryRequest;
import com.iotstar.onlinetest.DTOs.responses.HistoryResponse;
import com.iotstar.onlinetest.exceptions.ResourceNotFoundException;
import com.iotstar.onlinetest.models.*;
import com.iotstar.onlinetest.repositories.HistoryDAO;
import com.iotstar.onlinetest.services.answer.AnswerServiceImp;
import com.iotstar.onlinetest.services.question.QuestionServiceImp;
import com.iotstar.onlinetest.services.test.TestServiceImp;
import com.iotstar.onlinetest.services.user.UserService;
import com.iotstar.onlinetest.services.user.UserServiceImp;
import com.iotstar.onlinetest.utils.AppConstant;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class HistoryServiceImp implements HistoryService{
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private HistoryDAO historyDAO;
    @Autowired
    private UserServiceImp userServiceImp;
    @Autowired
    private TestServiceImp testServiceImp;
    @Autowired
    private QuestionServiceImp questionServiceImp;
    @Autowired
    private AnswerServiceImp answerServiceImp;

    private History history;
    @Override
    public HistoryResponse getHistoryByUserId(Long userId, Long testId) {
        User user = userServiceImp.getUserReturnUser(userId);
        Test test = testServiceImp.getTestReturnTest(testId);
        history = historyDAO.findByTestAndUser(test, user).orElseThrow(()->
                new ResourceNotFoundException(AppConstant.NOT_FOUND));
        return mapper.map(history, HistoryResponse.class);
    }

    @Override
    @Transactional
    public String setHistoryByUserId(HistoryRequest request) {
        List<Question> questions = new ArrayList<>();
        List<Answer> answers = new ArrayList<>();
        int size = request.getQuestionIds().size();
        int count = 0;
        for (int i=0; i< size; i++){
            answers.add(
                    i,
                    answerServiceImp.getAnswerReturnAnswer(request.getAnswerIds().get(i))
            );
            questions.add(
                    i,
                    questionServiceImp.getQuestionReturnQuestion(request.getQuestionIds().get(i))
            );
        }

        for(int i = 0; i<size; i++){
            for (Answer answer: questions.get(i).getAnswers()){
                if (answer.isCorrect())
                    if (Objects.equals(answer.getAnswerId(), answers.get(i).getAnswerId()))
                        count ++;
            }
        }
        history = new History();
        history.setUser(userServiceImp.getUserReturnUser(request.getUserId()));
        history.setTest(testServiceImp.getTestReturnTest(request.getTestId()));
        history.setTime(LocalDateTime.now());
//        history.setQuestion(questions);
//        history.setAnswer(answers);
        historyDAO.save(history);
        return String.valueOf(count)+ "/" + String.valueOf(size);
    }
}
