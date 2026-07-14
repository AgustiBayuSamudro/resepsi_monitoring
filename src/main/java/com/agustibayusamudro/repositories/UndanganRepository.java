package com.agustibayusamudro.repositories;

import java.sql.SQLException;
import java.util.List;

import com.agustibayusamudro.entities.Undangan;

public interface UndanganRepository {
    Undangan save(Undangan undangan) throws SQLException;
    List<Undangan> findAll() throws SQLException;
    Undangan findByKodeUndangan(String kodeUndangan) throws SQLException;
    void update(Undangan undangan) throws SQLException;
    void delete(String kodeUndangan) throws SQLException;    
    String findLastKodeUndangan() throws SQLException;
}
