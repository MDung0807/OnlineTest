package com.iotstar.onlinetest.controllers.client;

import com.iotstar.onlinetest.DTOs.AccountDTO;
import com.iotstar.onlinetest.DTOs.requests.UserProfileRequest;
import com.iotstar.onlinetest.DTOs.responses.UserResponse;
import com.iotstar.onlinetest.services.account.AccountService;
import com.iotstar.onlinetest.services.role.RoleService;
import com.iotstar.onlinetest.services.user.UserService;
import com.iotstar.onlinetest.utils.FileUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.GeneralSecurityException;


@RestController
@CrossOrigin
public class UserController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private FileUtils fileUtil;

    private AccountDTO accountDTO;
    @PostMapping("/profile")
    public ResponseEntity<UserResponse> getUser(@RequestBody UserProfileRequest userProfileRequest){
        return new ResponseEntity<>(userService.getUser(userProfileRequest.getUserId()), HttpStatus.OK);
    }


    @PostMapping("/delAcc")
    public void delAcc(@RequestBody UserProfileRequest userProfileRequest){
        userService.deleteUser(userProfileRequest);
    }

    @PostMapping("/updateProfile")
    public void updateProfile(@RequestBody MultipartFile avatar) throws IOException, GeneralSecurityException {
        String url = fileUtil.upload(avatar, "avatar");
    }

}
