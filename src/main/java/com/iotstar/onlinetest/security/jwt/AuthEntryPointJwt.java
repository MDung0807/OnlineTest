package com.iotstar.onlinetest.security.jwt;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.iotstar.onlinetest.DTOs.responses.Response;
import com.iotstar.onlinetest.exceptions.ExceptionDetails;
import com.iotstar.onlinetest.utils.AppConstant;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

    private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException {
        logger.error("Unauthorized error: {}", authException.getMessage());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        ExceptionDetails details= new ExceptionDetails(
                AppConstant.UNAUTHORIZED_ERROR,
                LocalDateTime.now()
        );
        Response resp = new Response(true, details);
        final ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), resp);
    }

}
