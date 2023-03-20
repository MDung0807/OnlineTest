package com.iotstar.onlinetest.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name="questions")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int questionId;

    @Column(nullable = false)
    private String question;

    @Column(columnDefinition = "LongText")
    private String questionImage;

    @Column
    private int status;

    @ManyToMany(mappedBy = "questions")
    private Set<Test> tests;

    @OneToMany(mappedBy = "question")
    private Set<Answer> answers;


    @OneToMany(mappedBy = "question")
    private Set<User_Answer> user_answers;

    @ManyToOne
    @JoinColumn(name = "subjectId")
    private Subject subject;
}
