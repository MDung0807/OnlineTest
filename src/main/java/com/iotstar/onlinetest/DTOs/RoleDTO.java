package com.iotstar.onlinetest.DTOs;

import com.iotstar.onlinetest.models.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO {
    private int roleId;
    private String roleName;
    private int status;
    private List<Account>accounts;
}
