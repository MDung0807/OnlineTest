package com.iotstar.onlinetest.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
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

    @Column(nullable = false)
    private LocalDateTime dateCreate;

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
