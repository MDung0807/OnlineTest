package com.iotstar.onlinetest.DTOs.requests;

import com.iotstar.onlinetest.utils.AppConstant;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginRequest {
    @Size(min = 5, max = 15, message = "username have length greater 5")
    private String username;
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,30}$", message = "Password Not Format")
    private String password;
}
