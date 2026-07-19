package com.agustibayusamudro.repositories.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.agustibayusamudro.database.DatabaseConnection;
import com.agustibayusamudro.entities.User;
import com.agustibayusamudro.repositories.UserRepository;

public class UserRepositoryImpl implements UserRepository{

    private final DatabaseConnection databaseConnection;    
    public UserRepositoryImpl(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public User findByUsername(String username)throws SQLException {
        String SQL = "SELECT * FROM users WHERE username = ?";
        try(Connection conn = databaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(SQL)) {

                stmt.setString(1, username);
                try(ResultSet rs = stmt.executeQuery()) {
                    if(rs.next()) {
                        return new User(
                            rs.getInt("user_id"),
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getTimestamp("created_at").toLocalDateTime(),
                            rs.getTimestamp("updated_at").toLocalDateTime()
                        );
                    }
                }
            }
        return null;
    }
}