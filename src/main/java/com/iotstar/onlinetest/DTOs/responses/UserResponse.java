package com.iotstar.onlinetest.DTOs.responses;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserResponse {
    private Long userId;
    private String avatar;
    private LocalDateTime dateCreate;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private int status;
}
