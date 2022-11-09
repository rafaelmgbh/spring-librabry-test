package com.springlibrabryapi.librarycontrol.configs;

import com.springlibrabryapi.librarycontrol.security.JwtConfigurer;
import com.springlibrabryapi.librarycontrol.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfigV2 {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;


    private final AuthenticationConfiguration authConfiguration;

    public SecurityConfigV2(AuthenticationConfiguration authConfiguration) {
        this.authConfiguration = authConfiguration;
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return authConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        //esse bean é responsável por codificar a senha
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());
        DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("pbkdf2", encoders);
        passwordEncoder.setDefaultPasswordEncoderForMatches(new Pbkdf2PasswordEncoder());
        return passwordEncoder;
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //esse método é responsável por configurar as regras de acesso
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(
                        //esse método é responsável por liberar o acesso a alguns endpoints
                        "/auth/signin",
                        "/auth/refresh",
                        "/api-docs/**",
                        "/swagger-ui.html**"
                ).permitAll()
                //esse método é responsável por bloquear o acesso a alguns endpoints
                .antMatchers("/**").authenticated()
                //esse método é responsável por configurar o filtro de autenticação
                .antMatchers("/users").denyAll()
                .and()
                .cors()
                .and()
                .apply(new JwtConfigurer(jwtTokenProvider));
        return http.build();
    }
}
