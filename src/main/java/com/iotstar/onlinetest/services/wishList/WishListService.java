package com.iotstar.onlinetest.services.wishList;

import com.iotstar.onlinetest.DTOs.requests.WishItemRequest;
import com.iotstar.onlinetest.DTOs.responses.WishListResponse;

public interface WishListService {
    void addWishItem (WishItemRequest wishItemRequest);
    WishListResponse getWishListByUserId(Long userId);
    WishListResponse delWishItem(WishItemRequest wishItemRequest);
}
