package com.iotstar.onlinetest.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "histories")
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHis;

    @Column(nullable = true)
    private String totalCorrect;

    @Column(nullable = false)
    private float score;

    @ManyToOne
    @JoinColumn(name = "testId")
    @JsonIgnore
    private Test test;

    @OneToMany
    @JoinColumn(name = "items")
    @JsonIgnore
    private List<HisItem> hisItems;

    @ManyToOne
    @JoinColumn(name = "userId")
    @JsonIgnore
    private User user;

    @Column(nullable = false)
    @JsonIgnore
    private LocalDateTime time;

}
