package com.iotstar.onlinetest.DTOs.responses;

import com.iotstar.onlinetest.config.ApplicationConfig;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Response {
    private Boolean error;
    Object data;
}
