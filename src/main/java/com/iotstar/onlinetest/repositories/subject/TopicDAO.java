package com.iotstar.onlinetest.repositories.subject;

import com.iotstar.onlinetest.models.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicDAO extends JpaRepository<Topic, Long> {

}
