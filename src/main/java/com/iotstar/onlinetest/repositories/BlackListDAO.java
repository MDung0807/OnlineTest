package com.iotstar.onlinetest.repositories;

import com.iotstar.onlinetest.models.BlackList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlackListDAO extends JpaRepository<BlackList, Long> {
    public boolean existsByJwt(String jwt);
}
