package com.springlibrabryapi.librarycontrol.repositories;


import com.springlibrabryapi.librarycontrol.models.UserRoleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserAuthRoles extends JpaRepository<UserRoleModel, UUID> {


}
