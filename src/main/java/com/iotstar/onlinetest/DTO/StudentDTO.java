package com.iotstar.onlinetest.DTO;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class StudentDTO {
    private int studentId;
    private String email;
    private String name;
}
