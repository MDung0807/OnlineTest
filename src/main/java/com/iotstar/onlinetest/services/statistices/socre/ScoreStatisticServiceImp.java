package com.iotstar.onlinetest.services.statistices.socre;

import com.iotstar.onlinetest.models.Test;
import com.iotstar.onlinetest.models.Topic;
import com.iotstar.onlinetest.models.statistics.ScoreStatistic;
import com.iotstar.onlinetest.models.statistics.TestStatistic;
import com.iotstar.onlinetest.repositories.HistoryDAO;
import com.iotstar.onlinetest.repositories.TestDAO;
import com.iotstar.onlinetest.services.test.TestServiceImp;
import com.iotstar.onlinetest.services.topic.TopicServiceImp;
import com.iotstar.onlinetest.utils.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ScoreStatisticServiceImp extends ScoreStatisticPaging implements ScoreStatisticService{

    @Autowired
    private HistoryDAO historyDAO;
    @Autowired
    private TestServiceImp testServiceImp;
    @Autowired
    private Converter converter;
    @Override
    public List<ScoreStatistic> getScoreStatisticInSubject(Long subjectId) {
        return null;
    }

    @Override
    public List<ScoreStatistic> getScoreStatisticInTest(Long testId) {
        Test test = testServiceImp.getTestReturnTest(testId);
        List<Test>tests = new ArrayList<>();
        tests.add(test);
        List<Map<String, Object>> data = historyDAO.getScoreStatistic(tests,pageable());

        List<ScoreStatistic> responses = returnScoreStatics(data);

        return responses;
    }

    @Override
    public List<ScoreStatistic> getScoreStatisticInTopic(Long topId) {
        List<Test> tests = testServiceImp.getTestsByTopicId(topId);
        List<Map<String, Object>> data = historyDAO.getScoreStatistic(tests, pageable());

        List<ScoreStatistic> responses = returnScoreStatics(data);

        return responses;
    }

    private List<ScoreStatistic> returnScoreStatics(List<Map<String, Object>> params){
        List<ScoreStatistic> scoreStatistics = new ArrayList<>();
        for (Map<String, Object> i: params){
            scoreStatistics.add(converter.converterScoreStatistic(i));
        }
        return scoreStatistics;
    }
}
