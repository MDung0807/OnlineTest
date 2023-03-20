package com.iotstar.onlinetest.models.services.role;

import com.iotstar.onlinetest.DTO.RoleDTO;
import com.iotstar.onlinetest.models.entities.Role;
import com.iotstar.onlinetest.models.repositories.RoleDAO;
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
    public void deleteRole(int roleId) {

    }
}
