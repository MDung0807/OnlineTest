package com.iotstar.onlinetest.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(columnDefinition = "LONGTEXT")
    private String avatar;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private int status;

    @Column(nullable = false)
    private LocalDateTime dateCreate;

    @OneToOne(mappedBy = "user")
    @JsonIgnore
    private Account account;

    @OneToMany(mappedBy = "user")
    private List<Scores> scores;


    @OneToMany(mappedBy = "user")
    private List<History> histories;

    @ManyToOne
    @JoinColumn(name = "subjectId")
    private Subject subject;

    @OneToMany(mappedBy = "user")
    private List<Question> questions;
}
