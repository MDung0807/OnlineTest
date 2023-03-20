package com.iotstar.onlinetest.models.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Entity
@Data
public class Student_Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "questionId")
    private Question question;

    @ManyToOne
    @JoinColumn(name = "answerId")
    private Answer answer;

    @ManyToOne
    @JoinColumn(name = "studentId")
    private Student student;

    @Column(nullable = false)
    private LocalDateTime time;

}
