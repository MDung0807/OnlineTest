package com.iotstar.onlinetest.controllers;

import com.iotstar.onlinetest.DTO.AccountDTO;
import com.iotstar.onlinetest.models.entities.Account;
import com.iotstar.onlinetest.models.repositories.AccountDAO;
import com.iotstar.onlinetest.models.services.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping({"/", ""})
public class test {
//    @Autowired
//    private AccountDAO accountDAO;

    @Autowired
    private AccountService accountService;


    @RequestMapping("/account")
    public void getAcc(){

    }
}
