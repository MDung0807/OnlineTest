package com.iotstar.onlinetest.controllers.client.userEndpoint;

import com.iotstar.onlinetest.DTOs.requests.ResetPassword;
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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
public class UserControllerImp implements IUserEndpoint{

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
    @Override
    public ResponseEntity<Response> getUser(){
        Long userId = authUtils.getAccountDetail().getUserId();
        userResponse = userService.getUser(userId);
        return new ResponseEntity<>(
                new Response(false, userResponse),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response> delAcc(Long userId){
        Long id = authUtils.getAccountDetail().getAccountId();
        if (!id.equals(userId))
            throw new AccessDeniedException(AppConstant.ACCESS_DENIED);

        userService.deleteUser(userId);
        return new ResponseEntity<>(
                new Response(false, new MessageResponse(AppConstant.SUCCESS)),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response> updateProfile(UserProfileRequest userParam1, UserProfileRequest userParam2) {
        Long userId = authUtils.getAccountDetail().getUserId();
        UserProfileRequest userProfileRequest = null;
        if(userParam2== null){
            if (!userId.equals(userParam1.getUserId()))
                throw new AccessDeniedException(AppConstant.ACCESS_DENIED);
            userProfileRequest = userParam1;
        }
        else {
            if (!userId.equals(userParam2.getUserId()))
                throw new AccessDeniedException(AppConstant.ACCESS_DENIED);
            userProfileRequest = userParam2;
        }
        userResponse = userService.updateUser(userProfileRequest);
        return new ResponseEntity<>(
                new Response(false, userResponse),
                HttpStatus.OK
        );

    }

    @Override
    public ResponseEntity<Response> updateAvatar(MultipartFile image){
        Long id = authUtils.getAccountDetail().getUserId();
        userResponse = userService.updateAvatar(id, image);
        return ResponseEntity.ok(new Response(false, userResponse));

    }

    @Override
    public ResponseEntity<?> logout(HttpServletRequest request) {
        String token = jwtUtils.parseJwt(request);
        if (token != null){
            blackListService.save(token);
        }
        return ResponseEntity.ok(new Response(false, new MessageResponse(AppConstant.SUCCESS)));
    }

    @Override
    public ResponseEntity<Response> updatePass(ResetPassword resetPassword) {
        Long userId = authUtils.getAccountDetail().getUserId();
        userService.updatePassword(resetPassword, userId);
        return ResponseEntity.ok(
                new Response(false, new MessageResponse(AppConstant.SUCCESS)));
    }
}
