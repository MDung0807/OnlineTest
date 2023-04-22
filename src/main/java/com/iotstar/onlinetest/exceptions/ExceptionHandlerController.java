package com.iotstar.onlinetest.exceptions;

import com.iotstar.onlinetest.DTOs.responses.Response;
import com.iotstar.onlinetest.utils.AppConstant;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Response> resourceNotFound(
            ResourceNotFoundException e, WebRequest request){
        ExceptionDetails details = new ExceptionDetails(
                e.getMessage(),
                LocalDateTime.now());
        return new ResponseEntity<>(new Response(true, details),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<Response> invalidRequest(
            InvalidRequestException e, WebRequest request){
        ExceptionDetails details = new ExceptionDetails(
                e.getMessage(),
                LocalDateTime.now());
        return new ResponseEntity<>(new Response(true, details),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceExistException.class)
    public ResponseEntity<Response> resourceExist(
            ResourceExistException e, WebRequest request){
        ExceptionDetails details = new ExceptionDetails(
                e.getMessage(),
                LocalDateTime.now());
        return new ResponseEntity<>(new Response(true, details),HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Response> UserNotFound(
            UserNotFoundException e, WebRequest request){
        ExceptionDetails details = new ExceptionDetails(
                e.getMessage(),
                LocalDateTime.now());
        return new ResponseEntity<>(new Response(true, details),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<Response> unauthorized(
            UnauthorizedException e, WebRequest request){
        ExceptionDetails details = new ExceptionDetails(
                AppConstant.UNAUTHORIZED_ERROR,
                LocalDateTime.now());
        return new ResponseEntity<>(new Response(true, details),HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Response> permissionDeny(
            AccessDeniedException e, WebRequest request){
        ExceptionDetails details = new ExceptionDetails(
                e.getMessage(),
                LocalDateTime.now());
        return new ResponseEntity<>(new Response(true, details),HttpStatus.FORBIDDEN);
    }
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Response> badCredentials(
            BadCredentialsException e,
            WebRequest request){
        ExceptionDetails details = new ExceptionDetails(
                AppConstant.USERNAME_OR_PASSWORD_INCORRECT,
                LocalDateTime.now()
        );
        return ResponseEntity.ok(new Response(true, details));
    }
}
