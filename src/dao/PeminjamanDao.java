/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Peminjaman;

/**
 *
 * @author gugi
 */
public class PeminjamanDao {
    private Connection connection;
    private PreparedStatement insertStatement;
    private PreparedStatement updateStatement;
    private PreparedStatement deleteStatement;
    private PreparedStatement getAllStatement;
    private PreparedStatement getByIdStatement;

    private final String insertQuery = "insert into peminjaman(npm,idpetugas,waktupinjam,waktukembali) "
            + " values(?,?,?,?)";
    private final String updateQuery = "update peminjaman set npm=?, "
            + " idpetugas=?, waktupinjam=?, waktukembali=? where id=?";
    private final String deleteQuery = "delete from peminjaman where id=?";
    private final String getByIdQuery = "select *, COUNT(detailpeminjaman.idbuku) as jumlahbuku from peminjaman JOIN mahasiswa ON peminjaman.npm = mahasiswa.npm JOIN petugas ON peminjaman.idpetugas = petugas.id JOIN detailpeminjaman ON peminjaman.id = detailpeminjaman.idpeminjaman where peminjaman.id =?";
    private final String getAllQuery = "select *, COUNT(peminjaman.id) as jumlahbuku from peminjaman JOIN mahasiswa ON peminjaman.npm = mahasiswa.npm JOIN petugas ON peminjaman.idpetugas = petugas.id RIGHT OUTER JOIN detailpeminjaman ON peminjaman.id = detailpeminjaman.idpeminjaman GROUP BY peminjaman.id";

    public void setConnection(Connection connection) throws SQLException {
        this.connection = connection;
        insertStatement = this.connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
        updateStatement = this.connection.prepareStatement(updateQuery);
        deleteStatement = this.connection.prepareStatement(deleteQuery);
        getByIdStatement = this.connection.prepareStatement(getByIdQuery);
        getAllStatement = this.connection.prepareStatement(getAllQuery);
    }
    
    public Peminjaman save(Peminjaman peminjaman) throws SQLException{
        if (peminjaman.getId() == 0) {
            insertStatement.setInt(1, peminjaman.getNpm());
            insertStatement.setInt(2, peminjaman.getIdpetugas());
            insertStatement.setString(3, peminjaman.getWaktupinjam());
            insertStatement.setString(4, peminjaman.getWaktukembali());
            int id = (int) insertStatement.executeUpdate();
            try (ResultSet generatedKeys = insertStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    peminjaman.setId(generatedKeys.getInt(1));
                } else {
                    System.out.println("Gagal menyimpan peminjaman baru");
                }
            }
        } else {
            updateStatement.setInt(1, peminjaman.getNpm());
            updateStatement.setInt(2, peminjaman.getIdpetugas());
            updateStatement.setString(3, peminjaman.getWaktupinjam());
            updateStatement.setString(4, peminjaman.getWaktukembali());
            updateStatement.setInt(5, peminjaman.getId());
            updateStatement.executeUpdate();
        }
        return peminjaman;
    }
    
    public Peminjaman delete(Peminjaman peminjaman) throws SQLException {
        deleteStatement.setInt(1, peminjaman.getId());
        deleteStatement.executeUpdate();
        return peminjaman;
    }
    
    public Peminjaman getById(int id) throws SQLException{
        getByIdStatement.setLong(1, id);
        ResultSet rs = getByIdStatement.executeQuery();
        //proses mapping dari relational ke object
        if (rs.next()) {
            Peminjaman peminjaman = new Peminjaman();
            peminjaman.setId(rs.getInt("id"));
            peminjaman.setNpm(rs.getInt("npm"));
            peminjaman.setNamamahasiswa(rs.getString("mahasiswa.nama"));
            peminjaman.setIdpetugas(rs.getInt("idpetugas"));
            peminjaman.setNamapetugas(rs.getString("petugas.nama"));
            peminjaman.setWaktupinjam(rs.getString("waktupinjam"));
            peminjaman.setWaktukembali(rs.getString("waktukembali"));
            peminjaman.setJumlahbuku(rs.getInt("jumlahbuku"));
            return peminjaman;
        }
        return null;
    }
    
    public List<Peminjaman> getAll() throws SQLException{
        List<Peminjaman> peminjamanR = new ArrayList<>();
        ResultSet rs = getAllStatement.executeQuery();
        while(rs.next()){
            Peminjaman peminjaman = new Peminjaman();
            peminjaman.setId(rs.getInt("id"));
            peminjaman.setNpm(rs.getInt("npm"));
            peminjaman.setNamamahasiswa(rs.getString("mahasiswa.nama"));
            peminjaman.setIdpetugas(rs.getInt("idpetugas"));
            peminjaman.setNamapetugas(rs.getString("petugas.nama"));
            peminjaman.setWaktupinjam(rs.getString("waktupinjam"));
            peminjaman.setWaktukembali(rs.getString("waktukembali"));
            peminjaman.setJumlahbuku(rs.getInt("jumlahbuku"));
            peminjamanR.add(peminjaman);
        }
        return peminjamanR;
    }
}
