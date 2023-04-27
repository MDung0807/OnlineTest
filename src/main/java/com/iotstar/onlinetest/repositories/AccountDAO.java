package com.iotstar.onlinetest.repositories;

import com.iotstar.onlinetest.models.Account;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountDAO extends JpaRepository<Account, Long> {
//    public Account getByUsername(String username);

    public Optional<Account> findByUsername(String username);
    public Optional<Account> findByUser_UserId(Long userId);
    public Boolean existsByUsername(String username);


}
