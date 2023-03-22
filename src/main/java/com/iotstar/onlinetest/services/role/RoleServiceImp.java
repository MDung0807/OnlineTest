package com.iotstar.onlinetest.services.role;

import com.iotstar.onlinetest.DTOs.RoleDTO;
import com.iotstar.onlinetest.models.Role;
import com.iotstar.onlinetest.repositories.RoleDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImp implements RoleService{

    @Autowired
    private RoleDAO roleDAO;

    private Role role;

    @Override
    public void createRole(RoleDTO roleDTO) {
        role = Role.builder()
                .status(1)
                .roleName(roleDTO.getRoleName())
                .build();

        roleDAO.save(role);
    }

    @Override
    public void updateRole(RoleDTO roleDTO) {
        role = Role.builder()
                .status(roleDTO.getStatus())
                .roleId(roleDTO.getRoleId())
                .roleName(roleDTO.getRoleName()).build();
        roleDAO.save(role);

    }

    @Override
    public List<RoleDTO> getAllRole() {
        List<RoleDTO> roleDTOS = new ArrayList<>();
        RoleDTO roleDTO;
        List<Role> roles = roleDAO.findAll();
        for (Role i: roles) {
            roleDTO = RoleDTO.builder()
                    .roleId(i.getRoleId())
                    .roleName(i.getRoleName())
                    .status(i.getStatus())
                    .accounts(i.getAccounts().stream().toList())
                    .build();
            roleDTOS.add(roleDTO);
        }
        return roleDTOS;
    }

    @Override
    public RoleDTO getRoleByRoleName(String roleName) {
        role = roleDAO.getByRoleName(roleName);
        return RoleDTO.builder()
                .roleId(role.getRoleId())
                .status(role.getStatus())
                .roleName(role.getRoleName())
                .accounts(role.getAccounts().stream().toList())
                .build();
    }

    @Override
    public void deleteRole(RoleDTO roleDTO) {
        roleDTO.setStatus(0);
        updateRole(roleDTO);
    }
}
