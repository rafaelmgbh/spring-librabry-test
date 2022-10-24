package com.springlibrabryapi.librarycontrol.Security;

import com.springlibrabryapi.librarycontrol.models.JwtRefreshRequestDto;
import com.springlibrabryapi.librarycontrol.models.JwtResponseDto;
import com.springlibrabryapi.librarycontrol.models.JwtUser;
import com.springlibrabryapi.librarycontrol.repositories.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtUtils jwtUtils;

    @Value("${jwt.refreshToken.expiration}")
    private int expiration;



    private boolean isTokenExpired(ZonedDateTime expirationTime) {
        return expirationTime.isBefore(ZonedDateTime.now(ZoneId.systemDefault()));
    }

    public JwtResponseDto createRefreshToken(JwtUser user) {
        RefreshToken refreshToken = RefreshToken.builder()
                .user(user)
                .expiration(ZonedDateTime.now(ZoneId.systemDefault()).plusMinutes(expiration))
                .token(UUID.randomUUID().toString())
                .build();

        refreshTokenRepository.save(refreshToken);

        return JwtResponseDto.of(jwtUtils.generateToken(user), refreshToken.getToken());
    }