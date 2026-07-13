package com.agustibayusamudro.dto;

public class UndanganDTO {    
    private String kodeUndangan;
    private String nama;
    private String alamat;
    private String jenisKelamin;   
    
    public UndanganDTO (String kodeUndangan,String nama,String alamat,String jenisKelamin) {    
        this.kodeUndangan = kodeUndangan;
        this.nama = nama;
        this.alamat = alamat;
        this.jenisKelamin = jenisKelamin;    
    }

    public String getKodeUndangan() {
        return kodeUndangan;
    }

    public String getNama() {
        return nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }
}