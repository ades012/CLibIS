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
import model.Petugas;

/**
 *
 * @author gugi
 */
public class PetugasDao {
    private Connection connection;
    private PreparedStatement insertStatement;
    private PreparedStatement updateStatement;
    private PreparedStatement deleteStatement;
    private PreparedStatement getAllStatement;
    private PreparedStatement getByIdStatement;
    private PreparedStatement checkLoginStatement;

    private final String insertQuery = "insert into petugas(username,password,nama) "
            + " values(?,?,?)";
    private final String updateQuery = "update petugas set username=?, "
            + " password=?, nama=? where id=?";
    private final String deleteQuery = "delete from petugas where id=?";
    private final String getByIdQuery = "select * from petugas where id =?";
    private final String getAllQuery = "select * from petugas";
    private final String checkLoginQuery = "select * from petugas where username=? AND password=?";

    public void setConnection(Connection connection) throws SQLException {
        this.connection = connection;
        insertStatement = this.connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
        updateStatement = this.connection.prepareStatement(updateQuery);
        deleteStatement = this.connection.prepareStatement(deleteQuery);
        getByIdStatement = this.connection.prepareStatement(getByIdQuery);
        getAllStatement = this.connection.prepareStatement(getAllQuery);
        checkLoginStatement = this.connection.prepareStatement(checkLoginQuery);
    }
    
    public Petugas save(Petugas petugas) throws SQLException{
        if (petugas.getId() == 0) {
            insertStatement.setString(1, petugas.getUsername());
            insertStatement.setString(2, petugas.getPassword());
            insertStatement.setString(3, petugas.getNama());
            int id = (int) insertStatement.executeUpdate();
            petugas.setId(id);
        } else {
            updateStatement.setString(1, petugas.getUsername());
            updateStatement.setString(2, petugas.getPassword());
            updateStatement.setString(3, petugas.getNama());
            updateStatement.setInt(4, petugas.getId());
            updateStatement.executeUpdate();
        }
        return petugas;
    }
    
    public Petugas delete(Petugas petugas) throws SQLException {
        deleteStatement.setInt(1, petugas.getId());
        deleteStatement.executeUpdate();
        return petugas;
    }
    
    public Petugas getById(int id) throws SQLException{
        getByIdStatement.setInt(1, id);
        ResultSet rs = getByIdStatement.executeQuery();
        //proses mapping dari relational ke object
        if (rs.next()) {
            Petugas petugas = new Petugas();
            petugas.setId(rs.getInt("id"));
            petugas.setUsername(rs.getString("username"));
            petugas.setPassword(rs.getString("password"));
            petugas.setNama(rs.getString("nama"));
            return petugas;
        }
        return null;
    }
    
    public List<Petugas> getAll() throws SQLException{
        List<Petugas> petugasR = new ArrayList<>();
        ResultSet rs = getAllStatement.executeQuery();
        while(rs.next()){
            Petugas petugas = new Petugas();
            petugas.setId(rs.getInt("id"));
            petugas.setUsername(rs.getString("username"));
            petugas.setPassword(rs.getString("password"));
            petugas.setNama(rs.getString("nama"));
            petugasR.add(petugas);
        }
        return petugasR;
    }
    
    public Petugas checkLogin(String username, String password) throws SQLException{
        checkLoginStatement.setString(1, username);
        checkLoginStatement.setString(2, MD5(password));
        ResultSet rs = checkLoginStatement.executeQuery();
        //proses mapping dari relational ke object
        if (rs.next()) {
            Petugas petugas = new Petugas();
            petugas.setId(rs.getInt("id"));
            petugas.setUsername(rs.getString("username"));
            petugas.setNama(rs.getString("nama"));
            return petugas;
        }
        return null;
    }
    
    public String MD5(String md5) {
        try {
             java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
             byte[] array = md.digest(md5.getBytes());
             StringBuffer sb = new StringBuffer();
             for (int i = 0; i < array.length; ++i) {
               sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
             return sb.toString();
         } catch (java.security.NoSuchAlgorithmException e) {
         }
         return null;
     }
}
