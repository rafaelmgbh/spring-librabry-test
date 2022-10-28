package com.springlibrabryapi.librarycontrol.services;

import com.springlibrabryapi.librarycontrol.dto.UserDto;
import com.springlibrabryapi.librarycontrol.models.RoleModel;
import com.springlibrabryapi.librarycontrol.models.UserModel;
import com.springlibrabryapi.librarycontrol.repositories.RolesRepository;
import com.springlibrabryapi.librarycontrol.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    final UserRepository userRepository;

    final RolesRepository rolesRepository;

    public UserService(UserRepository userRepository, RolesRepository rolesRepository) {
        this.userRepository = userRepository;
        this.rolesRepository = rolesRepository;
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

    public List<RoleModel> getAllRoles() {
        return rolesRepository.findAll();
    }
}

