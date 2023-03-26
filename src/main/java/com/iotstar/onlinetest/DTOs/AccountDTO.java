package com.iotstar.onlinetest.DTOs;

import com.iotstar.onlinetest.models.Role;
import com.iotstar.onlinetest.models.User;
import lombok.Data;

@Data
public class AccountDTO {

    private Long accountId;
    private String username;
    private String password;
    private User user;
    private String roleName;
}
