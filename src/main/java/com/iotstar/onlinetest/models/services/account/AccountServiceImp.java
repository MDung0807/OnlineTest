package com.iotstar.onlinetest.models.services.account;

import com.iotstar.onlinetest.DTO.AccountDTO;
import com.iotstar.onlinetest.models.entities.Account;
import com.iotstar.onlinetest.models.repositories.AccountDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountServiceImp implements AccountService{
    @Autowired
    private AccountDAO accountDAO;

    private AccountDTO accountDTO;
    private Account account;

    @Override
    public AccountDTO getAccByUsername(String username) {
        account = accountDAO.getByUsername(username);
        accountDTO = AccountDTO.builder()
                .accountId(account.getAccountId())
                .username(account.getUsername())
                .password(account.getPassword())
                .role(account.getRole())
                .user(account.getUser())
                .build();
        return accountDTO;
    }

    @Override
    public AccountDTO getByUserName(String name) {
        account = accountDAO.getByUsername(name);
        accountDTO = AccountDTO.builder()
                .accountId(account.getAccountId())
                .username(account.getUsername())
                .password(account.getPassword())
                .role(account.getRole())
                .user(account.getUser())
                .build();
        return accountDTO;
    }

    @Override
    @Transactional
    public void createAccount(AccountDTO accountDTO){
        String err = null;
        account = Account.builder()
                .role(accountDTO.getRole())
                .user(accountDTO.getUser())
                .password(accountDTO.getPassword())
                .username(accountDTO.getUsername()).build();
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
                .user(accountDTO.getUser())
                .password(accountDTO.getPassword())
                .username(accountDTO.getUsername()).build();

        accountDAO.save(account);
    }
}
