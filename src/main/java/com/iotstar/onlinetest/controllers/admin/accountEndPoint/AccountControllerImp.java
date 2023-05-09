package com.iotstar.onlinetest.controllers.admin.accountEndPoint;

import com.iotstar.onlinetest.DTOs.AccountDTO;
import com.iotstar.onlinetest.DTOs.responses.Response;
import com.iotstar.onlinetest.services.account.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountControllerImp implements IAccountController {

    @Autowired
    private AccountService accountService;

    @Override
    public List<AccountDTO> getAllAcc (){
        return accountService.getAllAcc();
    }


    @Override
    public ResponseEntity<Response> updateRole(Long userId, String roleName){
        AccountDTO accountDTO = accountService.updateRole(userId, roleName);
        return new ResponseEntity<>(
                new Response(false, accountDTO),
                HttpStatus.OK
        );
    }
}
