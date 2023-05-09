package com.iotstar.onlinetest.repositories;

import com.iotstar.onlinetest.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReviewDAO extends JpaRepository<Review, Long> {
    Optional<Review> findByUser_UserId(Long userId);
}
