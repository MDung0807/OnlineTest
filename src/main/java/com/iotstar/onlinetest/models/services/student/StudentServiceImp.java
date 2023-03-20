package com.iotstar.onlinetest.models.services.student;


import com.iotstar.onlinetest.DTO.StudentDTO;
import com.iotstar.onlinetest.models.entities.Student;
import com.iotstar.onlinetest.models.repositories.StudentDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentServiceImp implements StudentService{
    @Autowired
    private StudentDAO studentDAO;
    private Student student;


    @Override
    @Transactional
    public void createStudent(StudentDTO studentDTO) {
        student = Student.builder()
                .firstName(studentDTO.getFirstName())
                .lastName(studentDTO.getLastName())
                .phoneNumber(studentDTO.getPhoneNumber())
                .avatar(studentDTO.getAvatar())
                .email(studentDTO.getEmail())
                .status(studentDTO.getStatus()).build();

        studentDAO.save(student);
    }

    @Override
    public void deleteStudent(int studentId) {
//        studentDAO.save()
    }

    @Override
    public StudentDTO getStudent(int studentId) {
        return null;
    }

    @Override
    public StudentDTO updateStudent(StudentDTO studentDTO) {
        return null;
    }
}
