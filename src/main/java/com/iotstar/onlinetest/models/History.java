package com.iotstar.onlinetest.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "historis")
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "testId")
    @JsonIgnore
    private Test test;

    @OneToMany
    @JoinColumn(name = "questionId")
    @JsonIgnore
    private List<Question> question;

    @OneToMany
    @JoinColumn(name = "answerId")
    @JsonIgnore
    private List<Answer> answer;

    @ManyToOne
    @JoinColumn(name = "userId")
    @JsonIgnore
    private User user;

    @Column(nullable = false)
    @JsonIgnore
    private LocalDateTime time;

}
