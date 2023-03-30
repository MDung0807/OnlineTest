package com.iotstar.onlinetest.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "testId")
    @JsonIgnore
    private Test test;

    @ManyToOne
    @JoinColumn(name = "questionId")
    @JsonIgnore
    private Question question;

    @ManyToOne
    @JoinColumn(name = "answerId")
    @JsonIgnore
    private Answer answer;

    @ManyToOne
    @JoinColumn(name = "userId")
    @JsonIgnore
    private User user;

    @Column(nullable = false)
    @JsonIgnore
    private LocalDateTime time;

}
