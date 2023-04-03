package com.iotstar.onlinetest.services.account;

import com.iotstar.onlinetest.DTOs.AccountDTO;
import com.iotstar.onlinetest.DTOs.requests.AccountRequest;
import com.iotstar.onlinetest.exceptions.ResourceExistException;
import com.iotstar.onlinetest.exceptions.ResourceNotFoundException;
import com.iotstar.onlinetest.models.Account;
import com.iotstar.onlinetest.models.Role;
import com.iotstar.onlinetest.models.User;
import com.iotstar.onlinetest.repositories.AccountDAO;
import com.iotstar.onlinetest.repositories.RoleDAO;
import com.iotstar.onlinetest.repositories.UserDAO;
import com.iotstar.onlinetest.utils.AppConstant;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Autowired
    private PasswordEncoder encoder;



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
    public Optional <AccountDTO>getAccByUsername(String username) {
     return null;
    }

    @Override
    @Transactional
    public void createAccount(AccountDTO accountDTO){
        account = mapper.map(accountDTO, Account.class);
        role = roleDAO.getByRoleName(accountDTO.getRoleName()).orElseThrow(()->
                new ResourceNotFoundException(AppConstant.ROLE_NOTFOUND + accountDTO.getRoleName()));
        account.setRole(role);
        account.setPassword(encoder.encode(accountDTO.getPassword()));
        account.setStatus(1);
        String err = null;
        try {
            accountDAO.save(account);
        }
        catch (Exception ex){
          throw new ResourceExistException(AppConstant.ACCOUNT_EXIST);
        }
    }

    @Override
    @Transactional
    public void update(AccountRequest accountRequest){
        account = accountDAO.findByUsername(accountRequest.getUsername()).orElseThrow(()->
                new ResourceNotFoundException(AppConstant.INFO_ACC_NOTFOUND + accountRequest.getUsername()));

        if (account.getUser().getEmail().equals(accountRequest.getEmail())
        && account.getUser().getPhoneNumber().equals(accountRequest.getPhoneNumber())) {
            accountDAO.save(account);
        }
    }
}
