package com.iotstar.onlinetest.repositories.subject;

import com.iotstar.onlinetest.models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectDAO extends JpaRepository<Subject, Long> {

    public boolean existsByName(String name);
    public Subject findByName(String name);
}
