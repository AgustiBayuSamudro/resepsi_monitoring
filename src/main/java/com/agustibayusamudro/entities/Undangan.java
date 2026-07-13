package com.agustibayusamudro.entities;

import java.time.LocalDateTime;

public class Undangan {
    private Integer undanganId;
    private String kodeUndangan;
    private String nama;
    private String alamat;
    private String jenisKelamin;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Undangan (Integer undanganId,String kodeUndangan,String nama,String alamat,String jenisKelamin,LocalDateTime createdAt,LocalDateTime updatedAt) {
        this.undanganId = undanganId;
        this.kodeUndangan = kodeUndangan;
        this.nama = nama;
        this.alamat = alamat;
        this.jenisKelamin = jenisKelamin;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getUndanganId() {
        return undanganId;
    }
    public void setUndanganId(int undanganId) {
        this.undanganId = undanganId;
    }
    public String getKodeUndangan() {
        return kodeUndangan;
    }
    public void setKodeUndangan(String kodeUndangan) {
        this.kodeUndangan = kodeUndangan;
    }
    public String getNama() {
        return nama;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }
    public String getAlamat() {
        return alamat;
    }
    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
    public String getJenisKelamin() {
        return jenisKelamin;
    }
    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}