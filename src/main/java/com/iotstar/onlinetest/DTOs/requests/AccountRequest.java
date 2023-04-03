package com.iotstar.onlinetest.DTOs.requests;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class AccountRequest {

    @Size(min = 6, max = 15, message = "username have length greater 6")
    private String username;
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,30}$", message = "password not strong")
    private String password;
    @Email
    private String email;
    @Pattern(regexp = "^(0)(?=.*[0-9]).{9}$", message = "size = 10")
    private String phoneNumber;
}
