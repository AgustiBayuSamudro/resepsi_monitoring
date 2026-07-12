package com.agustibayusamudro.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database_Connect {
    private static final String URL = "jdbc:postgresql://localhost:5432/db_resepsi_wedding";
    private static final String USER = "postgres";
    private static final String PASS = "postgres123";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
