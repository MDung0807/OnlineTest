package com.iotstar.onlinetest.DTO;

import com.iotstar.onlinetest.models.entities.Role;
import com.iotstar.onlinetest.models.entities.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class AccountDTO {
    private int accountId;
    private String password;
    private String username;
    private Role role;
    private User user;
}
