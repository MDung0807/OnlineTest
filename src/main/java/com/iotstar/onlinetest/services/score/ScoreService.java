package com.iotstar.onlinetest.services.score;

import com.iotstar.onlinetest.DTOs.responses.ScoreResponse;

public interface ScoreService {
    public void setScore(Long userId, Long testId, String score);
    public ScoreResponse getScore(Long userId, Long testId);
}
