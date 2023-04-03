package com.iotstar.onlinetest.exceptions;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResourceExistException extends RuntimeException {
    String message;
}
