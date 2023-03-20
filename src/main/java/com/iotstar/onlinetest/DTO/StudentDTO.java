package com.iotstar.onlinetest.DTO;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class StudentDTO {
    private int studentId;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String avatar;
    private int status;
}
