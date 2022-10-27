package com.springlibrabryapi.librarycontrol.controllers;


import com.springlibrabryapi.librarycontrol.dto.AuthorDto;

import com.springlibrabryapi.librarycontrol.models.AuthorsModel;
import com.springlibrabryapi.librarycontrol.services.LibraryService;
import com.springlibrabryapi.librarycontrol.services.RatedLimitServices;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static org.hibernate.tool.schema.SchemaToolingLogging.LOGGER;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping()
public class LibraryControler {
    final LibraryService libraryService;

    final RatedLimitServices ratedLimitServices;


    public LibraryControler(LibraryService libraryService, RatedLimitServices ratedLimitServices) {
        this.libraryService = libraryService;
        this.ratedLimitServices = ratedLimitServices;
    }


    @PostMapping("/authors/CADASTRO")
    public ResponseEntity<Object> saveAuthor(@RequestBody @Valid AuthorDto authorDto) {
        if (ratedLimitServices.getBucket().tryConsume(1)) {
            if (libraryService.existsByName(authorDto.getName())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Author already registered");
            }
            var authorModel = new AuthorsModel();
            BeanUtils.copyProperties(authorDto, authorModel);
            return ResponseEntity.status(HttpStatus.CREATED).body(libraryService.save(authorModel));
        } else {
            LOGGER.warn("No tokens left");
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body("Too many requests");
        }

    }

    @GetMapping("/authors")
    public ResponseEntity<List<AuthorsModel>> getAllAuthors() {

        if (ratedLimitServices.getBucket().tryConsume(1)) {
            return ResponseEntity.status(HttpStatus.OK).body(libraryService.findAll());
        } else {
            LOGGER.warn("No tokens left");
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(null);
        }


    }

    @DeleteMapping("/authors/{id}")
    public ResponseEntity<Object> deleteAuthor(@PathVariable(value = "id") java.util.UUID id) {
        Optional<AuthorsModel> authorSpotModelOptional = libraryService.findById(id);
        if (ratedLimitServices.getBucket().tryConsume(1)) {
            if (!authorSpotModelOptional.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Author not found");
            }
            libraryService.delete(authorSpotModelOptional.get());
            return ResponseEntity.status(HttpStatus.OK).body("Author deleted");
        } else {
            LOGGER.warn("No tokens left");
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body("Too many requests");
        }
    }

    @PutMapping("/authors/ATUALIZACAO/{id}")
    public ResponseEntity<Object> updateAuthor(@PathVariable(value = "id") java.util.UUID id, @RequestBody @Valid AuthorDto authorDto) {
        Optional<AuthorsModel> libraryModelOptional = libraryService.findById(id);
        if (ratedLimitServices.getBucket().tryConsume(1)) {
            if (!libraryModelOptional.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Author not found");
            }
            var authorModel = libraryModelOptional.get();
            BeanUtils.copyProperties(authorDto, authorModel);
            return ResponseEntity.status(HttpStatus.OK).body(libraryService.save(authorModel));
        } else {
            LOGGER.warn("No tokens left");
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body("Too many requests");
        }
    }


}