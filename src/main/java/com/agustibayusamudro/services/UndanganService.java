package com.agustibayusamudro.services;

import java.util.List;

import com.agustibayusamudro.dto.UndanganDTO;

public interface UndanganService {
    void tambahUndangan(UndanganDTO dto);
    List<UndanganDTO> ambilSemuaUndangan();
    void updateUndangan(String kodeUndangan, UndanganDTO dto);
    void hapusUndangan(String kode);
}
