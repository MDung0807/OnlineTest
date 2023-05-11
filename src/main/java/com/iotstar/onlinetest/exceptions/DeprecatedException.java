package com.iotstar.onlinetest.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeprecatedException extends RuntimeException {
    String message;
}
