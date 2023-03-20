package com.iotstar.onlinetest.models.services.student;

import com.iotstar.onlinetest.DTO.StudentDTO;

public interface StudentService {
    public void createStudent(StudentDTO studentDTO);

    public void deleteStudent(int studentId);

    public StudentDTO getStudent(int studentId);

    public StudentDTO updateStudent(StudentDTO studentDTO);
}
