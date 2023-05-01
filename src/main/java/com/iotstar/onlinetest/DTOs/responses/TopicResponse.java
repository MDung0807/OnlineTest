package com.iotstar.onlinetest.DTOs.responses;

import com.iotstar.onlinetest.models.Subject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class TopicResponse {
    private int id;
    private String name;
//    private Subject subject;
    private String image;
    private int status;
}
