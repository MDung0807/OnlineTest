package com.iotstar.onlinetest.services.role;

import com.iotstar.onlinetest.DTOs.RoleDTO;

import java.util.List;

public interface RoleService {
    public void createRole(RoleDTO roleDTO);

    public void updateRole(RoleDTO roleDTO);

    public void deleteRole(RoleDTO roleDTO);

    public List<RoleDTO> getAllRole();

    public RoleDTO getRoleByRoleName(String roleName);
}
