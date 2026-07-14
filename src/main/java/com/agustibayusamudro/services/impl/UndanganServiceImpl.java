package com.agustibayusamudro.services.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import com.agustibayusamudro.dto.UndanganDTO;
import com.agustibayusamudro.entities.Undangan;
import com.agustibayusamudro.repositories.UndanganRepository;
import com.agustibayusamudro.repositories.impl.UndanganRepositoryImpl;
import com.agustibayusamudro.services.UndanganService;

public class UndanganServiceImpl implements UndanganService {
    private final UndanganRepository repository = new UndanganRepositoryImpl();
    
    @Override
    public void tambahUndangan(UndanganDTO dto) {
        try {        
            String lastKode = repository.findLastKodeUndangan();
            String newKode = generateNextKode(lastKode);        
            Undangan u = new Undangan(null, newKode, dto.getNama(), 
                                    dto.getAlamat(), dto.getJenisKelamin(), null, null);
            repository.save(u);
            } catch (SQLException e) {
                throw new RuntimeException("Gagal menambah data: " + e.getMessage());
        }
    }
    @Override
    public List<UndanganDTO> ambilSemuaUndangan() {
        try {
            List<Undangan> listEntity = repository.findAll();            
            return listEntity.stream().map(u -> 
                new UndanganDTO(u.getKodeUndangan(), u.getNama(), u.getAlamat(), u.getJenisKelamin())
            ).collect(Collectors.toList());
        } catch (SQLException e) {
            throw new RuntimeException("Gagal mengambil data: " + e.getMessage());
        }
    }
    @Override
    public void updateUndangan(String kodeUndangan, UndanganDTO dto) {
        try {
            Undangan undangan = repository.findByKodeUndangan(kodeUndangan);
            if (undangan != null) {
                undangan.setNama(dto.getNama());
                undangan.setAlamat(dto.getAlamat());
                undangan.setJenisKelamin(dto.getJenisKelamin());
                repository.update(undangan);
            } else {
                throw new RuntimeException("Undangan dengan by kode_undangan " + kodeUndangan + " tidak ditemukan.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Gagal mengupdate data: " + e.getMessage());
        }
    }
    @Override
    public void hapusUndangan(String kode) {
        try {
            repository.delete(kode);
        } catch (SQLException e) {
            throw new RuntimeException("Gagal menghapus data: " + e.getMessage());
        }
    }

    private String generateNextKode(String lastKode) {
        if (lastKode == null) {
            return "UND-0001";
        }
        int lastNumber = Integer.parseInt(lastKode.split("-")[1]);
        int nextNumber = lastNumber + 1;            
        return String.format("UND-%04d", nextNumber);
    }
}
