package com.iotstar.onlinetest.DTOs.requests;

import lombok.Data;

@Data
public class ResetPassword {
    private String oldPassword;
    private String newPassword;
    private String confirmPassword;
}
