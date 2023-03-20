package com.iotstar.onlinetest.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class User_Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "questionId")
    private Question question;

    @ManyToOne
    @JoinColumn(name = "answerId")
    private Answer answer;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @Column(nullable = false)
    private LocalDateTime time;

}
