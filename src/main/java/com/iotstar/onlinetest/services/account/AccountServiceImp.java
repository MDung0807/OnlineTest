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
import com.iotstar.onlinetest.utils.FileUtils;
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
    @Autowired
    private FileUtils fileUtils;


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
        if (existsByUsername(accountDTO.getUsername()))
            throw new ResourceExistException(AppConstant.USERNAME_EXISTS);
        account = mapper.map(accountDTO, Account.class);
        role = roleDAO.getByRoleName(accountDTO.getRoleName()).orElseThrow(()->
                new ResourceNotFoundException(AppConstant.ROLE_NOTFOUND + accountDTO.getRoleName()));
        account.setRole(role);
        account.setPassword(encoder.encode(accountDTO.getPassword()));
        account.setStatus(1);
        String err = null;
        try {
            account = accountDAO.save(account);
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
            account.setPassword(encoder.encode(accountRequest.getPassword()));
            accountDAO.save(account);
        }
    }

    @Override
    public AccountDTO updateRole(Long userId, String roleName){
        user = userDAO.findById(userId).orElseThrow(()->
                new ResourceNotFoundException(AppConstant.USER_NOTFOUND+userId));
        account = accountDAO.findByUser_UserId(userId).orElseThrow(()->
                new ResourceNotFoundException(AppConstant.USER_NOTFOUND+userId));

        role = roleDAO.getByRoleName(roleName).orElseThrow(()->
                new ResourceNotFoundException(AppConstant.ROLE_NOTFOUND+roleName));
        account.setRole(role);
        account = accountDAO.save(account);
        accountDTO = new AccountDTO();
        accountDTO.setAccountId(account.getAccountId());
        accountDTO.setRoleName(account.getRole().getRoleName());
        accountDTO.setUsername(account.getUsername());
        accountDTO.setUser(account.getUser());
        accountDTO.setPassword(account.getPassword());
        return accountDTO;
    }

    @Override
    public Boolean existsByUsername(String username) {
        return accountDAO.existsByUsername(username);
    }
}
