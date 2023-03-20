package com.iotstar.onlinetest.models.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;
import java.util.Set;

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

    @Column(nullable = false)
    private int status;

    @OneToMany(mappedBy = "subject")
    private Set<Test> tests;

    @OneToMany(mappedBy = "subject")
    private Set<Teacher> teachers;

    @OneToMany(mappedBy = "subject")
    private Set<Question> questions;

}
