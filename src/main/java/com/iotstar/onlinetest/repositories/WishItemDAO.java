package com.iotstar.onlinetest.repositories;

import com.iotstar.onlinetest.models.WishItem;
import com.iotstar.onlinetest.models.WishList;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WishItemDAO extends JpaRepository<WishItem, Long> {
    @Query("select u from WishItem u where u.topic.id = ?1 and u.wishList.wishListId =?2")
    Optional<WishItem> findByTopicIdInWishListId(Long topicId, Long wishListId);

    @Query("select u from WishItem  u where u.wishList.wishListId =?1")
    Optional<List<WishItem>> findWishItemsByWishListId(Long wishList, Pageable pageable);
}
