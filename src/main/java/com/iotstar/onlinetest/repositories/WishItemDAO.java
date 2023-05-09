package com.iotstar.onlinetest.repositories;

import com.iotstar.onlinetest.models.WishItem;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WishItemDAO extends JpaRepository<WishItem, Long> {
    @Query("select u from WishItem u where u.topic.id = ?1 and u.wishList.WishListId =?2")
    Optional<WishItem> findByTopicIdInWishListId(Long topicId, Long wishListId);

    Optional<List<WishItem>> findByWishList_WishListId(Long topicId, Pageable pageable);
}
