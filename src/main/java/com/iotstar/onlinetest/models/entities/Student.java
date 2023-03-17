package com.iotstar.onlinetest.models.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "students")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Student {
    @Id
    @GeneratedValue
    private int StudentId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @OneToMany (mappedBy = "student")
    private Set<Account> account;

    @OneToMany(mappedBy = "student")
    Set<Scores> scores;


}
