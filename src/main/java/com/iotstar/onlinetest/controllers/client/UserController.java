package com.iotstar.onlinetest.controllers.client;


import com.iotstar.onlinetest.DTO.UserDTO;
import com.iotstar.onlinetest.models.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public UserDTO getUser(@RequestParam int userId){
        return userService.getUser(userId);
    }
}
