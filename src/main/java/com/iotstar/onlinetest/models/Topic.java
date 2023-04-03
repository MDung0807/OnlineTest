package com.iotstar.onlinetest.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String image;

    @Column
    private int status;

    @ManyToOne
    @JoinColumn( name = "subjectId")
    @JsonIgnore
    private Subject subject;

    @OneToMany(mappedBy = "topic")
    @JsonIgnore
    private List<Question> questions;

    @OneToMany(mappedBy = "topic")
    @JsonIgnore
    private List<Test> tests;
}
