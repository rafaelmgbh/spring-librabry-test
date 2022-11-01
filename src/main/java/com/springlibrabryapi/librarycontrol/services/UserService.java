package com.springlibrabryapi.librarycontrol.services;

import com.springlibrabryapi.librarycontrol.dto.UserDto;
import com.springlibrabryapi.librarycontrol.dto.UserRoleDto;
import com.springlibrabryapi.librarycontrol.models.RoleModel;
import com.springlibrabryapi.librarycontrol.models.UserModel;
import com.springlibrabryapi.librarycontrol.models.UserRoleModel;
import com.springlibrabryapi.librarycontrol.repositories.RolesRepository;
import com.springlibrabryapi.librarycontrol.repositories.UserAuthRoles;
import com.springlibrabryapi.librarycontrol.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    final UserRepository userRepository;

    final RolesRepository rolesRepository;

    final UserAuthRoles userAuthRoles;


    public UserService(UserRepository userRepository, RolesRepository rolesRepository, UserAuthRoles userAuthRoles) {
        this.userRepository = userRepository;
        this.rolesRepository = rolesRepository;

        this.userAuthRoles = userAuthRoles;
    }

    public List<UserModel> findAll() {
        return userRepository.findAll();
    }

    public UserModel save(UserModel userModel) {
        return userRepository.save(userModel);
    }

    public Object createUser(UserDto userDto) {
        UserModel userModel = new UserModel();
        userModel.setName(userDto.getName());
        userModel.setUsername(userDto.getUsername());

        // Criptografando a senha
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        userModel.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));

        return save(userModel);
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public List<RoleModel> findAllRoles() {
        return rolesRepository.findAll();
    }

    public UserRoleModel saveAuth(UserRoleModel userRoleModel) {
        return userAuthRoles.save(userRoleModel);
    }

    public Object createAuth(UserRoleDto userRoleDto) {
        UserRoleModel userRoleModel = new UserRoleModel();
        userRoleModel.setUserId(userRoleDto.getUserId());
        userRoleModel.setRoleId(userRoleDto.getRoleId());


        return saveAuth(userRoleModel);
    }

    public List<UserRoleModel> findAllAuth() {
        return userAuthRoles.findAll();
    }


//    public List<UserModel> findAllRoles() {
//        return rolesRepository.findAll();
//    }

//
//    public UserRoleDto save(UserRoleDto userRoleDto) {
//        return userRoleRepository.save(userRoleDto);
//    }

//    public Object createRole(RoleDto roleDto) {
//        RoleModel roleModel = new RoleModel();
//        roleModel.setRole(roleDto.getRole());
//        return saveRole(roleModel);
//    }
//

}

