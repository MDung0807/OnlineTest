package com.iotstar.onlinetest.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Entity
@Table(name="questions")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    @Column(nullable = false)
    private String question;

    @Column(columnDefinition = "LongText")
    private String questionImage;

    @Column
    private int status;

    @OneToMany(mappedBy = "question")
    private List<Answer> answers;


    @ManyToOne
    @JoinColumn(name = "topicId")
    @JsonIgnore
    private Topic topic;

    @ManyToOne
    @JoinColumn(name = "techerId")
    @JsonIgnore
    private User user;
}
