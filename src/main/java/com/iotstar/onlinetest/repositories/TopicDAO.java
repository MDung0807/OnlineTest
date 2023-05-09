package com.iotstar.onlinetest.repositories;

import com.iotstar.onlinetest.models.Subject;
import com.iotstar.onlinetest.models.Test;
import com.iotstar.onlinetest.models.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TopicDAO extends JpaRepository<Topic, Long> {
    public Topic findByTests(Test test);
    public List<Topic> findBySubject(Subject subject, Pageable pageable);
}
