package com.springlibrabryapi.librarycontrol.repositories;


import com.springlibrabryapi.librarycontrol.models.PapersModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PapersRepository  extends JpaRepository<PapersModel, java.util.UUID> {
    boolean existsByTitle(String title);

}
