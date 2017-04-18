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
import model.Buku;

/**
 *
 * @author gugi
 */
public class BukuDao {
    private Connection connection;
    private PreparedStatement insertStatement;
    private PreparedStatement updateStatement;
    private PreparedStatement deleteStatement;
    private PreparedStatement getAllStatement;
    private PreparedStatement getByIdStatement;

    private final String insertQuery = "insert into buku(judul,pengarang,penerbit,tahun) "
            + " values(?,?,?,?)";
    private final String updateQuery = "update buku set judul=?, "
            + " pengarang=?, penerbit=?, tahun=? where id=?";
    private final String deleteQuery = "delete from buku where id=?";
    private final String getByIdQuery = "select * from buku where id =?";
    private final String getAllQuery = "select * from buku";

    public void setConnection(Connection connection) throws SQLException {
        this.connection = connection;
        insertStatement = this.connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
        updateStatement = this.connection.prepareStatement(updateQuery);
        deleteStatement = this.connection.prepareStatement(deleteQuery);
        getByIdStatement = this.connection.prepareStatement(getByIdQuery);
        getAllStatement = this.connection.prepareStatement(getAllQuery);
    }
    
    public Buku save(Buku buku) throws SQLException{
        if (buku.getId() == 0) {
            insertStatement.setString(1, buku.getJudul());
            insertStatement.setString(2, buku.getPengarang());
            insertStatement.setString(3, buku.getPenerbit());
            insertStatement.setInt(4, buku.getTahun());
            int id = (int) insertStatement.executeUpdate();
            buku.setId(id);
        } else {
            insertStatement.setString(1, buku.getJudul());
            insertStatement.setString(2, buku.getPengarang());
            insertStatement.setString(3, buku.getPenerbit());
            insertStatement.setInt(4, buku.getTahun());
            updateStatement.setInt(5, buku.getId());
            updateStatement.executeUpdate();
        }
        return buku;
    }
    
    public Buku delete(Buku buku) throws SQLException {
        deleteStatement.setInt(1, buku.getId());
        deleteStatement.executeUpdate();
        return buku;
    }
    
    public Buku getById(int id) throws SQLException{
        getByIdStatement.setLong(1, id);
        ResultSet rs = getByIdStatement.executeQuery();
        //proses mapping dari relational ke object
        if (rs.next()) {
            Buku buku = new Buku();
            buku.setId(rs.getInt("id"));
            buku.setJudul(rs.getString("judul"));
            buku.setPengarang(rs.getString("pengarang"));
            buku.setPenerbit(rs.getString("penerbit"));
            buku.setTahun(rs.getInt("tahun"));
            return buku;
        }
        return null;
    }
    
    public List<Buku> getAll() throws SQLException{
        List<Buku> bukuR = new ArrayList<>();
        ResultSet rs = getAllStatement.executeQuery();
        while(rs.next()){
            Buku buku = new Buku();
            buku.setId(rs.getInt("id"));
            buku.setJudul(rs.getString("judul"));
            buku.setPengarang(rs.getString("pengarang"));
            buku.setPenerbit(rs.getString("penerbit"));
            buku.setTahun(rs.getInt("tahun"));
            bukuR.add(buku);
        }
        return bukuR;
    }
}
