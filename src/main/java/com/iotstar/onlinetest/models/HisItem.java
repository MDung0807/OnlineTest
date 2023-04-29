package com.iotstar.onlinetest.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table
public class HisItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "questionId")
    private Question question;

    @ManyToOne
    @JoinColumn(name = "answerId")
    private Answer answer;
}
