package com.iotstar.onlinetest.controllers.client.reviewEndpoint;

import com.iotstar.onlinetest.DTOs.requests.ReviewItemRequest;
import com.iotstar.onlinetest.DTOs.responses.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/review")
public interface ReviewController {

    @GetMapping({"/", ""})
    ResponseEntity<Response> getAllReviewByTest(@RequestParam Long testId,
                                                @RequestParam(required = false, defaultValue = "10") int size,
                                                @RequestParam(required = false, defaultValue = "0") int index);

    @PostMapping("/add")
    ResponseEntity<Response> addReview(@RequestBody ReviewItemRequest request);

    @GetMapping("/del")
    ResponseEntity<Response> delReview(@RequestParam Long userId,
                                       @RequestParam Long testId);
}
