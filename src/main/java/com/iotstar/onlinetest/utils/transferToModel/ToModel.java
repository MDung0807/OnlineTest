package com.iotstar.onlinetest.utils.transferToModel;

import com.iotstar.onlinetest.DTOs.RegisterForm.*;
import com.iotstar.onlinetest.DTOs.RoleDTO;
import com.iotstar.onlinetest.models.Role;
import com.iotstar.onlinetest.models.User;

public class ToModel {
    public static User toUser(UserDTO userDTO){
        return User.builder()
                .userId(userDTO.getUserId())
                .avatar(userDTO.getAvatar())
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .email(userDTO.getEmail())
                .phoneNumber(userDTO.getPhoneNumber())
                .status(userDTO.getStatus())
                .dateCreate(userDTO.getDateCreate())
                .account(userDTO.getAccount()).build();
    }

    public static Role toRole(RoleDTO roleDTO){
        return Role.builder()
                .roleId(roleDTO.getRoleId())
                .roleName(roleDTO.getRoleName()).build();
    }
}
