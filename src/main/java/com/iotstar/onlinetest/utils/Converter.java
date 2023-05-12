package com.iotstar.onlinetest.utils;

import com.iotstar.onlinetest.models.statistics.ScoreStatistic;
import com.iotstar.onlinetest.models.statistics.TestStatistic;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class Converter {
    public TestStatistic converterTestStatistic(Map<String, Object> param){
        TestStatistic testStatistic = new TestStatistic();
        testStatistic.setTestId((Long)param.get("testId"));
        testStatistic.setNumberUserTest((Long)param.get("numberUserTest"));
        testStatistic.setTestName((String)param.get("testName"));
        return testStatistic;
    }

    public ScoreStatistic converterScoreStatistic(Map<String, Object> param){
        ScoreStatistic scoreStatistic = new ScoreStatistic();
        scoreStatistic.setNumberScore((Long) param.get("numberScore"));
        scoreStatistic.setScore((float) param.get("score"));
        scoreStatistic.setTestId((Long) param.get("testId"));
        return scoreStatistic;
    }
}