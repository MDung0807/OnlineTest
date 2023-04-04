package com.iotstar.onlinetest.config;

import com.iotstar.onlinetest.security.jwt.AuthEntryPointJwt;
import com.iotstar.onlinetest.security.jwt.AuthTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
//@EnableMethodSecurity
public class SecurityConfig {
    String[] allowURL = {
            "/**"
    };

//    @Bean
//    public DaoAuthenticationProvider authProvider() {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(accountDetailsService);
//        authProvider.setPasswordEncoder(encoder());
//        return authProvider;
//    }

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    @Bean
    AuthTokenFilter authTokenFilter() {
        return new AuthTokenFilter();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .cors()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint(unauthorizedHandler)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(authTokenFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests()
//                .requestMatchers(HttpMethod.GET,allowURL)
//                .permitAll()
                .requestMatchers("/auth/**")
                .permitAll()
//                .requestMatchers(HttpMethod.POST,allowURL)
//                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();

        return http.build();
    }
}
