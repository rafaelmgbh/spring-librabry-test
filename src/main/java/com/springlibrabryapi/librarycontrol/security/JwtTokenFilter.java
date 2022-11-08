package com.springlibrabryapi.librarycontrol.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class JwtTokenFilter extends GenericFilterBean {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //esse método é responsável por filtrar a requisição e verificar se o token é válido
        String token = jwtTokenProvider.resolveToken(((javax.servlet.http.HttpServletRequest) servletRequest));
        if (token != null && jwtTokenProvider.validateToken(token)){
            Authentication auth =  jwtTokenProvider.getAuthentication(token);
            if (auth != null){
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
        //esse método é responsável por receber o token e verificar se ele é válido
        this.jwtTokenProvider = jwtTokenProvider;
    }
}
