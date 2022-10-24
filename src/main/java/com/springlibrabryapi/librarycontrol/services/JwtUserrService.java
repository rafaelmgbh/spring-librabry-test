package com.springlibrabryapi.librarycontrol.services;

import com.springlibrabryapi.librarycontrol.models.JwtUser;
import com.springlibrabryapi.librarycontrol.repositories.JwtUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JwtUserrService {

    private final JwtUserRepository jwtUserRepository;

    public JwtUser save(JwtUser jwtUser){
        return jwtUserRepository.save(jwtUser);
    }

    public Optional<JwtUser>findByEmail(String email){
        return jwtUserRepository.findByEmail(email);
    }

    public JwtUser getUserByEmail(String email){
        return jwtUserRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public Optional<JwtUser>findByUsername(String username){
        return jwtUserRepository.findByUsername(username);
    }

    public JwtUser getUserByUsername(String username){
        return jwtUserRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
