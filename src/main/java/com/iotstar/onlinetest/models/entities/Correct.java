package com.iotstar.onlinetest.models.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "corrects")
public class Correct {
    @Id
    private int questionId;

    @OneToOne(mappedBy = "correct")
    private Question question;

    @OneToMany(mappedBy = "correct")
    private Set<Answer> answers;
}
