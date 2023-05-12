package com.iotstar.onlinetest.services.history;

import com.iotstar.onlinetest.DTOs.requests.HistoryRequest;
import com.iotstar.onlinetest.DTOs.responses.HistoryResponse;
import com.iotstar.onlinetest.DTOs.responses.ScoreResponse;

import java.util.List;

public interface HistoryService {
    List<ScoreResponse> getScore(Long userId, Long testId);
    List<HistoryResponse> getHistoryByUserId(Long userId, Long testId);
    List<ScoreResponse> getHistoryByUserId(Long userId);
    String setHistoryByUserId(HistoryRequest request);
    HistoryResponse getHistoryById(Long hisId);
}
