package com.iotstar.onlinetest.controllers.client.historyEndpoint;

import com.iotstar.onlinetest.DTOs.requests.HistoryRequest;
import com.iotstar.onlinetest.DTOs.responses.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/history")
public interface IHistoryController {
    @GetMapping({"", "/"})
    ResponseEntity<Response> getTestHis(@RequestParam Long userId,
                                               @RequestParam Long testId);
    @GetMapping({"/score"})
    ResponseEntity<Response> getScore(@RequestParam Long userId,
                                             @RequestParam Long testId);
    @PostMapping("/finishTest")
    ResponseEntity<Response> finishTest(@RequestBody HistoryRequest request);

    @GetMapping("/inUser")
    ResponseEntity<Response> getTestHisInUser(@RequestParam Long userId);

    //Get history for user if history id = hisId
    @GetMapping("/hisItem")
    ResponseEntity<Response> getHistoryInTest(@RequestParam Long userId,
                                            @RequestParam Long hisId);

}
