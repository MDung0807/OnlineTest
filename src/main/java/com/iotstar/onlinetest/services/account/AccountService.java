package com.iotstar.onlinetest.services.account;

import com.iotstar.onlinetest.DTOs.AccountDTO;
import com.iotstar.onlinetest.DTOs.requests.AccountRequest;
import com.iotstar.onlinetest.DTOs.requests.UserProfileRequest;
import com.iotstar.onlinetest.models.User;

import java.util.List;
import java.util.Optional;

public interface AccountService{

    List<AccountDTO> getAllAcc();
    Optional<AccountDTO> getAccByUsername(String username);

    void createAccount(AccountDTO accountDTO);

    void update(AccountRequest accountRequest);

    void delAcc(AccountDTO accountDTO);
    AccountDTO updateRole(Long userId, String roleName);
    Boolean existsByUsername(String username);
}
