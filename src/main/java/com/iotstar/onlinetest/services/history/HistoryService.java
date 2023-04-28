package com.iotstar.onlinetest.services.history;

import com.iotstar.onlinetest.DTOs.requests.HistoryRequest;
import com.iotstar.onlinetest.DTOs.responses.HistoryResponse;

public interface HistoryService {
    public HistoryResponse getHistoryByUserId(Long userId, Long testId);
    public String setHistoryByUserId(HistoryRequest request);
}
