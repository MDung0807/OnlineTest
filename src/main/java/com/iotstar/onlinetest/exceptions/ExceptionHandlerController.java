package com.iotstar.onlinetest.exceptions;

import com.iotstar.onlinetest.DTOs.responses.Response;
import com.iotstar.onlinetest.utils.AppConstant;
import org.springframework.cglib.core.Local;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ExceptionHandlerController{
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
                AppConstant.INVALID_REQUEST,
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

    @ExceptionHandler(HttpServerErrorException.InternalServerError.class)
    public ResponseEntity<Response> internalException (){
        ExceptionDetails details = new ExceptionDetails(AppConstant.INTERNAL_VALID, LocalDateTime.now());
        return new ResponseEntity<>(
                new Response(true, details),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<Response> internalError(){
        ExceptionDetails details = new ExceptionDetails(AppConstant.SQL_EXCEPTION, LocalDateTime.now());
        return new ResponseEntity<>(
                new Response(true, details),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Response> validateException(MethodArgumentNotValidException ex){
//        ExceptionDetails details = new ExceptionDetails(ex.getMessage(), LocalDateTime.now());
//        return new ResponseEntity<>(
//                new Response(true, details),
//                HttpStatus.BAD_REQUEST
//        );
//    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<Response> bindingException(BindException ex){
        Map<String, String> errors = new HashMap<>();
        for (FieldError error: ex.getFieldErrors()){
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return new ResponseEntity<>(
                new Response(true, errors),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(HttpClientErrorException.UnsupportedMediaType.class)
    public ResponseEntity<Response> unSupportMediaType(HttpClientErrorException.UnsupportedMediaType ex){
        ExceptionDetails details = new ExceptionDetails(ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(
                new Response(true, details),
                HttpStatus.UNSUPPORTED_MEDIA_TYPE
        );
    }
}
