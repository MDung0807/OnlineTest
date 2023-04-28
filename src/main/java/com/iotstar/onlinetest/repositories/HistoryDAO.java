package com.iotstar.onlinetest.repositories;

import com.iotstar.onlinetest.models.History;
import com.iotstar.onlinetest.models.Test;
import com.iotstar.onlinetest.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HistoryDAO extends JpaRepository<History, Long> {
    public Optional<History> findByTestAndUser(Test test, User user);
}
