package com.iotstar.onlinetest.repositories;

import com.iotstar.onlinetest.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionDAO extends JpaRepository<Question, Long> {
}
