package com.iotstar.onlinetest.DTOs.requests;

import lombok.Data;

@Data
public class ReviewItemRequest {
    private Long userId;
    private Long testId;
    private int rating;
    private String comment;
}
