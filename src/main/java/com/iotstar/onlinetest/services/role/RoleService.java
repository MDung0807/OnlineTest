package com.iotstar.onlinetest.services.role;

import com.iotstar.onlinetest.DTO.RoleDTO;

public interface RoleService {
    public void createRole(RoleDTO roleDTO);

    public void updateRole(RoleDTO roleDTO);

    public void deleteRole(int roleId);

    public RoleDTO getAllRole();

    public RoleDTO getRoleByRoleName(String roleName);
}
