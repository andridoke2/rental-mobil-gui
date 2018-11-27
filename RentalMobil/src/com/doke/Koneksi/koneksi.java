package com.doke.Koneksi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.util.logging.PlatformLogger;

/**
 *
 * @author doke
 */
public class koneksi {
    
    
    public static Statement st;
    
    public koneksi(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/rentalMobil", "root", "");
            st = con.createStatement();
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public static Connection setKoneksi(){
        String con = "jdbc:mysql://localhost/rentalMobil";
        Connection koneksi = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            koneksi = DriverManager.getConnection(con, "root", "");
        } catch(ClassNotFoundException | SQLException ex){
            Logger.getLogger(koneksi.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Koneksi gagal...");
        }
        return koneksi;
    }
    
    public static int execute(String sql){
        int status = 0;
        Connection con = setKoneksi();
        try{
            Statement stat = con.createStatement();
            status = stat.executeUpdate(sql);
        } catch(SQLException ex){
            Logger.getLogger(koneksi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }
    
    public static ResultSet executeQuery(String sql){
        ResultSet rs = null;
        Connection con = setKoneksi();
        try{
            Statement stat = con.createStatement();
            rs = stat.executeQuery(sql);
        } catch(SQLException ex){
            Logger.getLogger(koneksi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
}
