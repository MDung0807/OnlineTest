package com.iotstar.onlinetest.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
//@Table(name = "reviewItems",
//        uniqueConstraints = @UniqueConstraint(columnNames = {"testId", "reviewId"}))

@Table(name = "reviewItems")
@Data
public class ReviewItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewItemId;

    @Column(nullable = false)
    private int status;

    @Column(nullable = false)
    private LocalDateTime dateReview;

    @Column(nullable = false)
    private int rating;

    @Column(nullable = false)
    private LocalDateTime dateUpdate;

    @Column
    private String comment;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "testId")
    private Test test;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "reviewId")
    private Review review;
}
