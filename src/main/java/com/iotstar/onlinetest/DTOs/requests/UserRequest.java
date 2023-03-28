package com.iotstar.onlinetest.DTOs.requests;

import lombok.Data;

import java.time.LocalDateTime;

@Data

public class UserRequest {
    private String avatar;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String username;
    private String password;
}
