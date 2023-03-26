package com.iotstar.onlinetest.repositories;

import com.iotstar.onlinetest.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDAO extends JpaRepository<Role, Long> {
    public Role getByRoleName(String roleName);
}
