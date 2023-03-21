package com.iotstar.onlinetest.controllers.client;


import com.iotstar.onlinetest.DTO.RegisterForm;
import com.iotstar.onlinetest.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.iotstar.onlinetest.DTO.RegisterForm.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public UserDTO getUser(@RequestParam int userId){
        return userService.getUser(userId);
    }

    @RequestMapping("/register")
    public void createUser(@RequestBody RegisterForm registerForm)throws Exception{


    }
}
