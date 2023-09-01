package com.iotstar.onlinetest.controllers.client.userEndpoint;

import com.iotstar.onlinetest.DTOs.requests.ResetPassword;
import com.iotstar.onlinetest.DTOs.requests.UserProfileRequest;
import com.iotstar.onlinetest.DTOs.responses.Response;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("")
@PreAuthorize("hasAnyRole({" +
        "@environment.getProperty('ROLE_STUDENT'), " +
        "@environment.getProperty('ROLE_TEACHER')," +
        "@environment.getProperty('ROLE_ADMIN')})")

public interface IUserEndpoint {
    @RequestMapping(value = "/logoutAcc", method = RequestMethod.GET)
    ResponseEntity<?> logout(HttpServletRequest request);

    @PostMapping("/updateAvatar")
    ResponseEntity<Response> updateAvatar(@ModelAttribute MultipartFile image);

    @PostMapping("/updateProfile")
    ResponseEntity<Response> updateProfile(@Valid @RequestBody UserProfileRequest userParam1,
                                                  @Valid @RequestPart(value = "user", required = false)UserProfileRequest userParam2);

    @GetMapping("/delAcc")
    ResponseEntity<Response> delAcc(@RequestParam("userId") Long userId);

    @GetMapping("/profile")
    ResponseEntity<Response> getUser();

    @PostMapping("/resetPassword")
    ResponseEntity<Response> updatePass(ResetPassword resetPassword);
}
