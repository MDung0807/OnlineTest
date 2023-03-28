package com.iotstar.onlinetest.security.jwt;

import com.iotstar.onlinetest.security.services.AccountDetailsImpl;
import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.security.Keys;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import java.util.Date;


@Component
public class JwtUtils {
//    @Value("${test.app.jwtSecret}")
    private String secret = "supersecurityandsucurityandsucurityandsucurityandsupersupersecurity";

//    @Value("${test.app.jwtExpirationMs}")
    private Long jwtExpirationMs = Long.valueOf(7200000);

    public JwtUtils() {
    }

    public String generateJwtToken(Authentication authentication) {
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());

        AccountDetailsImpl accountDetails = (AccountDetailsImpl) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject((accountDetails.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");

        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7, headerAuth.length());
        }

        return null;
    }

    public boolean validateJwtToken(String authToken) {
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(authToken);
            return true;
        }catch(Exception e){
            return false;
        }

    }


}

