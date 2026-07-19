package com.agustibayusamudro.repositories.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.agustibayusamudro.database.DatabaseConnection;
import com.agustibayusamudro.entities.Undangan;
import com.agustibayusamudro.repositories.UndanganRepository;

public class UndanganRepositoryImpl implements UndanganRepository {
    private final DatabaseConnection databaseConnection;
    public UndanganRepositoryImpl(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }
    
    @Override
    public Undangan save(Undangan undangan) throws SQLException {
        String sql = "INSERT INTO undangans (kode_undangan, nama, alamat, jenis_kelamin, created_at,updated_at) VALUES (?,?,?,?,now(),now())";
        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
             stmt.setString(1, undangan.getKodeUndangan());
             stmt.setString(2, undangan.getNama());
             stmt.setString(3, undangan.getAlamat());
             stmt.setString(4, undangan.getJenisKelamin());
             stmt.executeUpdate();
             return undangan;
        }
    }

    @Override
    public List<Undangan> findAll() throws SQLException {
        List<Undangan> list = new ArrayList<>();
        String sql = "SELECT * FROM undangans";
        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(mapRowToUndangan(rs));
            }
        }
        return list;
    }

    @Override
    public Undangan findByKodeUndangan(String kodeUndangan) throws SQLException {
        String sql = "SELECT * FROM undangans WHERE kode_undangan = ?";
        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, kodeUndangan);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return mapRowToUndangan(rs);
            }
        }
        return null;
    }
    @Override
    public void update(Undangan undangan) throws SQLException {
        String sql = "UPDATE undangans SET nama = ?, alamat = ?, jenis_kelamin = ? WHERE undangan_id = ?";
        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, undangan.getNama());
            stmt.setString(2, undangan.getAlamat());
            stmt.setString(3, undangan.getJenisKelamin());
            stmt.setInt(4, undangan.getUndanganId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(String kodeUndangan) throws SQLException {
        String sql = "DELETE FROM undangans WHERE kode_undangan = ?";
        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, kodeUndangan);
            stmt.executeUpdate();
        }
    }

    private Undangan mapRowToUndangan(ResultSet rs) throws SQLException {
        return new Undangan(
            rs.getInt("undangan_id"),
            rs.getString("kode_undangan"),
            rs.getString("nama"),
            rs.getString("alamat"),
            rs.getString("jenis_kelamin"),
            rs.getTimestamp("created_at").toLocalDateTime(),
            rs.getTimestamp("updated_at") != null ? rs.getTimestamp("updated_at").toLocalDateTime() : null
        );
    }

    @Override
    public String findLastKodeUndangan() throws SQLException {
    String sql = "SELECT kode_undangan FROM undangans ORDER BY undangan_id DESC LIMIT 1";
    try (Connection conn = databaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {
        if (rs.next()) {
            return rs.getString("kode_undangan");
        }
    }
    return null; 
}
}
