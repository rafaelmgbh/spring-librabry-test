package com.springlibrabryapi.librarycontrol.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
// Habilita a segurança global de métodos para o Spring Security e permite
// que as anotações @PreAuthorize e @PostAuthorize sejam usadas em métodos de serviço.

public class WebSecurityConfigV2 {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.httpBasic()
                .and()
                .authorizeHttpRequests()
                .antMatchers( "/swagger-ui/**",
                        "/api-docs/**").permitAll()
//                .antMatchers(HttpMethod.GET , "/users/**").permitAll()
//                .antMatchers(HttpMethod.DELETE , "/authors/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.POST , "/authors/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.PUT , "/authors/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.GET , "/authors/**").permitAll()

                .anyRequest()
                .authenticated()
                .and()
                .csrf()
                .disable();
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
