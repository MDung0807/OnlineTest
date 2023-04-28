package com.iotstar.onlinetest.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "scores")

public class Score {
//    @EmbeddedId
//    private UserTest id;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userId")
    @JsonIgnore
    User user;

    @ManyToOne
    @JoinColumn(name = "testId")
    @JsonIgnore
    Test test;

    private String scores;

    private LocalDateTime dateTest;
}
