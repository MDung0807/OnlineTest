package com.iotstar.onlinetest.controllers.client.wishEndpoint;

import com.iotstar.onlinetest.DTOs.requests.WishItemRequest;
import com.iotstar.onlinetest.DTOs.responses.Response;
import com.iotstar.onlinetest.DTOs.responses.WishListResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/wish")
public interface WishController {

    @GetMapping({"", "/"})
    ResponseEntity<Response> getWishListByUserId(@RequestParam Long userId,
                                                 @RequestParam(required = false, defaultValue = "10") int size,
                                                 @RequestParam(required = false, defaultValue = "0") int index);

    @PostMapping("/add")
    ResponseEntity<Response> addWishItem(@RequestBody WishItemRequest wishItemRequest);

    @PostMapping("/del")
    ResponseEntity<Response> delWishItem(@RequestBody WishItemRequest wishItemRequest);
}
