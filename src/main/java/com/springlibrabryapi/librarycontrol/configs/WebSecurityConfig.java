package com.springlibrabryapi.librarycontrol.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//@Configuration // Classe de configuração do Spring
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    final
    UserDetailsService userDetailsService;

    public WebSecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override

    protected void configure(HttpSecurity http) throws Exception {
        // Configurações de autorização de acesso


        http.httpBasic()
                .and()
                .authorizeHttpRequests()
                .antMatchers(HttpMethod.GET , "/users/**").permitAll()
                .antMatchers(HttpMethod.DELETE , "/authors/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST , "/authors/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT , "/authors/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET , "/authors/**").permitAll()

                .anyRequest()
                .authenticated()
                .and()
                .csrf()
                .disable();
    }

    @Override
    //Configura a autenticacao do usuario no banco de dados ou em memoria
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    //Cria uma instancia do BCryptPasswordEncoder para criptografar a senha do usuario
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
