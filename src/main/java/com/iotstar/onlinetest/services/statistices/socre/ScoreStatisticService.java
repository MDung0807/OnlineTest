package com.iotstar.onlinetest.services.statistices.socre;

import com.iotstar.onlinetest.models.Test;
import com.iotstar.onlinetest.models.statistics.ScoreStatistic;

import java.util.List;

public interface ScoreStatisticService {
    List<ScoreStatistic> getScoreStatisticInSubject(Long subjectId);
    List<ScoreStatistic> getScoreStatisticInTopic(Long topId);
    List<ScoreStatistic> getScoreStatisticInTest(Long testId);
}
