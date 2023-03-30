package com.iotstar.onlinetest.services.account;

import com.iotstar.onlinetest.models.Account;
import com.iotstar.onlinetest.models.Role;
import com.iotstar.onlinetest.models.User;
import com.iotstar.onlinetest.repositories.AccountDAO;
import com.iotstar.onlinetest.repositories.RoleDAO;
import com.iotstar.onlinetest.repositories.UserDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountServiceImpTest {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private RoleDAO roleDAO;
    @Autowired
    private AccountDAO accountDAO;

    @BeforeEach
    void setUp() {
    }

    @Test
    void delAcc() {
    }

    @Test
    void getAllAcc() {
    }

    @Test
    void getAccByUsername() {
    }

    @Test
    void createAccount() {

    }

    @Test
    void update() {
        Account account;
        account = accountDAO.getReferenceById(5L);
        account.setPassword("$2a$10$VGEuv56GaeaEMGKCIDCC7u0ZWswG0wq1trpaMLxzGGnV/zplolGKu");
        accountDAO.save(account);
    }
}