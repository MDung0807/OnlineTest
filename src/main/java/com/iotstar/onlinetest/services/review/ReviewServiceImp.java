package com.iotstar.onlinetest.services.review;

import com.iotstar.onlinetest.DTOs.requests.ReviewItemRequest;
import com.iotstar.onlinetest.DTOs.responses.ReviewItemResponse;
import com.iotstar.onlinetest.exceptions.ResourceNotFoundException;
import com.iotstar.onlinetest.models.Review;
import com.iotstar.onlinetest.models.ReviewItem;
import com.iotstar.onlinetest.models.Test;
import com.iotstar.onlinetest.models.User;
import com.iotstar.onlinetest.repositories.ReviewDAO;
import com.iotstar.onlinetest.repositories.ReviewItemDAO;
import com.iotstar.onlinetest.services.test.TestServiceImp;
import com.iotstar.onlinetest.statval.EReview;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewServiceImp extends ReviewPaging implements ReviewService {

    @Autowired
    private ReviewItemDAO reviewItemDAO;
    @Autowired
    private ReviewDAO reviewDAO;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    @Lazy
    private TestServiceImp testServiceImp;
    public void createReview (User user){
        Review review = new Review();
        review.setUser(user);
        reviewDAO.saveAndFlush(review);
    }

    public Review getReviewByUserId(Long userId){
        return reviewDAO.findByUser_UserId(userId).orElseThrow(()->
                new ResourceNotFoundException(EReview.E_REVIEW_NOTFOUND.getDescription()));
    }

    @Override
    public ReviewItemResponse addReview(ReviewItemRequest request) {
        Test test = testServiceImp.getTestReturnTest(request.getTestId());
        Review review = getReviewByUserId(request.getUserId());
        mapper.getConfiguration().setAmbiguityIgnored(true);
        ReviewItem reviewItem = mapper.map(request, ReviewItem.class);
        reviewItem.setDateReview(LocalDateTime.now());
        reviewItem.setReview(review);
        reviewItem.setTest(test);
        reviewItem.setDateUpdate(LocalDateTime.now());
        reviewItem.setStatus(1);
        reviewItem = reviewItemDAO.save(reviewItem);
        return mapper.map(reviewItem, ReviewItemResponse.class);
    }

    @Override
    public List<ReviewItemResponse> getAllReviewByTestId(Long testId) {
        List<ReviewItem> reviewItems = reviewItemDAO.findByTest_TestId(testId, pageable()).orElseThrow(()->
                new ResourceNotFoundException(EReview.E_REVIEW_IN_TEST_NOTFOUND.getDescription()));
        List<ReviewItemResponse> responses = new ArrayList<>();
        for (ReviewItem i: reviewItems){
            responses.add(mapper.map(i, ReviewItemResponse.class));
        }
        return responses;
    }

    @Override
    public void deleteReview(Long userId, Long testId) {
        ReviewItem reviewItem = reviewItemDAO.findByUserIdAndTestId(userId, testId).orElseThrow(()->
                new ResourceNotFoundException(EReview.E_REVIEW_NOTFOUND.getDescription()));
        reviewItem.setStatus(0);
        reviewItem.setDateUpdate(LocalDateTime.now());
        reviewItemDAO.saveAndFlush(reviewItem);
    }
}
