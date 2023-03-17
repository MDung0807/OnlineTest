package com.iotstar.onlinetest.models.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name = "subjects")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int subjectId;

    @Column(nullable = false)
    private String name;

    @ManyToMany
    @JoinTable(name = "subject_grade",
    joinColumns = @JoinColumn(name = "subjectId"),
    inverseJoinColumns = @JoinColumn(name = "gradeId"))
    private List<Grade> grades;

    @OneToMany
    @JoinColumn(name = "subjectId")
    private List<Test> tests;
}
