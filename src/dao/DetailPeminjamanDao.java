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
import model.DetailPeminjaman;

/**
 *
 * @author gugi
 */
public class DetailPeminjamanDao {
    private Connection connection;
    private PreparedStatement insertStatement;
    private PreparedStatement updateStatement;
    private PreparedStatement deleteStatement;
    private PreparedStatement getAllStatement;
    private PreparedStatement getByIdPeminjamanStatement;
    private PreparedStatement getByIdStatement;

    private final String insertQuery = "insert into detailpeminjaman(idpeminjaman,idbuku) "
            + " values(?,?)";
    private final String updateQuery = "update detailpeminjaman set idpeminjaman=?, "
            + " idbuku=? where id=?";
    private final String deleteQuery = "delete from detailpeminjaman where id=?";
    private final String getByIdQuery = "select * from detailpeminjaman where id =?";
    private final String getByIdPeminjamanQuery = "select * from detailpeminjaman JOIN buku ON detailpeminjaman.idbuku = buku.id where idpeminjaman =?";
    private final String getAllQuery = "select * from detailpeminjaman JOIN buku ON detailpeminjaman.idbuku = buku.id";

    public void setConnection(Connection connection) throws SQLException {
        this.connection = connection;
        insertStatement = this.connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
        updateStatement = this.connection.prepareStatement(updateQuery);
        deleteStatement = this.connection.prepareStatement(deleteQuery);
        getByIdStatement = this.connection.prepareStatement(getByIdQuery);
        getByIdPeminjamanStatement = this.connection.prepareStatement(getByIdPeminjamanQuery);
        getAllStatement = this.connection.prepareStatement(getAllQuery);
    }
    
    public DetailPeminjaman save(DetailPeminjaman detailpeminjaman) throws SQLException{
        if (detailpeminjaman.getId() == 0) {
            insertStatement.setInt(1, detailpeminjaman.getIdpeminjaman());
            insertStatement.setInt(2, detailpeminjaman.getIdbuku());
            int id = (int) insertStatement.executeUpdate();
            detailpeminjaman.setId(id);
        } else {
            updateStatement.setInt(1, detailpeminjaman.getIdpeminjaman());
            updateStatement.setInt(2, detailpeminjaman.getIdbuku());
            updateStatement.setInt(3, detailpeminjaman.getId());
            updateStatement.executeUpdate();
        }
        return detailpeminjaman;
    }
    
    public DetailPeminjaman delete(DetailPeminjaman detailpeminjaman) throws SQLException {
        deleteStatement.setInt(1, detailpeminjaman.getId());
        deleteStatement.executeUpdate();
        return detailpeminjaman;
    }
    
    public DetailPeminjaman getById(int id) throws SQLException{
        getByIdStatement.setLong(1, id);
        ResultSet rs = getByIdStatement.executeQuery();
        //proses mapping dari relational ke object
        if (rs.next()) {
            DetailPeminjaman detailpeminjaman = new DetailPeminjaman();
            detailpeminjaman.setId(rs.getInt("id"));
            detailpeminjaman.setIdpeminjaman(rs.getInt("idpeminjaman"));
            detailpeminjaman.setIdbuku(rs.getInt("idbuku"));
            return detailpeminjaman;
        }
        return null;
    }
    
    public List<DetailPeminjaman> getByIdPeminjaman(int idpeminjaman) throws SQLException {
        List<DetailPeminjaman> detailpeminjamanR = new ArrayList<>();
        getByIdPeminjamanStatement.setInt(1, idpeminjaman);
        ResultSet rs = getByIdPeminjamanStatement.executeQuery();
        while (rs.next()) {
            DetailPeminjaman detailpeminjaman = new DetailPeminjaman();
            detailpeminjaman.setId(rs.getInt("id"));
            detailpeminjaman.setIdpeminjaman(rs.getInt("idpeminjaman"));
            detailpeminjaman.setIdbuku(rs.getInt("idbuku"));
            detailpeminjaman.setJudulbuku(rs.getString("judul"));
            detailpeminjamanR.add(detailpeminjaman);
        }
        return detailpeminjamanR;
    }
    
    public List<DetailPeminjaman> getAll() throws SQLException{
        List<DetailPeminjaman> detailpeminjamanR = new ArrayList<>();
        ResultSet rs = getAllStatement.executeQuery();
        while(rs.next()){
            DetailPeminjaman detailpeminjaman = new DetailPeminjaman();
            detailpeminjaman.setId(rs.getInt("id"));
            detailpeminjaman.setIdpeminjaman(rs.getInt("idpeminjaman"));
            detailpeminjaman.setIdbuku(rs.getInt("idbuku"));
            detailpeminjaman.setJudulbuku(rs.getString("judul"));
            detailpeminjamanR.add(detailpeminjaman);
        }
        return detailpeminjamanR;
    }
}
