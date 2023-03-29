package com.iotstar.onlinetest.DTOs.requests;

import lombok.Data;

@Data
public class TopicRequest {
    private Long subjectId;
    private Long id;
    private String name;
}
