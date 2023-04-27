package com.iotstar.onlinetest.controllers.admin;

import com.iotstar.onlinetest.DTOs.AccountDTO;
import com.iotstar.onlinetest.DTOs.requests.RoleRequest;
import com.iotstar.onlinetest.DTOs.responses.Response;
import com.iotstar.onlinetest.services.account.AccountService;
import com.iotstar.onlinetest.services.role.RoleService;
import com.iotstar.onlinetest.services.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/account")
@PreAuthorize("hasRole(@environment.getProperty('ROLE_ADMIN'))")
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

    @GetMapping("/updateRole")
    public ResponseEntity<Response> updateRole(@Valid @RequestParam("userId") Long userId,
                                               @RequestParam("roleName") String roleName){
        AccountDTO accountDTO = accountService.updateRole(userId, roleName);
        return new ResponseEntity<>(
                new Response(false, accountDTO),
                HttpStatus.OK
        );
    }
}
