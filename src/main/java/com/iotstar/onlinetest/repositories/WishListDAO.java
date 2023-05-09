package com.iotstar.onlinetest.repositories;

import com.iotstar.onlinetest.models.WishList;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WishListDAO extends JpaRepository<WishList, Long> {
    Optional<WishList> findByUser_UserId(Long userId);
}
