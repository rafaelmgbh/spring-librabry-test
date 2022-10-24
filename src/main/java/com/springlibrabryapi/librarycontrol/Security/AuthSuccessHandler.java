package com.springlibrabryapi.librarycontrol.Security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springlibrabryapi.librarycontrol.services.JwtUserrService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
@Slf4j

public class AuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final int expTime;
    private final String secret;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final JwtUtils jwtUtils;
    private final JwtUserrService jwtUserrService;
    private final RefreshTokenService refreshTokenService;

    public AuthSuccessHandler(JwtUtils jwtUtils, JwtUserrService jwtUserrService, RefreshTokenService refreshTokenService) {
        this.jwtUtils = jwtUtils;
        this.jwtUserrService = jwtUserrService;
        this.refreshTokenService = refreshTokenService;
        this.expTime = 1000 * 60 * 60 * 24 * 7;
        this.secret = "secret";
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        var user = jwtUserrService.getUserByUsername(principal.getUsername());
        String token = jwtUtils.createJwt(user.getEmail());
        String refreshToken = refreshTokenService.createToken(user);
        response.addHeader("Authorization", "Bearer " + token);
        response.addHeader("Content-Type", "application/json");
        response.getWriter().write("{ \"token\": \"" + token + "\" }");
    }
}
