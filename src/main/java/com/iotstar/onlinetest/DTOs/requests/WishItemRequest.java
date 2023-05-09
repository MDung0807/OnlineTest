package com.iotstar.onlinetest.DTOs.requests;

import lombok.Data;

@Data
public class WishItemRequest {
    private Long userId;
    private Long topicId;
}
