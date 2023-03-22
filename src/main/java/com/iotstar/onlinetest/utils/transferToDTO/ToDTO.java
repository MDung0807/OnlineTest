package com.iotstar.onlinetest.utils.transferToDTO;

import com.iotstar.onlinetest.DTOs.RegisterForm.*;
import com.iotstar.onlinetest.models.Account;
import com.iotstar.onlinetest.models.User;

public class ToDTO {
    public static UserDTO toUserDTO(User user){
        return UserDTO.builder()
                .userId(user.getUserId())
                .avatar(user.getAvatar())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .status(user.getStatus())
                .dateCreate(user.getDateCreate())
                .account(user.getAccount()).build();
    }

    public static AccountDTO toAccDTO(Account account){
        return AccountDTO.builder()
                .accountId(account.getAccountId())
                .username(account.getUsername())
                .password(account.getPassword())
                .role(account.getRole())
//                .user(account.getUser())
                .status(account.getStatus()).build();
    }
}
