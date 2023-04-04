package com.iotstar.onlinetest.security.jwt;


import com.iotstar.onlinetest.security.services.AccountDetailsImpl;
import com.iotstar.onlinetest.security.services.AccountDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
    public class AuthTokenFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    AccountDetailsServiceImpl accountDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String token = jwtUtils.parseJwt(request);
            if(jwtUtils.validateJwtToken(token) && StringUtils.hasText(token)) {

                String username = jwtUtils.getUserNameFromJwtToken(token);
                AccountDetailsImpl accountDetails = (AccountDetailsImpl) accountDetailsService.loadUserByUsername(username);
//                log.error(accountDetails.getEmail());
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(
                                accountDetails,
                                null,
                                accountDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        } catch (Exception e) {
            logger.error("Cannot set user authentication: {}", e);
        }
        filterChain.doFilter(request, response);

    }
}
