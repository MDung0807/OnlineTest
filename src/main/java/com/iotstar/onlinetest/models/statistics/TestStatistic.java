package com.iotstar.onlinetest.models.statistics;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TestStatistic {
    private Long testId;
    private Long numberUserTest;
    private String testName;
    private LocalDateTime dateCreate;
    private int time;
}
