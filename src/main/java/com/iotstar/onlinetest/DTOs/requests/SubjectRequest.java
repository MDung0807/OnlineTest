package com.iotstar.onlinetest.DTOs.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.apache.commons.lang3.mutable.Mutable;
import org.springframework.web.multipart.MultipartFile;

@Data
public class SubjectRequest {
    private Long subjectId;

    @Size(max = 50, message = "username have length greater 50")
    @NotBlank
    private String name;
    private MultipartFile image;
}
