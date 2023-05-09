package com.iotstar.onlinetest.DTOs.responses;

import lombok.Data;

import java.util.List;

@Data
public class WishListResponse {
    private List<WishItemResponse> wishItemRespons;
}
