package com.iotstar.onlinetest.DTOs.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    @Email
    private String email;
    @Size(max = 30, message = "username have length greater 6")
    private String firstName;
    @Size(max = 30, message = "username have length greater 6")
    private String lastName;
    @Pattern(regexp = "^(0)(?=.*[0-9]).{9}$")
    private String phoneNumber;
    @Size(min = 6, max = 15, message = "username have length greater 6")
    private String username;
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,30}$", message = "password not strong")
    private String password;
    @Size(max = 10, message = "gender have length greater 10")
    private String gender;
    private MultipartFile avatar;
}
