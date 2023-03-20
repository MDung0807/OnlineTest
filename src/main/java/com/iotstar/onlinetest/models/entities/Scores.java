package com.iotstar.onlinetest.models.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Scores {
    @EmbeddedId
    private UserTest id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "userId")
    User user;

    @ManyToOne
    @MapsId("testId")
    @JoinColumn(name = "testId")
    Test test;

    private int scores;

    private LocalDateTime dateTest;
}
