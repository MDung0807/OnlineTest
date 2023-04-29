package com.iotstar.onlinetest.services.history;

import com.iotstar.onlinetest.DTOs.requests.HistoryRequest;
import com.iotstar.onlinetest.DTOs.responses.HistoryResponse;
import com.iotstar.onlinetest.DTOs.responses.ScoreResponse;

import java.util.List;

public interface HistoryService {
    public List<ScoreResponse> getScore(Long userId, Long testId);
    public List<HistoryResponse> getHistoryByUserId(Long userId, Long testId);
    public String setHistoryByUserId(HistoryRequest request);
}
