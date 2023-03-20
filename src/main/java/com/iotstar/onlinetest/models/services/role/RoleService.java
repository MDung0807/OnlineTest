package com.iotstar.onlinetest.models.services.role;

import com.iotstar.onlinetest.DTO.RoleDTO;

public interface RoleService {
    public void createRole(RoleDTO roleDTO);

    public void updateRole(RoleDTO roleDTO);

    public void deleteRole(int roleId);
}
