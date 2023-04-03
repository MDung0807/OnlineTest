package com.iotstar.onlinetest.repositories;

import com.iotstar.onlinetest.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleDAO extends JpaRepository<Role, Long> {
    public Optional<Role> getByRoleName(String roleName);
    public boolean existsByRoleName(String roleName);
}
