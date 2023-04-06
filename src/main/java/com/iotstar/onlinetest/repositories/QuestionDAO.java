package com.iotstar.onlinetest.repositories;

import com.iotstar.onlinetest.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionDAO extends JpaRepository<Question, Long> {
    public Optional<List<Question>> findByTopicId(Long id);

    public Optional<List<Question>> findByUserUserId(Long id);

}
