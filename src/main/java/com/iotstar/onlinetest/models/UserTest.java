package com.iotstar.onlinetest.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class UserTest implements Serializable {
    @Column(nullable = false)
    private Long userId;


    @Column(nullable = false)
    private Long testId;
}
