package com.springlibrabryapi.librarycontrol.repositories;

import com.springlibrabryapi.librarycontrol.models.AuthorsModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LibraryRepository extends JpaRepository<AuthorsModel, java.util.UUID> {

    boolean existsByName(String name);

    Optional <AuthorsModel> findByNameContainingIgnoreCase(String name);
}
