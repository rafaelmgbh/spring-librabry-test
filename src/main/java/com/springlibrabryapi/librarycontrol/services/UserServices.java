package com.springlibrabryapi.librarycontrol.services;

import com.springlibrabryapi.librarycontrol.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServices implements UserDetailsService {



    @Autowired
    UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByUsername(username);
        if (user != null){
            return user;
        }else {
            throw new UsernameNotFoundException("Username " + username + " not found");
        }
    }


    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
        var user = userRepository.findByEmail(email);
        if (user != null){
            return user;
        }else {
            throw new UsernameNotFoundException("Email " + email + " not found");
        }
    }
}
