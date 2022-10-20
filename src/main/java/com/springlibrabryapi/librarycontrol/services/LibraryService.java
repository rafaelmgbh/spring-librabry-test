package com.springlibrabryapi.librarycontrol.services;


import com.springlibrabryapi.librarycontrol.models.AuthorModel;
import com.springlibrabryapi.librarycontrol.repositories.LibraryRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LibraryService {

    final LibraryRepository libraryRepository;

    public LibraryService(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    public List<AuthorModel> findAll(){
        return libraryRepository.findAll();
    };

    public boolean existsByName(String name) {
        return libraryRepository.existsByName(name);
    }

    @Transactional
    public AuthorModel save(AuthorModel authorModel) {
        return libraryRepository.save(authorModel);
    }

    @Transactional
    public Optional<AuthorModel> findById(UUID id) {
        return libraryRepository.findById(id);

    }

    public void delete(AuthorModel authorModel) {
        libraryRepository.delete(authorModel);
    }
}
