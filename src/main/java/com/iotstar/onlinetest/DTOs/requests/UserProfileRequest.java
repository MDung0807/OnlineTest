package com.iotstar.onlinetest.DTOs.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
public class UserProfileRequest {
    private Long userId;
    @Size(max = 30, message = "FirstName have length less then equal 30")
    private String firstName;
    @Size(max = 30, message = "LastName have length less then equal 30")
    private String lastName;
    @Email
    private String email;
    @Pattern(regexp = "^(0)(?=.*[0-9]).{9}$", message = "Phone number incorrect format")
    private String phoneNumber;
    @Size(max = 10, message = "gender have length greater 10")
    private String gender;
}
