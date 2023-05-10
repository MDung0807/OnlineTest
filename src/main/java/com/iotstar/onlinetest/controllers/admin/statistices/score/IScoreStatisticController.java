package com.iotstar.onlinetest.controllers.admin.statistices.score;

import com.iotstar.onlinetest.DTOs.responses.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/statistic/score")
@PreAuthorize("hasRole(@environment.getProperty('ROLE_ADMIN'))")
public interface IScoreStatisticController {
    @GetMapping({"/inSubject"})
    ResponseEntity<Response> getScoreStatisticInSubject(@RequestParam(required = false, defaultValue = "0") int index,
                                                        @RequestParam(required = false, defaultValue = "10") int size,
                                                        @RequestParam(required = false, defaultValue = "increase") String sort,
                                                        @RequestParam Long subjectId);

    @GetMapping({"/inTopic"})
    ResponseEntity<Response> getScoreStatisticInTopic(@RequestParam(required = false, defaultValue = "0") int index,
                                                        @RequestParam(required = false, defaultValue = "10") int size,
                                                        @RequestParam(required = false, defaultValue = "increase") String sort,
                                                      @RequestParam Long topicId);

    @GetMapping({"/inTest"})
    ResponseEntity<Response> getScoreStatisticInTest (@RequestParam(required = false, defaultValue = "0") int index,
                                                      @RequestParam(required = false, defaultValue = "10") int size,
                                                      @RequestParam(required = false, defaultValue = "increase") String sort,
                                                      @RequestParam Long testId);
}