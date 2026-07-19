package com.agustibayusamudro.services.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import com.agustibayusamudro.dto.UndanganDTO;
import com.agustibayusamudro.entities.Undangan;
import com.agustibayusamudro.repositories.UndanganRepository;
import com.agustibayusamudro.services.UndanganService;

public class UndanganServiceImpl implements UndanganService {

    private final UndanganRepository undanganRepository;
    public UndanganServiceImpl(UndanganRepository undanganRepository) {
        this.undanganRepository = undanganRepository;
    }
    
    @Override
    public void tambahUndangan(UndanganDTO dto) {
        try {        
            String lastKode = undanganRepository.findLastKodeUndangan();
            String newKode = generateNextKode(lastKode);        
            Undangan u = new Undangan(null, newKode, dto.getNama(), 
                                    dto.getAlamat(), dto.getJenisKelamin(), null, null);
            undanganRepository.save(u);
            } catch (SQLException e) {
                throw new RuntimeException("Gagal menambah data: " + e.getMessage());
        }
    }
    @Override
    public List<UndanganDTO> ambilSemuaUndangan() {
        try {
            List<Undangan> listEntity = undanganRepository.findAll();            
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
            Undangan undangan = undanganRepository.findByKodeUndangan(kodeUndangan);
            if (undangan != null) {
                undangan.setNama(dto.getNama());
                undangan.setAlamat(dto.getAlamat());
                undangan.setJenisKelamin(dto.getJenisKelamin());
                undanganRepository.update(undangan);
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
            undanganRepository.delete(kode);
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
