package com.iotstar.onlinetest.services.history;

import com.iotstar.onlinetest.DTOs.requests.HistoryRequest;
import com.iotstar.onlinetest.DTOs.responses.HistoryResponse;
import com.iotstar.onlinetest.DTOs.responses.ScoreResponse;
import com.iotstar.onlinetest.exceptions.ResourceNotFoundException;
import com.iotstar.onlinetest.models.*;
import com.iotstar.onlinetest.repositories.HisItemDAO;
import com.iotstar.onlinetest.repositories.HistoryDAO;
import com.iotstar.onlinetest.services.answer.AnswerServiceImp;
import com.iotstar.onlinetest.services.question.QuestionServiceImp;
import com.iotstar.onlinetest.services.test.TestServiceImp;
import com.iotstar.onlinetest.services.user.UserService;
import com.iotstar.onlinetest.services.user.UserServiceImp;
import com.iotstar.onlinetest.statval.EHistory;
import com.iotstar.onlinetest.utils.AppConstant;
import com.iotstar.onlinetest.utils.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
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
    private HisItemDAO hisItemDAO;
    @Autowired
    private UserServiceImp userServiceImp;
    @Autowired
    private TestServiceImp testServiceImp;
    @Autowired
    private QuestionServiceImp questionServiceImp;
    @Autowired
    private AnswerServiceImp answerServiceImp;
    @Autowired
    private Converter converter;

    private History history;

    public List<History> getHistoryReturnHistory(Long userId, Long testId){
        User user = userServiceImp.getUserReturnUser(userId);
        Test test = testServiceImp.getTestReturnTest(testId);
        return historyDAO.findByTestAndUser(test, user).orElseThrow(()->
                new ResourceNotFoundException(AppConstant.NOT_FOUND));

    }

    public List<History> getHistoryReturnHistory(Long userId){
        User user = userServiceImp.getUserReturnUser(userId);
        return historyDAO.findByUser(user).orElseThrow(()->
                new ResourceNotFoundException(AppConstant.NOT_FOUND));

    }

    private List<HisItem> mapToHistoryItem(List<Long> questionIds, List<Long> answerIds){
        List<HisItem> hisItems = new ArrayList<>();
        HisItem hisItem;
        Long questionId, answerId;
        int size = questionIds.size();
        for (int i=0; i< size; i++){
            questionId = questionIds.get(i);
            answerId = answerIds.get(i);
            hisItem = new HisItem();
            if (answerId != 0)
                hisItem.setAnswer(answerServiceImp.getAnswerReturnAnswer(answerId));
            hisItem.setQuestion(questionServiceImp.getQuestionReturnQuestion(questionId));
            hisItems.add(hisItemDAO.save(hisItem));
        }
        return hisItems;
    }
    @Override
    public List<HistoryResponse> getHistoryByUserId(Long userId, Long testId) {
        List<HistoryResponse> historyResponses = new ArrayList<>();
        List<History> histories = getHistoryReturnHistory(userId, testId);
        for (History i: histories){
            historyResponses.add(mapper.map(i, HistoryResponse.class));
        }
        return historyResponses;
    }

    @Override
    @Transactional
    public String setHistoryByUserId(HistoryRequest request) {
        int count = 0, size = request.getQuestionIds().size();
        List<HisItem> hisItems = new ArrayList<>();
        hisItems = mapToHistoryItem(request.getQuestionIds(), request.getAnswerIds());
        for (HisItem i: hisItems){
            if (i.getAnswer()!= null)
                if(i.getAnswer().isCorrect())
                    count++;
        }
        String totalCorrect = String.valueOf(count)+ "/" + String.valueOf(size);
        float score = returnScore(size, count);
        history = new History();
        history.setUser(userServiceImp.getUserReturnUser(request.getUserId()));
        history.setTest(testServiceImp.getTestReturnTest(request.getTestId()));
        history.setHisItems(hisItems);
        history.setTime(LocalDateTime.now());
        history.setTotalCorrect(totalCorrect);
        history.setScore(score);

        historyDAO.save(history);
        return null;
    }

    @Override
    public List<ScoreResponse> getScore(Long userId, Long testId) {
        List<ScoreResponse> scoreResponses = new ArrayList<>();
        List<History> histories = getHistoryReturnHistory(userId, testId);
        for (History i: histories){
            scoreResponses.add(converter.converterScoreResponse(i));
        }
        return scoreResponses;
    }

    @Override
    public List<ScoreResponse> getHistoryByUserId(Long userId) {
//        Configuration config =  mapper.getConfiguration().setFieldMatchingEnabled(true);
        List<ScoreResponse> scoreResponses = new ArrayList<>();
        List<History> histories = getHistoryReturnHistory(userId);
        for (History i: histories){
            scoreResponses.add(converter.converterScoreResponse(i));
        }
        return scoreResponses;
    }

    private float returnScore(int size, int totalCorrect){
        DecimalFormat format = new DecimalFormat("#.###");
        return  Float.parseFloat(format.format((float) totalCorrect/size))*10;
    }

    @Override
    public HistoryResponse getHistoryById(Long hisId) {
        History historiy = historyDAO.findById(hisId).orElseThrow(()->
                new ResourceNotFoundException(EHistory.NOT_FOUND.getDes()));
        return (mapper.map(historiy, HistoryResponse.class));
    }
}
