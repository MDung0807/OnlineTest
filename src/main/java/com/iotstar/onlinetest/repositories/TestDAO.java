package com.iotstar.onlinetest.repositories;

import com.iotstar.onlinetest.models.Test;
import com.iotstar.onlinetest.models.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface TestDAO extends JpaRepository<Test, Long> {
    public List<Test> findByTopics(Topic topic);
}
