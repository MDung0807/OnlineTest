package com.iotstar.onlinetest.models.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SelectBeforeUpdate;

import java.util.List;
import java.util.Set;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int testId;

    @Column(nullable = false)
    private String testName;

    @Column(nullable = false)
    private int time;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private int status;


    @OneToMany(mappedBy = "test")
    Set<Scores> scores;

    @ManyToMany
    @JoinTable(
            name = "test_question",
            joinColumns = @JoinColumn(name = "testId"),
            inverseJoinColumns = @JoinColumn(name = "questionId")
    )
    private Set<Question>questions;

    @ManyToOne
    @JoinColumn(name = "subjectId")
    private Subject subject;



}
