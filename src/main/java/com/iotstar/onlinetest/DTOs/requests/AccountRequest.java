package com.iotstar.onlinetest.DTOs.requests;

import lombok.Data;

@Data
public class AccountRequest {
    private String userName;
    private String password;
    private String email;
    private String phoneNumber;
}
