package com.iotstar.onlinetest.models.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "grades")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int gradeId;

    @Column(nullable = false)
    private String gradeName;

    @ManyToMany
    @JoinTable(name = "subject_grade",
    joinColumns = @JoinColumn(name = "gradeId"),
    inverseJoinColumns = @JoinColumn(name = "subjectId"))
    private List<Subject> subjects;
}
