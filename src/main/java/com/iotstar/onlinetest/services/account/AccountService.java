package com.iotstar.onlinetest.services.account;

import com.iotstar.onlinetest.DTOs.AccountDTO;
import com.iotstar.onlinetest.DTOs.requests.AccountRequest;
import com.iotstar.onlinetest.models.User;

import java.util.List;

public interface AccountService{

    public List<AccountDTO> getAllAcc();
    public AccountDTO getAccByUsername(String username);

    public void createAccount(AccountDTO accountDTO);

    public void update(AccountRequest accountRequest);

    public void delAcc(AccountDTO accountDTO);
}
