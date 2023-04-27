package com.iotstar.onlinetest.controllers.client;

import com.iotstar.onlinetest.DTOs.AccountDTO;
import com.iotstar.onlinetest.DTOs.requests.UserProfileRequest;
import com.iotstar.onlinetest.DTOs.responses.MessageResponse;
import com.iotstar.onlinetest.DTOs.responses.Response;
import com.iotstar.onlinetest.DTOs.responses.UserResponse;
import com.iotstar.onlinetest.security.jwt.JwtUtils;
import com.iotstar.onlinetest.services.blackList.BlackListService;
import com.iotstar.onlinetest.services.user.UserService;
import com.iotstar.onlinetest.utils.AppConstant;
import com.iotstar.onlinetest.utils.AuthUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.GeneralSecurityException;


@RestController
@CrossOrigin
@RequestMapping("")
@PreAuthorize("hasAnyRole({" +
        "@environment.getProperty('ROLE_STUDENT'), " +
        "@environment.getProperty('ROLE_TEACHER')," +
        "@environment.getProperty('ROLE_ADMIN')})")
public class UserController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;
    @Autowired
    private BlackListService blackListService;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private AuthUtils authUtils;

    private UserResponse userResponse;
    @GetMapping("/profile")
    public ResponseEntity<Response> getUser(){
        Long userId = authUtils.getAccountDetail().getUserId();
        userResponse = userService.getUser(userId);
        return new ResponseEntity<>(
                new Response(false, userResponse),
                HttpStatus.OK);
    }


    @PostMapping("/delAcc")
    public ResponseEntity<Response> delAcc(@RequestBody UserProfileRequest userProfileRequest){
        Long id = authUtils.getAccountDetail().getAccountId();
        if (!id.equals(userProfileRequest.getUserId()))
            throw new AccessDeniedException(AppConstant.ACCESS_DENIED);
        userProfileRequest.setUserId(id);
        userService.deleteUser(userProfileRequest);
        return new ResponseEntity<>(
                new Response(false, new MessageResponse(AppConstant.SUCCESS)),
                HttpStatus.OK);
    }

    @PostMapping("/updateProfile")
    public ResponseEntity<Response> updateProfile(@Valid @ModelAttribute UserProfileRequest userParam1,
                              @Valid @RequestPart(value = "user", required = false)UserProfileRequest userParam2) {
        Long userId = authUtils.getAccountDetail().getUserId();

        if(userParam2== null){
            if (!userId.equals(userParam1.getUserId()))
                throw new AccessDeniedException(AppConstant.ACCESS_DENIED);
            userResponse = userService.updateUser(userParam1);
            return new ResponseEntity<>(
                    new Response(false, userResponse),
                    HttpStatus.OK);
        }
        if (!userId.equals(userParam2.getUserId()))
            throw new AccessDeniedException(AppConstant.ACCESS_DENIED);
        userResponse = userService.updateUser(userParam2);
        return new ResponseEntity<>(
                new Response(false, userResponse),
                HttpStatus.OK);
    }

    @PostMapping("/updateAvatar")
    public ResponseEntity<Response> updateAvatar(@ModelAttribute MultipartFile avatar){
        Long id = authUtils.getAccountDetail().getAccountId();
        userResponse = userService.updateAvatar(id, avatar);
        return ResponseEntity.ok(new Response(false, userResponse));

    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public ResponseEntity<?> logout(HttpServletRequest request) {
        String token = jwtUtils.parseJwt(request);
        if (token != null){
            blackListService.save(token);
        }
        return ResponseEntity.ok(new Response(false, new MessageResponse(AppConstant.SUCCESS)));
    }
}
