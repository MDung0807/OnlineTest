package com.iotstar.onlinetest.services.role;

import com.iotstar.onlinetest.DTO.RoleDTO;
import com.iotstar.onlinetest.models.Role;
import com.iotstar.onlinetest.repositories.RoleDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImp implements RoleService{

    @Autowired
    private RoleDAO roleDAO;

    private Role role;

    @Override
    public void createRole(RoleDTO roleDTO) {
        role = Role.builder()
                .roleName(roleDTO.getRoleName()).build();
    }

    @Override
    public void updateRole(RoleDTO roleDTO) {

    }

    @Override
    public RoleDTO getAllRole() {
        return null;
    }

    @Override
    public RoleDTO getRoleByRoleName(String roleName) {
        return null;
    }

    @Override
    public void deleteRole(int roleId) {

    }
}
