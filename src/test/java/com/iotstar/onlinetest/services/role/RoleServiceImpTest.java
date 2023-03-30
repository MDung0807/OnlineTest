package com.iotstar.onlinetest.services.role;

import com.iotstar.onlinetest.models.Role;
import com.iotstar.onlinetest.repositories.RoleDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class RoleServiceImpTest {
    @Autowired
    private RoleDAO roleDAO;

    private Role role;

    @Test
    void createRole() {
        role = Role.builder().roleName("teacher")
                .status(1)
                .build();
        roleDAO.save(role);

    }

    @Test
    void updateRole() {
    }

    @Test
    void getAllRole() {
    }

    @Test
    void getRoleByRoleName() {
    }

    @Test
    void deleteRole() {
    }
}