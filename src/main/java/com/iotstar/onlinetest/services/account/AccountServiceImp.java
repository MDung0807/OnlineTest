package com.iotstar.onlinetest.services.account;

import com.iotstar.onlinetest.DTOs.AccountDTO;
import com.iotstar.onlinetest.DTOs.requests.AccountRequest;
import com.iotstar.onlinetest.models.Account;
import com.iotstar.onlinetest.models.Role;
import com.iotstar.onlinetest.models.User;
import com.iotstar.onlinetest.repositories.AccountDAO;
import com.iotstar.onlinetest.repositories.RoleDAO;
import com.iotstar.onlinetest.repositories.UserDAO;
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
    @Autowired
    private RoleDAO roleDAO;
    @Autowired
    private UserDAO userDAO;


    @Override
    @Transactional
    public void delAcc(AccountDTO accountDTO){

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
      return null;
    }

    @Override
    @Transactional
    public void createAccount(AccountDTO accountDTO){
        account = mapper.map(accountDTO, Account.class);
        account.setStatus(1);
        role = roleDAO.getByRoleName(accountDTO.getRoleName());
        account.setRole(role);
        String err = null;
        try {
            accountDAO.save(account);
        }
        catch (Exception ex){
           err = ex.getMessage();
        }
    }

    @Override
    @Transactional
    public void update(AccountRequest accountRequest){
        account = accountDAO.getByUsername(accountRequest.getUserName());
        account.setPassword(accountRequest.getPassword());
        accountDAO.save(account);
    }
}
