package com.iotstar.onlinetest.services.account;

import com.iotstar.onlinetest.DTOs.RegisterForm.*;
import com.iotstar.onlinetest.DTOs.RoleDTO;

import java.util.List;

public interface AccountService{

    public List<AccountDTO> getAllAcc();
    public AccountDTO getAccByUsername(String username);

    public void createAccount(AccountDTO accountDTO, UserDTO userDTO,RoleDTO roleDTO);

    public void update(AccountDTO accountDTO);

    public void delAcc(AccountDTO accountDTO);
}
