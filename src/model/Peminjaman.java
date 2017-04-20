/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author gugi
 */
public class Peminjaman {
    private int id;
    private int npm;
    private String namamahasiswa;
    private int idpetugas;
    private String namapetugas;
    private int jumlahbuku;
    private String waktupinjam;
    private String waktukembali;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNpm() {
        return npm;
    }

    public void setNpm(int npm) {
        this.npm = npm;
    }

    public String getNamamahasiswa() {
        return namamahasiswa;
    }

    public void setNamamahasiswa(String namamahasiswa) {
        this.namamahasiswa = namamahasiswa;
    }

    public int getIdpetugas() {
        return idpetugas;
    }

    public void setIdpetugas(int idpetugas) {
        this.idpetugas = idpetugas;
    }

    public String getNamapetugas() {
        return namapetugas;
    }

    public void setNamapetugas(String namapetugas) {
        this.namapetugas = namapetugas;
    }

    public int getJumlahbuku() {
        return jumlahbuku;
    }

    public void setJumlahbuku(int jumlahbuku) {
        this.jumlahbuku = jumlahbuku;
    }

    public String getWaktupinjam() {
        return waktupinjam;
    }

    public void setWaktupinjam(String waktupinjam) {
        this.waktupinjam = waktupinjam;
    }

    public String getWaktukembali() {
        return waktukembali;
    }

    public void setWaktukembali(String waktukembali) {
        this.waktukembali = waktukembali;
    }
}
