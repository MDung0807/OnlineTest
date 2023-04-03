package com.iotstar.onlinetest.DTOs.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class TopicRequest {
    private Long subjectId;
    private Long id;
    @Size(max = 50, message = "name topic size have length less 50")
    @NotBlank
    private String name;
    private MultipartFile image;
    private int status;
}
