package com.springlibrabryapi.librarycontrol.controllers;


import com.springlibrabryapi.librarycontrol.dto.PapersDto;
import com.springlibrabryapi.librarycontrol.models.AuthorsModel;
import com.springlibrabryapi.librarycontrol.models.PapersModel;
import com.springlibrabryapi.librarycontrol.services.PapersService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping()
public class PapersControler {
    final PapersService papersService;

    public PapersControler(PapersService papersService) {
        this.papersService = papersService;
    }

    @PostMapping("/papers/CADASTRO")
    public ResponseEntity<Object> savePapers(@RequestBody @Valid PapersDto papersDto) {
        if (papersService.existsByTitle(papersDto.getTitle())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Papers already registered");
        }

        var paperModel = new PapersModel();
        BeanUtils.copyProperties(papersDto, paperModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(papersService.save(paperModel));

    }

    @GetMapping("/papers")
    public ResponseEntity<List<PapersModel>> getAllPapers(){
        return ResponseEntity.status(HttpStatus.OK).body(papersService.findAll());
    }

    @PutMapping("/papers/ATUALIZACAO/{id}")
    public ResponseEntity<Object> updatePaper(@PathVariable(value = "id")UUID id, @RequestBody @Valid List<PapersDto> papersDto){
        Optional<PapersModel> papersModelOptional = papersService.findById(id);
        if(!papersModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paper not found");
        }
        var papersModel = papersModelOptional.get();
        BeanUtils.copyProperties(papersDto, papersModel);
        return ResponseEntity.status(HttpStatus.OK).body(papersService.save(papersModel));
    }

    @DeleteMapping("/papers/{id}")
    public ResponseEntity<Object> deletePaper(@PathVariable(value = "id") java.util.UUID id){
        Optional<PapersModel> papersModelOptional = papersService.findById(id);
        if(!papersModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paper not found");
        }
        papersService.delete(papersModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Paper deleted");
    }
}
