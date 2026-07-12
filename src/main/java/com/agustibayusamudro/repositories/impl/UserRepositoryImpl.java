package com.agustibayusamudro.repositories.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.agustibayusamudro.database.Database_Connect;
import com.agustibayusamudro.entities.User;
import com.agustibayusamudro.repositories.UserRepository;

public class UserRepositoryImpl implements UserRepository {
    
    @Override
    public User findByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (Connection conn = Database_Connect.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
                
                pstmt.setString(1, username);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
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
