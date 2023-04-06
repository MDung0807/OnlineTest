package com.iotstar.onlinetest.repositories;

import com.iotstar.onlinetest.DTOs.AnswerDTO;
import com.iotstar.onlinetest.models.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AnswerDAO extends JpaRepository<Answer, Long> {
}
