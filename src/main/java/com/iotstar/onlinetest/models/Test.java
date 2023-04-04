package com.iotstar.onlinetest.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table
@Data
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

    @ManyToMany
    @JoinTable(
            name = "TestTopic",
            joinColumns = @JoinColumn(name = "testId"),
            inverseJoinColumns = @JoinColumn(name = "topicId")
    )
    @JsonIgnore
    private List<Topic> topics;

    @ManyToMany
    @JoinTable(
            name = "TestQuestion",
            joinColumns = @JoinColumn(name = "testId"),
            inverseJoinColumns = @JoinColumn(name = "questionId")
    )
    @JsonIgnore
    private List<Question> questions;

}
