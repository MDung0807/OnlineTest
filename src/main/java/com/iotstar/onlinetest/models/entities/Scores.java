package com.iotstar.onlinetest.models.entities;

import jakarta.persistence.*;

@Entity
public class Scores {
    @EmbeddedId
    private StudentTest id;

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "studentId")
    Student student;

    @ManyToOne
    @MapsId("testId")
    @JoinColumn(name = "testId")
    Test test;

    private int scores;
}
