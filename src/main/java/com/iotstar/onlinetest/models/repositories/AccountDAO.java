package com.iotstar.onlinetest.models.repositories;

import com.iotstar.onlinetest.models.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountDAO extends JpaRepository<Account, Integer> {
    public Account getByUsername(String username);


}
