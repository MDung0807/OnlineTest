package com.iotstar.onlinetest.repositories;

import com.iotstar.onlinetest.models.Test;
import com.iotstar.onlinetest.models.Topic;
import com.iotstar.onlinetest.models.statistics.TestStatistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface TestDAO extends JpaRepository<Test, Long> {
    List<Test> findByTopics(Topic topic, Pageable pageable);

    @Query("select t.testId as testId, count(t.testId) as numberUserTest, t.testName as testName" +
            " from Test t join History h " +
            "on t.testId=h.test.testId group by t.testId")
    List<Map<String, Object>> TestStatistics (Pageable pageable);
}
