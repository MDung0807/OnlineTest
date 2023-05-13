package com.iotstar.onlinetest.utils;

import com.iotstar.onlinetest.DTOs.responses.statistices.TestStatisticResponseUser;
import com.iotstar.onlinetest.models.statistics.ScoreStatistic;
import com.iotstar.onlinetest.models.statistics.TestStatistic;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;

@Component
public class Converter {
    public TestStatistic converterTestStatistic(Map<String, Object> param){
        TestStatistic testStatistic = new TestStatistic();
        testStatistic.setTestId((Long)param.get("testId"));
        testStatistic.setNumberUserTest((Long)param.get("numberUserTest"));
        testStatistic.setTestName((String)param.get("testName"));
        testStatistic.setTime((int) param.get("time"));
        testStatistic.setDateCreate((LocalDateTime) param.get("dateCreate"));
        return testStatistic;
    }

    public ScoreStatistic converterScoreStatistic(Map<String, Object> param){
        ScoreStatistic scoreStatistic = new ScoreStatistic();
        scoreStatistic.setNumberScore((Long) param.get("numberScore"));
        scoreStatistic.setScore((float) param.get("score"));
        scoreStatistic.setTestId((Long) param.get("testId"));
        return scoreStatistic;
    }

    public TestStatisticResponseUser converterTestStatisticUser(Map<String,Object>param){
        TestStatisticResponseUser response = new TestStatisticResponseUser();
        String firstName = (String) param.get("firstName");
        String lastName = (String) param.get("lastName");
        response.setFullName(firstName+ " "+lastName);
        response.setScore((float) param.get("score"));
        return response;
    }
}
