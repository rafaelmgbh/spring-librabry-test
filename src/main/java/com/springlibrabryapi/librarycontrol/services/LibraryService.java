package com.springlibrabryapi.librarycontrol.services;


import com.springlibrabryapi.librarycontrol.models.AuthorsModel;
import com.springlibrabryapi.librarycontrol.repositories.LibraryRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class LibraryService {

    final LibraryRepository libraryRepository;

    public LibraryService(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    public List<AuthorsModel> findAll(){
        return libraryRepository.findAll();
    };

    public boolean existsByName(String name) {
        return libraryRepository.existsByName(name);
    }

    @Transactional
    public AuthorsModel save(AuthorsModel authorModel) {
        return libraryRepository.save(authorModel);
    }

    @Transactional
    public Optional<AuthorsModel> findById(java.util.UUID id) {
        return libraryRepository.findById(id);
    }

    public void delete(AuthorsModel authorModel) {
        libraryRepository.delete(authorModel);
    }

    @Transactional
    public Object findByNameContainingIgnoreCase(String name) {
        return libraryRepository.findByNameContainingIgnoreCase(name);
    }
}
