package com.springlibrabryapi.librarycontrol.repositories;

import com.springlibrabryapi.librarycontrol.models.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EnumType;
import java.util.List;
import java.util.UUID;

@Repository
public interface RolesRepository extends JpaRepository<RoleModel, UUID> {

    List<RoleModel> findAllByRoleNameIn(List<EnumType> roleNames);


}
