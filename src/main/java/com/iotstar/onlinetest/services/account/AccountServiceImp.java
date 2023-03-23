package com.iotstar.onlinetest.services.account;

import com.iotstar.onlinetest.DTOs.RegisterForm.*;
import com.iotstar.onlinetest.DTOs.RoleDTO;
import com.iotstar.onlinetest.models.Account;
import com.iotstar.onlinetest.models.Role;
import com.iotstar.onlinetest.models.User;
import com.iotstar.onlinetest.repositories.AccountDAO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountServiceImp implements AccountService{
    @Autowired
    private AccountDAO accountDAO;

    @Autowired
    private ModelMapper mapper;

    private AccountDTO accountDTO;
    private Account account;
    private Role role;
    private User user;


    @Override
    @Transactional
    public void delAcc(AccountDTO accountDTO){
        account = Account.builder()
                .status(0)
                .accountId(accountDTO.getAccountId())
                .username(accountDTO.getUsername())
                .password(accountDTO.getPassword())
                .role(accountDTO.getRole())
//                .user(accountDTO.getUser())
                .build();


        accountDAO.save(account);
    }

    @Override
    public List<AccountDTO> getAllAcc() {
        List<Account> accounts = accountDAO.findAll();
        List<AccountDTO> accountDTOS = new ArrayList<>();
        for (Account i: accounts) {
            accountDTOS.add(mapper.map(i, AccountDTO.class));
        }
        return accountDTOS;
    }

    @Override
    public AccountDTO getAccByUsername(String username) {
        account = accountDAO.getByUsername(username);
        accountDTO = AccountDTO.builder()
                .user(account.getUser())
                .accountId(account.getAccountId())
                .username(account.getUsername())
                .password(account.getPassword())
                .role(account.getRole())
                .status(account.getStatus())
                .build();
        return accountDTO;
    }

    @Override
    @Transactional
    public void createAccount(AccountDTO accountDTO, UserDTO userDTO, RoleDTO roleDTO){
        role = Role.builder().build();
        user = User.builder().build();
        mapper.map(roleDTO, role);
        mapper.map(userDTO, user);

        accountDTO.setStatus(1);
        accountDTO.setRole(role);
        accountDTO.setUser(user);
        String err = null;
        account = mapper.map(accountDTO, Account.class);
        try {
            accountDAO.save(account);
        }
        catch (Exception ex){
           err = ex.getMessage();
        }
    }

    @Override
    @Transactional
    public void update(AccountDTO accountDTO){
        account = Account.builder()
                .accountId(accountDTO.getAccountId())
                .role(accountDTO.getRole())
//                .user(accountDTO.getUser())
                .password(accountDTO.getPassword())
                .username(accountDTO.getUsername()).build();

        accountDAO.save(account);
    }
}
