package com.iotstar.onlinetest.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long testId;

    @Column(nullable = false)
    private String testName;

    @Column(nullable = false)
    private int time;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private int status;

    @Column(nullable = false)
    private LocalDateTime dateCreate;

    @OneToMany(mappedBy = "test")
    @JsonIgnore
    List<Scores> scores;

    @ManyToOne
    @JoinColumn(name = "topicId")
    @JsonIgnore
    private Topic topic;

}
