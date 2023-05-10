package com.iotstar.onlinetest.controllers.admin.statistices.test;

import com.iotstar.onlinetest.DTOs.responses.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/statistic/test")
@PreAuthorize("hasRole(@environment.getProperty('ROLE_ADMIN'))")
public interface ITestStatisticController {
    @GetMapping({"", "/"})
    ResponseEntity<Response> getTestStatistic(@RequestParam(required = false, defaultValue = "0") int index,
                                              @RequestParam(required = false, defaultValue = "10") int size,
                                              @RequestParam(required = false, defaultValue = "increase") String sort);
}
