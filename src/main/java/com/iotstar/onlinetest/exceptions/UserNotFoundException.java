package com.iotstar.onlinetest.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class UserNotFoundException extends RuntimeException {
    String message;
}
