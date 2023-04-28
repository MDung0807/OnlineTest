package com.iotstar.onlinetest.repositories;

import com.iotstar.onlinetest.models.Score;
import com.iotstar.onlinetest.models.Test;
import com.iotstar.onlinetest.models.User;
import com.iotstar.onlinetest.models.UserTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScoreDAO extends JpaRepository<Score, Long> {
    public Optional<Score> findByTestAndUser(Test test, User user);
}
