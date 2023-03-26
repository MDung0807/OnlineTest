package com.iotstar.onlinetest.controllers.admin;

import com.iotstar.onlinetest.DTOs.AccountDTO;
import com.iotstar.onlinetest.DTOs.requests.RoleRequest;
import com.iotstar.onlinetest.services.account.AccountService;
import com.iotstar.onlinetest.services.role.RoleService;
import com.iotstar.onlinetest.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/account")
public class AccountController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;

    @PostMapping("")
    public void updateRole(@RequestBody RoleRequest roleRequest) throws Exception{

    }

    @GetMapping({"/", ""})
    public List<AccountDTO> getAllAcc (){
        return accountService.getAllAcc();
    }
}
