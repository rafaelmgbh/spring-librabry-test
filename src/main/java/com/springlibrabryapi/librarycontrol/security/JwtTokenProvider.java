package com.springlibrabryapi.librarycontrol.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.springlibrabryapi.librarycontrol.dto.TokenDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Service
public class JwtTokenProvider {

    @Value("${security.jwt.token.secret-key:secret}")
    private String secretKey = "secret";

    @Value("${security.jwt.token.expire-length:3600000}")
    private long validityInMilliseconds = 3600000; // 1h

    @Autowired
    private UserDetailsService userDetailsService;

    Algorithm algorithm = null;

    @PostConstruct
    protected void init(){
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        algorithm = Algorithm.HMAC256(secretKey.getBytes());
    }

    public TokenDto createToken(String username, List<String> roles){
        //esse método é responsável por criar o token
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);
        var accessToken = getAccessToken(username, roles, now, validity);
        var refreshToken = getRefreshToken(username, roles, now);
        return new TokenDto(username, true, now, validity, accessToken, refreshToken);
    }

    private String getRefreshToken(String username, List<String> roles, Date now) {
        //esse método é responsável por gerar o refresh token
        Date validityRefreshToken = new Date(now.getTime() + (validityInMilliseconds*2));
        return JWT.create().withClaim("roles", roles).withIssuedAt(now)
                .withExpiresAt(validityRefreshToken).withSubject(username)
                .sign(algorithm).strip();
    }

    private String getAccessToken(String username, List<String> roles, Date now, Date validity) {

        String issueUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();

        return JWT.create().withClaim("roles", roles).withIssuedAt(now)
                .withExpiresAt(validity).withSubject(username).withIssuer(issueUrl)
                .sign(algorithm).strip();
    }

    public Authentication getAuthentication(String token){
        //esse método é responsável por receber o token e verificar se ele é válido
        DecodedJWT decodedJWT = decodedToken(token);
        UserDetails userDetails = userDetailsService.loadUserByUsername(decodedJWT.getSubject());
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private DecodedJWT decodedToken(String token) {
        //esse método é responsável por decodificar o token e verificar se ele é válido
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        return decodedJWT;
    }

    public String resolveToken(HttpServletRequest req){
        //esse método é responsável por pegar o token da requisição e verificar se ele é válido
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean validateToken(String token){
        //verifica se o token é válido e se não expirou

        DecodedJWT decodedJWT = decodedToken(token);
        try {
            if(decodedJWT.getExpiresAt().before(new Date())){
                return false;
            }
            return true;
        }catch (Exception e){
            throw new RuntimeException("Expired or invalid token");
        }
    }
}
