package com.iotstar.onlinetest.DTOs.requests;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
