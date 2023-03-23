package com.iotstar.onlinetest.services.role;

import com.iotstar.onlinetest.DTOs.RoleDTO;
import com.iotstar.onlinetest.models.Role;
import com.iotstar.onlinetest.repositories.RoleDAO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImp implements RoleService{

    @Autowired
    private RoleDAO roleDAO;
    @Autowired
    private ModelMapper mapper;

    private Role role;

    @Override
    public void createRole(RoleDTO roleDTO) {
        role = mapper.map(roleDTO, Role.class);
        role.setStatus(1);
        roleDAO.save(role);
    }

    @Override
    public void updateRole(RoleDTO roleDTO) {
        mapper.map(roleDTO, role);
        roleDAO.save(role);

    }

    @Override
    public List<RoleDTO> getAllRole() {
        List<RoleDTO> roleDTOS = new ArrayList<>();
        RoleDTO roleDTO;
        List<Role> roles = roleDAO.findAll();
        for (Role i: roles) {
            roleDTOS.add(mapper.map(i, RoleDTO.class));
        }
        return roleDTOS;
    }

    @Override
    public RoleDTO getRoleByRoleName(String roleName) {
        role = roleDAO.getByRoleName(roleName);
        return mapper.map(role, RoleDTO.class);
    }

    @Override
    public void deleteRole(RoleDTO roleDTO) {
        roleDTO.setStatus(0);
        updateRole(roleDTO);
    }
}
