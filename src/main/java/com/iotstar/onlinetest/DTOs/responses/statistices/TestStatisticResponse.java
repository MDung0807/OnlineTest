package com.iotstar.onlinetest.DTOs.responses.statistices;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.iotstar.onlinetest.DTOs.responses.TestResponse;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TestStatisticResponse {
    private Long testId;
    private String testName;
    private Long numberUserTest;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime dateCreate;
    private int time;
}
