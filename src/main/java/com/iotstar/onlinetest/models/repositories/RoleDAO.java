package com.iotstar.onlinetest.models.repositories;

import com.iotstar.onlinetest.models.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDAO extends JpaRepository<Role, Integer> {
    public Role getByRoleName(String roleName);
}
