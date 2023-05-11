package com.iotstar.onlinetest.repositories;

import com.iotstar.onlinetest.models.ReviewItem;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewItemDAO extends JpaRepository<ReviewItem, Long> {
    @Query("select u from ReviewItem u where u.review.user.userId = ?1 and u.test.testId=?2")
    Optional<ReviewItem> findByUserIdAndTestId(Long userId, Long testId);

    Optional<List<ReviewItem>> findByTest_TestId(Long testId, Pageable pageable);
    @Query("select u from ReviewItem u where u.review.reivewId=?1")
    Optional<List<ReviewItem>> findByReviewId(Long reviewId);
}
