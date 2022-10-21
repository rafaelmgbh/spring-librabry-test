package com.springlibrabryapi.librarycontrol.controllers;


import com.springlibrabryapi.librarycontrol.dto.PapersDto;
import com.springlibrabryapi.librarycontrol.models.PapersModel;
import com.springlibrabryapi.librarycontrol.services.PapersService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/papers")
public class PapersControler {
    final PapersService papersService;

    public PapersControler(PapersService papersService) {
        this.papersService = papersService;
    }

    @PostMapping
    public ResponseEntity<Object> savePapers(@RequestBody @Valid PapersDto papersDto) {
        if (papersService.existsByTitle(papersDto.getTitle())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Papers already registered");
        }

        var paperModel = new PapersModel();
        BeanUtils.copyProperties(papersDto, paperModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(papersService.save(paperModel));

    }
}
