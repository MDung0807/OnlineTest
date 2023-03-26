package com.iotstar.onlinetest.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@Table(name = "teachers")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @JoinColumn(name = "userId")
    @OneToOne
    private User user;

    @ManyToOne
    @JoinColumn(name = "subjectId")
    private Subject subject;

    @OneToMany(mappedBy = "teacher")
    private Set<Question> questions;

}
