package com.springlibrabryapi.librarycontrol.repositories;

import com.springlibrabryapi.librarycontrol.models.JwtUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JwtUserRepository extends JpaRepository<JwtUser, Long> {

    Optional<JwtUser> findByUsername(String username);
    Optional<JwtUser> findByEmail(String email);

}

