/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import model.Mahasiswa;
import model.Petugas;
import service.ServiceJdbc;

/**
 *
 * @author gugi
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //buka koneksi
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser("root");
        dataSource.setPassword("");
        dataSource.setDatabaseName("perpustakaan?serverTimezone=UTC");
        dataSource.setServerName("localhost");

        dataSource.setPortNumber(3306);

        //service db
        ServiceJdbc service = new ServiceJdbc();
        service.setDataSource(dataSource);
        Scanner in = new Scanner(System.in);
        
        Boolean loggedin = false;
        Petugas petugas = null;
        
        while (!loggedin) {
            System.out.println("Silakan login terlebih dahulu!\n");
            System.out.print("Username : ");
            String username = in.nextLine();
            System.out.print("Password : ");
            String password = in.nextLine();

            petugas = service.checkLogin(username, password);

            if (petugas != null) {
                loggedin = true;
                System.out.println("\nSelamat datang, " + petugas.getNama() + "!");
                
                while (loggedin) {
                    getMainMenu();
                    String pilihan = in.nextLine();
                    switch (Integer.parseInt(pilihan)) {
                        case 1:
                            getPeminjamanMenu(petugas);
                            break;
                        case 2:
                            getPengembalianMenu(petugas);
                            break;
                        case 3:
                            getHistoriMenu();
                            break;
                        case 4:
                            getAnggotaMenu(service);
                            break;
                        case 5:
                            getBukuMenu();
                            break;
                        case 6:
                            System.out.println("\nAnda telah berhasil keluar!\n");
                            petugas = null;
                            loggedin = false;
                            break;
                        default:
                            break;
                    }
                }
                
            } else {
                System.out.println("\nUsername atau password yang anda masukkan salah!\n");
            }
        }
        
        try {
            dataSource.getConnection().close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void getMainMenu() {
        System.out.println("Menu Utama : \n");
        System.out.println("1. Peminjaman Buku");
        System.out.println("2. Pengembalian Buku");
        System.out.println("3. Histori Peminjaman");
        System.out.println("4. Pengelolaan Anggota");
        System.out.println("5. Pengelolaan Buku");
        System.out.println("6. Keluar");
        
        System.out.print("\nPilihan : ");
    }
    
    public static void getPeminjamanMenu(Petugas petugas) {
        Scanner in = new Scanner(System.in);
        System.out.println("Menu Peminjaman Buku : \n");
        System.out.println("0. Kembali ke Menu Utama");
        
        System.out.print("\nPilihan : ");
        String pilih = in.nextLine();
        switch(pilih) {
            case "0":
                break;
        }
    }
    
    public static void getPengembalianMenu(Petugas petugas) {
        System.out.println("Menu Peminjaman Buku : \n");
        System.out.println("0. Kembali ke Menu Utama");
        
        System.out.print("\nPilihan : ");
    }
    
    public static void getHistoriMenu() {
        System.out.println("Menu Histori Peminjaman : \n");
        System.out.println("0. Kembali ke Menu Utama");

        System.out.print("\nPilihan : ");
    }
    
    public static void getAnggotaMenu(ServiceJdbc service) {
        Scanner in = new Scanner(System.in);
        Boolean active = true;
        while(active) {
            System.out.println("\nMenu Pengelolaan Anggota : \n");
            System.out.println("1. Lihat Daftar Anggota");
            System.out.println("2. Tambah Anggota");
            System.out.println("3. Ubah  Anggota");
            System.out.println("4. Hapus Data Anggota");
            System.out.println("\n0. Kembali ke Menu Utama");

            System.out.print("\nPilihan : ");
            String pilih = in.nextLine();
            switch (pilih) {
                case "1":
                    List<Mahasiswa> anggotaR = service.getAllMahasiswa();
                    if (anggotaR.isEmpty()) {
                        System.out.println("\nBelum ada anggota yang terdaftar. Silakan tambahkan anggota terlebih dahulu!");
                    } else {
                        System.out.println("\nNPM\t| Nama\t\t\t| Alamat\t\t\t| No. HP");
                        System.out.println("--------------------------------------------------------------------------------------");
                        for (Mahasiswa anggota : anggotaR) {
                            System.out.println(anggota.getNpm()+"\t| "+anggota.getNama()+"\t\t| "+anggota.getAlamat()+"\t\t\t| "+anggota.getNohp());
                        }                        
                    }
                    break;
                case "2":
                    System.out.print("NPM : ");
                    String npm = in.nextLine();
                    System.out.print("Nama : ");                    
                    String nama = in.nextLine();
                    System.out.print("Alamat : ");
                    String alamat = in.nextLine();
                    System.out.print("No. HP : ");
                    String nohp = in.nextLine();
                    System.out.print("Simpan? (Y/N) : ");
                    String tambah = in.nextLine();
                    if (tambah.toLowerCase().equals("y")) {
                        Mahasiswa mhs = new Mahasiswa();
                        mhs.setNpm(Integer.parseInt(npm));
                        mhs.setNama(nama);
                        mhs.setAlamat(alamat);
                        mhs.setNohp(nohp);
                        service.save(mhs);
                    }
                    break;
                case "3":
                    System.out.print("Masukkan NPM : ");
                    String npm_x = in.nextLine();
                    Mahasiswa mhs_x = service.getMahasiswa(Integer.parseInt(npm_x));
                    if (mhs_x == null) {
                        System.out.println("Tidak ditemukan mahasiswa dengan NPM " + npm_x);
                        break;
                    }
                    System.out.print("Nama : ");                    
                    String nama_x = in.nextLine();
                    System.out.print("Alamat : ");
                    String alamat_x = in.nextLine();
                    System.out.print("No. HP : ");
                    String nohp_x = in.nextLine();
                    System.out.print("Simpan? (Y/N) : ");
                    String tambah_x = in.nextLine();
                    if (tambah_x.toLowerCase().equals("y")) {
                        mhs_x.setNpm(Integer.parseInt(npm_x));
                        mhs_x.setNama(nama_x);
                        mhs_x.setAlamat(alamat_x);
                        mhs_x.setNohp(nohp_x);
                        service.update(mhs_x);
                    }
                    break;
                case "4":
                    System.out.print("Masukkan NPM : ");
                    String npm_d = in.nextLine();
                    Mahasiswa mhs_d = service.getMahasiswa(Integer.parseInt(npm_d));
                    if (mhs_d == null) {
                        System.out.println("Tidak ditemukan mahasiswa dengan NPM "+npm_d);
                        break;
                    }
                    System.out.print("Hapus? (Y/N) : ");
                    String hapus = in.nextLine();
                    if (hapus.toLowerCase().equals("y")) {
                        service.delete(mhs_d);
                    }
                    break;
                case "0":
                    active = false;
                    break;
            }
        }
    }
    
    public static void getBukuMenu() {
        System.out.println("Menu Pengelolaan Buku : \n");
        System.out.println("0. Kembali ke Menu Utama");
        
        System.out.print("\nPilihan : ");
    }
    
}
