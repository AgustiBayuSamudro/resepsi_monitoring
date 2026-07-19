package com.agustibayusamudro.repositories;

import java.sql.SQLException;

import com.agustibayusamudro.entities.User;

public interface UserRepository {
    User findByUsername(String username)throws SQLException;
}