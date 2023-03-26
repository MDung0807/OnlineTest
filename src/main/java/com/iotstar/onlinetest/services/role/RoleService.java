package com.iotstar.onlinetest.services.role;

import com.iotstar.onlinetest.DTOs.requests.RoleRequest;
import com.iotstar.onlinetest.DTOs.responses.RoleResponse;

import java.util.List;

public interface RoleService {
    public void createRole(RoleRequest roleRequest);

    public void updateRole(RoleRequest roleRequest);

    public void deleteRole(RoleRequest roleRequest);

    public List<RoleResponse> getAllRole();

    public RoleResponse getRoleByRoleName(String roleName);
}
