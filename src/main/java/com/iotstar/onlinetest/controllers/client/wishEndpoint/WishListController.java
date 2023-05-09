package com.iotstar.onlinetest.controllers.client.wishEndpoint;

import com.iotstar.onlinetest.DTOs.requests.WishItemRequest;
import com.iotstar.onlinetest.DTOs.responses.MessageResponse;
import com.iotstar.onlinetest.DTOs.responses.Response;
import com.iotstar.onlinetest.DTOs.responses.WishListResponse;
import com.iotstar.onlinetest.services.wishList.WishListPaging;
import com.iotstar.onlinetest.services.wishList.WishListService;
import com.iotstar.onlinetest.utils.AppConstant;
import com.iotstar.onlinetest.utils.AuthUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class WishListController implements IWishController {
    @Autowired
    private WishListService wishListService;
    @Autowired
    private AuthUtils authUtils;
    @Autowired
    private WishListPaging paging;
    private WishListResponse wishListResponse;

    @Override
    public ResponseEntity<Response> getWishListByUserId(Long userId, int size, int index) {
        paging.setPageSize(size);
        paging.setPageIndex(index);
        Long id = authUtils.getAccountDetail().getUserId();
        if (!id.equals(userId))
            throw new AccessDeniedException(AppConstant.ACCESS_DENIED);
        wishListResponse = wishListService.getWishListByUserId(userId);
        return new ResponseEntity<>(
                new Response(false, wishListResponse),
                HttpStatus.OK
        );
    }

    @Override
    public ResponseEntity<Response> addWishItem(WishItemRequest wishItemRequest) {
        Long id = authUtils.getAccountDetail().getUserId();
        if (!id.equals(wishItemRequest.getUserId()))
            throw new AccessDeniedException(AppConstant.ACCESS_DENIED);
        wishListService.addWishItem(wishItemRequest);
        return new ResponseEntity<>(
                new Response(false, new MessageResponse(AppConstant.SUCCESS)),
                HttpStatus.OK
        );
    }

    @Override
    public ResponseEntity<Response> delWishItem(WishItemRequest wishItemRequest) {
        Long id = authUtils.getAccountDetail().getUserId();
        if (!id.equals(wishItemRequest.getUserId()))
            throw new AccessDeniedException(AppConstant.ACCESS_DENIED);
        wishListService.delWishItem(wishItemRequest);
        return new ResponseEntity<>(
                new Response(false, new MessageResponse(AppConstant.SUCCESS)),
                HttpStatus.OK
        );
    }
}
