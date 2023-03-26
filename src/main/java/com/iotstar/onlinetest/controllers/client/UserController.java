package com.iotstar.onlinetest.controllers.client;


import com.iotstar.onlinetest.DTOs.AccountDTO;
import com.iotstar.onlinetest.DTOs.requests.UserProfileRequest;
import com.iotstar.onlinetest.DTOs.requests.UserRequest;
import com.iotstar.onlinetest.DTOs.responses.UserResponse;
import com.iotstar.onlinetest.services.account.AccountService;
import com.iotstar.onlinetest.services.role.RoleService;
import com.iotstar.onlinetest.services.user.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

    @RestController
    public class UserController {

        @Autowired
        private UserService userService;
        @Autowired
        private RoleService roleService;
        @Autowired
    private AccountService accountService;
    @Autowired
    private ModelMapper mapper;

    private AccountDTO accountDTO;
    @PostMapping("/profile")
    public ResponseEntity<UserResponse> getUser(@RequestBody UserProfileRequest userProfileRequest){
        return new ResponseEntity<>(userService.getUser(userProfileRequest.getUserId()), HttpStatus.OK);
    }

    @PostMapping("/register")
    public void createUser(@RequestBody UserRequest userRequest)throws Exception{
        userService.createUser(userRequest);
    }

    @PostMapping("/delAcc")
    public void delAcc(@RequestBody UserProfileRequest userProfileRequest){
        userService.deleteUser(userProfileRequest);
    }
}
