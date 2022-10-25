package com.springlibrabryapi.librarycontrol.services;

import com.springlibrabryapi.librarycontrol.models.AuthorsModel;
import com.springlibrabryapi.librarycontrol.models.PapersModel;
import com.springlibrabryapi.librarycontrol.repositories.PapersRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PapersService {

    final PapersRepository papersRepository;

    public PapersService(PapersRepository papersRepository) {
        this.papersRepository = papersRepository;
    }


    @Transactional
    public boolean existsByTitle(String title) {
        return papersRepository.existsByTitle(title);
    }



    @Transactional
    public PapersModel save(PapersModel papersModel) {
        return papersRepository.save(papersModel);
    }


    public List<PapersModel> findAll() {
        return papersRepository.findAll();
    }

    ;

    @Transactional
    public Optional<PapersModel> findById(java.util.UUID id){
        return papersRepository.findById(id);
    }

    public void delete(PapersModel papersModel) {
        papersRepository.delete(papersModel);
    }


}
