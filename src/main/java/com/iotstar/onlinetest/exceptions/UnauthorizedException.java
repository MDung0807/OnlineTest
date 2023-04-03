package com.iotstar.onlinetest.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class UnauthorizedException extends RuntimeException {
    String message;
}
