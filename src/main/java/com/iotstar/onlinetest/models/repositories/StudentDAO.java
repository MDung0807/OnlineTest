package com.iotstar.onlinetest.models.repositories;

import com.iotstar.onlinetest.models.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentDAO extends JpaRepository<Student, Integer> {
    public Student getByName(String name);
}
