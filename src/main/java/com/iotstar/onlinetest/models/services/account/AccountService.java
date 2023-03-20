package com.iotstar.onlinetest.models.services.account;

import com.iotstar.onlinetest.DTO.AccountDTO;

public interface AccountService{
    public AccountDTO getAccByUsername(String username);

    public AccountDTO getByStudentName(String name);

    public void createAccount(AccountDTO accountDTO);

    public void update(AccountDTO accountDTO);
}
