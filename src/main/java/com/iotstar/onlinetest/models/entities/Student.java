package com.iotstar.onlinetest.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "students")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int StudentId;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(columnDefinition = "LONGTEXT")
    private String avatar;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private int status;

    @Column(nullable = false)
    private LocalDateTime dateCreate;

    @OneToMany (mappedBy = "student")
    private Set<Account> account;

    @OneToMany(mappedBy = "student")
    Set<Scores> scores;

//    @ManyToMany
//    @JoinTable(
//            name = "student_answer",
//            inverseJoinColumns = @JoinColumn(name="answerId"),
//            joinColumns = @JoinColumn(name = "studentId"))
//    private Set<Answer> answers;

    @OneToMany(mappedBy = "student")
    private Set<Student_Answer> student_answers;
}
