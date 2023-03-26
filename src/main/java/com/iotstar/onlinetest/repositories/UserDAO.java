package com.iotstar.onlinetest.repositories;

import com.iotstar.onlinetest.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends JpaRepository<User, Long> {
    public User getUserByUserId(Long userId);

}
