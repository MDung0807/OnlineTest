package com.iotstar.onlinetest.repositories;

import com.iotstar.onlinetest.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDAO extends JpaRepository<User, Long> {
    public Optional<User> getUserByUserId(Long userId);

}
