package com.iotstar.onlinetest.DTOs.requests;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserProfileRequest {
    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String gender;
}
