package com.springlibrabryapi.librarycontrol.services;

import com.springlibrabryapi.librarycontrol.models.PapersModel;
import com.springlibrabryapi.librarycontrol.repositories.PapersRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PapersService {

    final PapersRepository papersRepository;

    public PapersService(PapersRepository papersRepository) {
        this.papersRepository = papersRepository;
    }


    public boolean existsByTitle(String title) {
        return papersRepository.existsByTitle(title);
    }

    @Transactional
    public PapersModel save(PapersModel paperModel) {
        return papersRepository.save(paperModel);
    }
}
