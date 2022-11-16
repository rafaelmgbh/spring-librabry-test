package com.springlibrabryapi.librarycontrol.controllers;


import com.springlibrabryapi.librarycontrol.dto.AuthorDto;
import com.springlibrabryapi.librarycontrol.models.AuthorsModel;
import com.springlibrabryapi.librarycontrol.services.LibraryService;
import com.springlibrabryapi.librarycontrol.services.RatedLimitServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.models.annotations.OpenAPI31;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Create Author", description = "Create Author", tags = {"Authors"}, operationId = "createAuthor")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Author created"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid input"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "409", description = "Author already exists")})
    @PostMapping("/authors")
    public ResponseEntity<Object> saveAuthor(@RequestBody @Valid AuthorDto authorDto) {
        if (libraryService.existsByName(authorDto.getName())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Author already registered");
        }
        var authorModel = new AuthorsModel();
        BeanUtils.copyProperties(authorDto, authorModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(libraryService.save(authorModel));

    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @Operation(summary = "Get all authors", description = "Get all authors", tags = {"Authors"}, operationId = "getAllAuthors")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "successful operation"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid input"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized")})
    @GetMapping("/authors")
    public ResponseEntity<List<AuthorsModel>> getAllAuthors() {

        return ResponseEntity.status(HttpStatus.OK).body(libraryService.findAll());

    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @Operation(summary = "Get author by Name", description = "Get author by Name", tags = {"Authors"}, operationId = "getAuthorByName")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "successful operation"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid input"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Author not found")})
    @GetMapping("/authors/{name}")
    public ResponseEntity<Object> getAuthorByName(@PathVariable String name) {

        AuthorsModel authorModel = new AuthorsModel();
        if (libraryService.existsByName(authorModel.getName())) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Author not found");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(libraryService.findByNameContainingIgnoreCase(name));
        }

    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete author by ID", description = "Delete author by ID", tags = {"Authors"}, operationId = "deleteAuthorById")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "successful operation"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid input"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Author not found")})
    @DeleteMapping("/authors/{id}")
    public ResponseEntity<Object> deleteAuthor(@PathVariable(value = "id") java.util.UUID id) {
        Optional<AuthorsModel> authorSpotModelOptional = libraryService.findById(id);

        if (!authorSpotModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Author not found");
        }
        libraryService.delete(authorSpotModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Author deleted");

    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Edit Author by ID", description = "Edit Author by ID", tags = {"Authors"}, operationId = "editAuthorById")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "successful operation"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid input"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Author not found")})
    @PutMapping("/authors/ATUALIZACAO/{id}")
    public ResponseEntity<Object> updateAuthor(@PathVariable(value = "id") java.util.UUID id, @RequestBody @Valid AuthorDto authorDto) {
        Optional<AuthorsModel> libraryModelOptional = libraryService.findById(id);

        if (!libraryModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Author not found");
        }
        var authorModel = libraryModelOptional.get();
        BeanUtils.copyProperties(authorDto, authorModel);
        return ResponseEntity.status(HttpStatus.OK).body(libraryService.save(authorModel));

    }


}