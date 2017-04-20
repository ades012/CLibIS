package service;


import dao.MahasiswaDao;
import dao.PetugasDao;
import dao.BukuDao;
import dao.PeminjamanDao;
import dao.DetailPeminjamanDao;
import model.Petugas;
import model.Mahasiswa;
import model.Buku;
import model.Peminjaman;
import model.DetailPeminjaman;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author gugi
 */
public class ServiceJdbc {

    private PetugasDao petugasDao;
    private MahasiswaDao mahasiswaDao;
    private BukuDao bukuDao;
    private PeminjamanDao peminjamanDao;
    private DetailPeminjamanDao detailpeminjamanDao;
    private Connection connection;
    
    public void setDataSource(DataSource dataSource){
        try {
            connection = dataSource.getConnection();
            petugasDao = new PetugasDao();
            mahasiswaDao = new MahasiswaDao();
            bukuDao = new BukuDao();
            peminjamanDao = new PeminjamanDao();
            detailpeminjamanDao = new DetailPeminjamanDao();
            petugasDao.setConnection(connection);
            mahasiswaDao.setConnection(connection);
            bukuDao.setConnection(connection);
            peminjamanDao.setConnection(connection);
            detailpeminjamanDao.setConnection(connection);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public Petugas save(Petugas petugas){
        try {
            connection.setAutoCommit(false);
            petugasDao.save(petugas);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            try{
                connection.rollback();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return petugas;
    }
    public Petugas delete(Petugas petugas){
        try {
            connection.setAutoCommit(false);
            petugasDao.save(petugas);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            try{
                connection.rollback();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return petugas;
    }
    public Petugas getPetugas(int id){
        try {
            return petugasDao.getById(id);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public Petugas checkLogin(String username, String password){
        try {
            return petugasDao.checkLogin(username, password);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public Mahasiswa save(Mahasiswa mahasiswa) {
        try {
            connection.setAutoCommit(false);
            mahasiswaDao.save(mahasiswa);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return mahasiswa;
    }
    
    public Mahasiswa update(Mahasiswa mahasiswa) {
        try {
            connection.setAutoCommit(false);
            mahasiswaDao.update(mahasiswa);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return mahasiswa;
    }

    public Mahasiswa delete(Mahasiswa mahasiswa) {
        try {
            connection.setAutoCommit(false);
            mahasiswaDao.delete(mahasiswa);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return mahasiswa;
    }

    public Mahasiswa getMahasiswa(int npm) {
        try {
            return mahasiswaDao.getByNpm(npm);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public List<Mahasiswa> getAllMahasiswa() {
        try {
            return mahasiswaDao.getAll();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public Buku save(Buku buku) {
        try {
            connection.setAutoCommit(false);
            bukuDao.save(buku);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return buku;
    }

    public Buku delete(Buku buku) {
        try {
            connection.setAutoCommit(false);
            bukuDao.delete(buku);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return buku;
    }

    public Buku getBuku(int id) {
        try {
            return bukuDao.getById(id);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public List<Buku> getBukuBySearch(String cari) {
        try {
            return bukuDao.getBySearch(cari);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public List<Buku> getAllBuku() {
        try {
            return bukuDao.getAll();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public Peminjaman save(Peminjaman peminjaman) {
        try {
            connection.setAutoCommit(false);
            peminjamanDao.save(peminjaman);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return peminjaman;
    }

    public Peminjaman delete(Peminjaman peminjaman) {
        try {
            connection.setAutoCommit(false);
            peminjamanDao.delete(peminjaman);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return peminjaman;
    }

    public Peminjaman getPeminjaman(int id) {
        try {
            return peminjamanDao.getById(id);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public List<Peminjaman> getAllPeminjaman() {
        try {
            return peminjamanDao.getAll();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public DetailPeminjaman save(DetailPeminjaman detail) {
        try {
            connection.setAutoCommit(false);
            detailpeminjamanDao.save(detail);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return detail;
    }

    public DetailPeminjaman delete(DetailPeminjaman detail) {
        try {
            connection.setAutoCommit(false);
            detailpeminjamanDao.delete(detail);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return detail;
    }

    public DetailPeminjaman getDetailPeminjaman(int id) {
        try {
            return detailpeminjamanDao.getById(id);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public List<DetailPeminjaman> getDetailPeminjamanByIdPeminjaman(int idpeminjaman) {
        try {
            return detailpeminjamanDao.getByIdPeminjaman(idpeminjaman);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public List<DetailPeminjaman> getAllDetailPeminjaman() {
        try {
            return detailpeminjamanDao.getAll();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
