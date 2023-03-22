package com.iotstar.onlinetest.controllers.client;


import com.iotstar.onlinetest.DTOs.RegisterForm;
import com.iotstar.onlinetest.DTOs.RoleDTO;
import com.iotstar.onlinetest.services.account.AccountService;
import com.iotstar.onlinetest.services.role.RoleService;
import com.iotstar.onlinetest.services.user.UserService;
import com.iotstar.onlinetest.utils.transferToDTO.ToDTO;
import com.iotstar.onlinetest.utils.transferToModel.ToModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.iotstar.onlinetest.DTOs.RegisterForm.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private AccountService accountService;

    private RoleDTO roleDTO;
    private AccountDTO accountDTO;
    private UserDTO userDTO;
    @PostMapping("/profile")
    public RegisterForm getUser(@RequestBody AccountDTO accountDTO){
        accountDTO = accountService.getAccByUsername(accountDTO.getUsername());
        RegisterForm registerForm = RegisterForm.builder()
                .account(accountDTO)
//                .user(ToDTO.toUserDTO(accountDTO.getUser()))
                .build();
        return registerForm;
    }

    @PostMapping("/register")
    public void createUser(@RequestBody RegisterForm registerForm)throws Exception{
        roleDTO = roleService.getRoleByRoleName(registerForm.getRoleName());
        userDTO = userService.createUser(registerForm.getUser());
        accountService.createAccount(registerForm.getAccount(), userDTO, roleDTO);
//        userService.createUser(registerForm.getUser(), registerForm.getAccount());
    }

    @PostMapping("/delAcc")
    public void delAcc(@RequestBody AccountDTO accountDTO){
        accountDTO = accountService.getAccByUsername(accountDTO.getUsername());
        accountDTO.setStatus(0);
    }
}
