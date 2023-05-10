package com.iotstar.onlinetest.models.statistics;

import jakarta.persistence.*;
import lombok.Data;

@Data
public class TestStatistic {
    private Long testId;
    private Long numberUserTest;
    private String testName;

}
