package com.iotstar.onlinetest.DTOs;

import com.iotstar.onlinetest.models.Account;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RoleDTO {
    private int roleId;
    private String roleName;
    private int status;
    private List<Account>accounts;
}
