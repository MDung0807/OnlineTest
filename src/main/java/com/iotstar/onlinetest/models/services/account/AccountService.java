package com.iotstar.onlinetest.models.services.account;

import com.iotstar.onlinetest.DTO.AccountDTO;

public interface AccountService{
    public AccountDTO getAccByUsername(String username);

    public void createAccount(AccountDTO accountDTO);

    public void update(AccountDTO accountDTO);

    public void delAcc(AccountDTO accountDTO);
}
