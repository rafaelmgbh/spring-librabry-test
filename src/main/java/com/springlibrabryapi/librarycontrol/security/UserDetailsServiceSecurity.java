package com.springlibrabryapi.librarycontrol.security;

import com.springlibrabryapi.librarycontrol.models.UserModel;
import com.springlibrabryapi.librarycontrol.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceSecurity implements UserDetailsService {

    final UserRepository userRepository;

    public UserDetailsServiceSecurity(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel userModel = userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User "+ username + "not found"));
        return userModel;
    }
}
