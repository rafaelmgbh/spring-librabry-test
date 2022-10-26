package com.springlibrabryapi.librarycontrol.controllers;


import com.springlibrabryapi.librarycontrol.dto.PapersDto;
import com.springlibrabryapi.librarycontrol.models.PapersModel;
import com.springlibrabryapi.librarycontrol.services.PapersService;
import com.springlibrabryapi.librarycontrol.services.RatedLimitServices;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.hibernate.tool.schema.SchemaToolingLogging.LOGGER;

@Controller
@RequestMapping()
public class PapersControler {
    final PapersService papersService;

    final RatedLimitServices ratedLimitServices;

    public PapersControler(PapersService papersService, RatedLimitServices ratedLimitServices) {
        this.papersService = papersService;
        this.ratedLimitServices = ratedLimitServices;
    }

    @PostMapping("/papers/CADASTRO")
    public ResponseEntity<Object> savePapers(@RequestBody @Valid PapersDto papersDto) {
        if (ratedLimitServices.getBucket().tryConsume(1)) {
            if (papersService.existsByTitle(papersDto.getTitle())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Papers already registered");
            }

            var paperModel = new PapersModel();
            BeanUtils.copyProperties(papersDto, paperModel);
            return ResponseEntity.status(HttpStatus.CREATED).body(papersService.save(paperModel));

        } else {
            LOGGER.warn("No tokens left");
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body("Too many requests");
        }
    }

    @GetMapping("/papers")
    public ResponseEntity<List<PapersModel>> getAllPapers() {
        if (ratedLimitServices.getBucket().tryConsume(1)) {
            return ResponseEntity.status(HttpStatus.OK).body(papersService.findAll());
        } else {
            LOGGER.warn("No tokens left");
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(null);
        }
    }

    @PutMapping("/papers/ATUALIZACAO/{id}")
    public ResponseEntity<Object> updatePaper(@PathVariable(value = "id") UUID id, @RequestBody @Valid PapersDto papersDto) {
        Optional<PapersModel> papersModelOptional = papersService.findById(id);
        if (ratedLimitServices.getBucket().tryConsume(1)) {
            if (!papersModelOptional.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paper not found");
            }
            var papersModel = papersModelOptional.get();
            BeanUtils.copyProperties(papersDto, papersModel);
            return ResponseEntity.status(HttpStatus.OK).body(papersService.save(papersModel));
        } else {
            LOGGER.warn("No tokens left");
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body("Too many requests");
        }
    }

    @DeleteMapping("/papers/{id}")
    public ResponseEntity<Object> deletePaper(@PathVariable(value = "id") java.util.UUID id) {
        Optional<PapersModel> papersModelOptional = papersService.findById(id);
        if (ratedLimitServices.getBucket().tryConsume(1)) {
            if (!papersModelOptional.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paper not found");
            }
            papersService.delete(papersModelOptional.get());
            return ResponseEntity.status(HttpStatus.OK).body("Paper deleted");
        } else {
            LOGGER.warn("No tokens left");
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body("Too many requests");
        }
    }
}
