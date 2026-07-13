package com.agustibayusamudro.services.impl;

import java.sql.SQLException;

import com.agustibayusamudro.dto.LoginDTO;
import com.agustibayusamudro.repositories.UserRepository;
import com.agustibayusamudro.repositories.impl.UserRepositoryImpl;
import com.agustibayusamudro.services.UserService;

public class UserServiceImpl implements UserService {

    private UserRepository userRepository = new UserRepositoryImpl();

    @Override
    public boolean login(LoginDTO loginDTO) {
        if (loginDTO.getUsername().isBlank() || loginDTO.getPassword().isBlank()) {
            return false;
        }
        try {
            var user = userRepository.findByUsername(loginDTO.getUsername());
            return user != null && user.getPassword().equals(loginDTO.getPassword());
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
