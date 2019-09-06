/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverreservasihotel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author A3
 */
public class KoneksiDB {
    private Connection koneksi;
    private PreparedStatement ps;
    private ResultSet rs;
    
    public Connection getKoneksi(){
        if(koneksi == null){
            try{
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            try{
                String url = "jdbc:jtds:sqlserver://localhost:1433/dbreservasihotel";
                koneksi = DriverManager.getConnection(url, "sa", "admin");
                System.out.println("Koneksi Database Sukses");
            }catch(SQLException se){
                System.out.println("Koneksi Database Gagal, Error : "+se);
                System.exit(0);
            }
        }catch(ClassNotFoundException cnfe){
                System.out.println("Class Tidak Ditemukan, Error :"+cnfe);
                System.exit(0);
                }
    }
    return koneksi;
    }

 public boolean eksekusiNonQuery(String query){
    try {
    ps = koneksi.prepareStatement(query);
    ps.executeUpdate();
    return true;
    }catch(SQLException e){
    System.out.println("Salah Query : "+e);
    return false;
} }   

public ResultSet eksekusiQuery(String query){
    try{
        ps = koneksi.prepareStatement(query);
        return ps.executeQuery();
    }catch(SQLException e){
        System.out.println("Salah Query : "+e);
    return null;
    }
}
    public static void main(String[] args) {
        KoneksiDB kon = new KoneksiDB();
        kon.getKoneksi();
    }
}
