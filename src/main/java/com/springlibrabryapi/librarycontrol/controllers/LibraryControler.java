package com.springlibrabryapi.librarycontrol.controllers;


import com.springlibrabryapi.librarycontrol.dto.AuthorDto;

import com.springlibrabryapi.librarycontrol.models.AuthorsModel;
import com.springlibrabryapi.librarycontrol.services.LibraryService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/library")
public class LibraryControler {
    final LibraryService libraryService;

    public LibraryControler(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @PostMapping
    public ResponseEntity<Object> saveAuthor(@RequestBody @Valid AuthorDto authorDto) {
        if (libraryService.existsByName(authorDto.getName())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Author already registered");
        }

    var authorModel = new AuthorsModel();
    BeanUtils.copyProperties(authorDto, authorModel);
    return ResponseEntity.status(HttpStatus.CREATED).body(libraryService.save(authorModel));

    }

    @GetMapping
    public ResponseEntity<List<AuthorsModel>> getAllAuthors(){
        return ResponseEntity.status(HttpStatus.OK).body(libraryService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteParkingSpot(@PathVariable(value = "id") java.util.UUID id){
        Optional<AuthorsModel> parkingSpotModelOptional = libraryService.findById(id);
        if(!parkingSpotModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Author not found");
        }
        libraryService.delete(parkingSpotModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Author deleted");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAuthor(@PathVariable(value = "id") java.util.UUID id, @RequestBody @Valid AuthorDto authorDto){
        Optional<AuthorsModel> libraryModelOptional = libraryService.findById(id);
        if(!libraryModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Author not found");
        }
        var authorModel = libraryModelOptional.get();
        BeanUtils.copyProperties(authorDto, authorModel);
        return ResponseEntity.status(HttpStatus.OK).body(libraryService.save(authorModel));
    }


}