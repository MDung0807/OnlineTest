package com.iotstar.onlinetest.services.role;

import com.iotstar.onlinetest.DTOs.requests.RoleRequest;
import com.iotstar.onlinetest.DTOs.responses.RoleResponse;
import com.iotstar.onlinetest.models.Role;
import com.iotstar.onlinetest.repositories.RoleDAO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.ranges.Range;

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
    public void createRole(RoleRequest roleRequest) {
        role = mapper.map(roleRequest, Role.class);
        role.setStatus(1);
        roleDAO.save(role);
    }

    @Override
    public void updateRole(RoleRequest roleRequest) {
        role = roleDAO.getByRoleName(roleRequest.getRoleName());
        roleDAO.save(role);
    }

    @Override
    public List<RoleResponse> getAllRole() {
        List<RoleResponse> roleResponses = new ArrayList<>();
        List<Role> roles = roleDAO.findAll();
        for (Role i: roles) {
            roleResponses.add(mapper.map(i, RoleResponse.class));
        }
        return roleResponses;
    }

    @Override
    public RoleResponse getRoleByRoleName(String roleName) {
        role = roleDAO.getByRoleName(roleName);
        return mapper.map(role, RoleResponse.class);
    }

    @Override
    public void deleteRole(RoleRequest roleRequest) {
        role = roleDAO.getByRoleName(roleRequest.getRoleName());
        role.setStatus(0);
        roleDAO.save(role);
    }
}
