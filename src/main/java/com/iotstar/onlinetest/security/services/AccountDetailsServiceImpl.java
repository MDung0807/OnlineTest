package com.iotstar.onlinetest.security.services;

import com.iotstar.onlinetest.exceptions.StateUserNotExists;
import com.iotstar.onlinetest.models.Account;
import com.iotstar.onlinetest.repositories.AccountDAO;
import com.iotstar.onlinetest.services.account.AccountService;
import com.iotstar.onlinetest.statval.EAccount;
import com.iotstar.onlinetest.utils.AppConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AccountDetailsServiceImpl  implements UserDetailsService {
    @Autowired
    private AccountDAO accountDAO;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountDAO.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(EAccount.INFO_ACC_NOTFOUND.getDes()));
        if (account.getStatus()== 0)
            throw new StateUserNotExists(EAccount.ACCOUNT_LOCKED.getDes());
        return AccountDetailsImpl.create(account);
    }


}
