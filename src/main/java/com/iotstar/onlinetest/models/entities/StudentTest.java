package com.iotstar.onlinetest.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class StudentTest implements Serializable {
    @Column(nullable = false)
    private int studentId;


    @Column(nullable = false)
    private int testId;
}
