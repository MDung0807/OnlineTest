package com.iotstar.onlinetest.controllers.client.reviewEndpoint;

import com.iotstar.onlinetest.DTOs.requests.ReviewItemRequest;
import com.iotstar.onlinetest.DTOs.responses.MessageResponse;
import com.iotstar.onlinetest.DTOs.responses.Response;
import com.iotstar.onlinetest.DTOs.responses.ReviewItemResponse;
import com.iotstar.onlinetest.services.review.ReviewPaging;
import com.iotstar.onlinetest.services.review.ReviewService;
import com.iotstar.onlinetest.utils.AppConstant;
import com.iotstar.onlinetest.utils.AuthUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class ReviewController implements IReviewController {
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private ReviewPaging paging;
    @Autowired
    private AuthUtils authUtils;

    private ReviewItemResponse response;
    @Override
    public ResponseEntity<Response> getAllReviewByTest(Long testId, int size, int index) {
        paging.setPageSize(size);
        paging.setPageIndex(index);
        List<ReviewItemResponse> responses = reviewService.getAllReviewByTestId(testId);
        return new ResponseEntity<>(
                new Response(false, responses),
                HttpStatus.OK
        );
    }

    @Override
    public ResponseEntity<Response> addReview(ReviewItemRequest request) {
        Long id = authUtils.getAccountDetail().getUserId();
        if (!id.equals(request.getUserId()))
            throw new AccessDeniedException(AppConstant.ACCESS_DENIED);
        response = reviewService.addReview(request);
        return new ResponseEntity<>(
                new Response(false, response),
                HttpStatus.OK
        );
    }

    @Override
    public ResponseEntity<Response> delReview(Long userId, Long testId) {

        Long id = authUtils.getAccountDetail().getUserId();
        if (!id.equals(userId))
            throw new AccessDeniedException(AppConstant.ACCESS_DENIED);
        reviewService.deleteReview(userId, testId);
        return new ResponseEntity<>(
                new Response(false, new MessageResponse(AppConstant.SUCCESS)),
                HttpStatus.OK
        );
    }
}
