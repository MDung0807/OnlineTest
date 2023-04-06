package com.iotstar.onlinetest.DTOs.requests;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class QuestionImageRequest {
    private Long questionId;
    private MultipartFile image;
}
