package com.iotstar.onlinetest.utils;

import com.iotstar.onlinetest.security.services.AccountDetailsImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthUtils {

    @Autowired
    private ModelMapper mapper;
    public AccountDetailsImpl getAccountDetail(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AccountDetailsImpl accountDetails = mapper.map(authentication.getPrincipal(), AccountDetailsImpl.class);
        return accountDetails;
    }
}
