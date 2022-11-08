package com.springlibrabryapi.librarycontrol.repositories;

import com.springlibrabryapi.librarycontrol.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID > {


        @Query("SELECT u FROM UserModel u WHERE u.userName = :userName")
        UserModel findByUsername(@Param("userName")String userName);

}
