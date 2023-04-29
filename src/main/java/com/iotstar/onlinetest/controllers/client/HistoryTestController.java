package com.iotstar.onlinetest.controllers.client;

import com.iotstar.onlinetest.DTOs.requests.HistoryRequest;
import com.iotstar.onlinetest.DTOs.responses.HistoryResponse;
import com.iotstar.onlinetest.DTOs.responses.MessageResponse;
import com.iotstar.onlinetest.DTOs.responses.Response;
import com.iotstar.onlinetest.DTOs.responses.ScoreResponse;
import com.iotstar.onlinetest.services.history.HistoryService;
import com.iotstar.onlinetest.services.score.ScoreService;
import com.iotstar.onlinetest.utils.AppConstant;
import com.iotstar.onlinetest.utils.AuthUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/history")
public class HistoryTestController {
    @Autowired
    private HistoryService historyService;
    @Autowired
    private ScoreService scoreService;
    @Autowired
    private AuthUtils authUtils;

    @GetMapping({"", "/"})
    public ResponseEntity<Response> getTestHis(@RequestParam Long userId,
                                               @RequestParam Long testId){
        Long id = authUtils.getAccountDetail().getUserId();
        if (!id.equals(userId))
            throw new AccessDeniedException(AppConstant.ACCESS_DENIED);
        List<HistoryResponse> responses = historyService.getHistoryByUserId(userId, testId);
        return new ResponseEntity<>(
                new Response(false, responses),
                HttpStatus.OK
        );
    }

    @GetMapping({"/score"})
    public ResponseEntity<Response> getScore(@RequestParam Long userId,
                                             @RequestParam Long testId){
        Long id = authUtils.getAccountDetail().getUserId();
        if (!id.equals(userId))
            throw new AccessDeniedException(AppConstant.ACCESS_DENIED);
        List<ScoreResponse> scoreResponses = historyService.getScore(userId, testId);
        return new ResponseEntity<>(
                new Response(false, scoreResponses),
                HttpStatus.OK
        );
    }
    @PostMapping ("/finishTest")
    public ResponseEntity<Response> finishTest(@RequestBody HistoryRequest request){
        Long userId = authUtils.getAccountDetail().getUserId();
        if (!userId.equals(request.getUserId()))
            throw new AccessDeniedException(AppConstant.ACCESS_DENIED);
        String score = historyService.setHistoryByUserId(request);
//        scoreService.setScore(request.getUserId(), request.getTestId(), score);
        return new ResponseEntity<>(
                new Response(false, new MessageResponse(AppConstant.SUCCESS)),
                HttpStatus.OK
        );
    }
}
