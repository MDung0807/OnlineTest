package com.iotstar.onlinetest.DTOs.responses;

import com.iotstar.onlinetest.models.Account;
import lombok.Data;

import java.util.List;

@Data
public class RoleResponse {
    private int roleId;
    private String roleName;
    private int status;
    private List<Account> accounts;
}
