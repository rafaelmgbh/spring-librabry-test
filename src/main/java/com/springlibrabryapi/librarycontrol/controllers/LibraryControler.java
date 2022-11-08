package com.springlibrabryapi.librarycontrol.controllers;


import com.springlibrabryapi.librarycontrol.dto.AuthorDto;
import com.springlibrabryapi.librarycontrol.models.AuthorsModel;
import com.springlibrabryapi.librarycontrol.services.LibraryService;
import com.springlibrabryapi.librarycontrol.services.RatedLimitServices;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping()
public class LibraryControler {
    final LibraryService libraryService;

    final int limit = 1;

    final RatedLimitServices ratedLimitServices;


    public LibraryControler(LibraryService libraryService, RatedLimitServices ratedLimitServices) {
        this.libraryService = libraryService;
        this.ratedLimitServices = ratedLimitServices;
    }


    @PostMapping("/authors")
    public ResponseEntity<Object> saveAuthor(@RequestBody AuthorDto authorDto) {
        if (libraryService.existsByName(authorDto.getName())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Author already registered");
        }
        var authorModel = new AuthorsModel();
        BeanUtils.copyProperties(authorDto, authorModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(libraryService.save(authorModel));

    }


    @GetMapping("/authors")
    public ResponseEntity<List<AuthorsModel>> getAllAuthors() {

        return ResponseEntity.status(HttpStatus.OK).body(libraryService.findAll());

    }


    @GetMapping("/authors/{name}")
    public ResponseEntity<Object> getAuthorByName(@PathVariable String name) {

        AuthorsModel authorModel = new AuthorsModel();
        if (libraryService.existsByName(authorModel.getName())) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Author not found");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(libraryService.findByNameContainingIgnoreCase(name));
        }

    }


    @DeleteMapping("/authors/{id}")
    public ResponseEntity<Object> deleteAuthor(@PathVariable(value = "id") java.util.UUID id) {
        Optional<AuthorsModel> authorSpotModelOptional = libraryService.findById(id);

        if (!authorSpotModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Author not found");
        }
        libraryService.delete(authorSpotModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Author deleted");

    }


    @PutMapping("/authors/ATUALIZACAO/{id}")
    public ResponseEntity<Object> updateAuthor(@PathVariable(value = "id") java.util.UUID id, @RequestBody AuthorDto authorDto) {
        Optional<AuthorsModel> libraryModelOptional = libraryService.findById(id);

        if (!libraryModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Author not found");
        }
        var authorModel = libraryModelOptional.get();
        BeanUtils.copyProperties(authorDto, authorModel);
        return ResponseEntity.status(HttpStatus.OK).body(libraryService.save(authorModel));

    }


}