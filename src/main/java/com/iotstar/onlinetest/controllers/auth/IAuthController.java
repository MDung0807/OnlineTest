package com.iotstar.onlinetest.controllers.auth;

import com.iotstar.onlinetest.DTOs.requests.AccountRequest;
import com.iotstar.onlinetest.DTOs.requests.LoginRequest;
import com.iotstar.onlinetest.DTOs.requests.UserRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


public interface IAuthController {
    @RequestMapping(value = "auth/register")
    ResponseEntity<?> createUser(@Valid @ModelAttribute UserRequest userParam1,
                                 @ModelAttribute MultipartFile image,
                                 @RequestPart(value = "user", required = false) @Valid UserRequest userParam2);

    @PostMapping("auth/login")
    ResponseEntity<?> login (@RequestBody LoginRequest loginRequest);

    @PostMapping("/auth/reset")
    ResponseEntity<?> resetPass(@RequestBody @Valid AccountRequest accountRequest);
}
