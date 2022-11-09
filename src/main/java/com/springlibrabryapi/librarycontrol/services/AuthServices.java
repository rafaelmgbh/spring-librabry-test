package com.springlibrabryapi.librarycontrol.services;

import com.springlibrabryapi.librarycontrol.dto.AccountCredentialsDto;
import com.springlibrabryapi.librarycontrol.dto.TokenDto;
import com.springlibrabryapi.librarycontrol.repositories.UserRepository;
import com.springlibrabryapi.librarycontrol.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

@Service
public class AuthServices {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity sigin(AccountCredentialsDto data){
        try {
            var email = data.getEmail();
            var password = data.getPassword();

            authenticationManager.authenticate(new org.springframework.security.authentication
                    .UsernamePasswordAuthenticationToken(email, password));
            var user = userRepository.findByEmail(email);
            var token = new TokenDto();
            if (user != null){
                token = jwtTokenProvider.createToken(email, user.getRoles());
            } else {
                throw new Exception("User not found");
            }
            return ResponseEntity.ok(token);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
