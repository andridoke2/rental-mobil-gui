package com.doke.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author doke
 */
public class detailSewaDAO {
    
    static detailSewaDAO instance;
    
    private static final String DB_HOST = "localhost";
    private static final String DB_PORT = "3306";
    private static final String DB_NAME = "rentalMobil";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "";
    
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://"+DB_HOST+"/"+DB_PORT+":"+DB_NAME;
    
    public static final String FIELD_ID_UNIT = "ID_UNIT";
    public static final String FIELD_NO_SEWA = "NO_SEWA";
    public static final String FIELD_HARGA = "HARGA_SEWA";
    
    private Connection con;
    private PreparedStatement st;
    
    public detailSewaDAO getInstance() throws SQLException {
        if(instance == null){
            instance = new detailSewaDAO();
        }
        return instance;
    }
    
    List<detailSewa> listDetailSewa;
    
    public detailSewaDAO() throws SQLException {
        bukaKoneksiDB();
    }
    
    private Connection bukaKoneksiDB() throws SQLException {
        try{
            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to rentalMobil...(detailSewaDAO)");
            con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            if(con != null){
                System.out.println("Successfully connect to rentalMobil...(detailSewaDAO)");
            }
            return con;
        } catch(ClassNotFoundException ex){
            ex.printStackTrace();
        }
        return null;
    }
    
    public void tutupKoneksiDB(){
        try{
            if(st != null && !st.isClosed()){
                st.close();
                System.out.println("Statement close...");
            }
            if(con != null && !con.isClosed()){
                con.close();
                System.out.println("Connection close...");
            }
        } catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    public Connection getCon(){
        return con;
    }
    
    public PreparedStatement getSt(){
        return st;
    }
    
    public detailSewa findDetailSewa(String kode) throws SQLException {
        detailSewa DetailSewa = null;
        String sql = "SELECT * FROM detail_penyewaan WHERE ID_UNIT = ?";
        System.out.println("Finding record...");
        st = con.prepareStatement(sql);
        st.setString(1, kode);
        ResultSet rs = st.executeQuery();
        while(rs.next()){
            DetailSewa = new detailSewa();
            DetailSewa.setIdUnit(rs.getString(FIELD_ID_UNIT));
            DetailSewa.setNoSewa(Integer.valueOf(rs.getString(FIELD_NO_SEWA)));
            DetailSewa.setHarga(Double.valueOf(rs.getString(FIELD_HARGA)));
            System.out.println("Record found : "+DetailSewa.getIdUnit());
        }
        rs.close();
        st.close();
        return DetailSewa;
    }
    
    public List<detailSewa> getListDetailSewa(String param) throws SQLException {
        listDetailSewa = new ArrayList<>();
        detailSewa DetailSewa = null;
        String sql;
        System.out.println("Execute Query...");
        if(param == null || param.isEmpty()){
            sql = "SELECT * FROM detail_penyewaan";
            st = con.prepareStatement(sql);
        } else {
            sql = "SELECT * FROM detail_penyewaan WHERE ID_UNIT LIKE ? OR UPPER(NO_SEWA) LIKE UPPER(?) OR UPPER(HARGA) LIKE UPPER(?)";
            st = con.prepareStatement(sql);
            st.setString(1, param);
            st.setString(2, "%"+param+"%");
            st.setString(3, "%"+param+"%");
        }
        ResultSet rs = st.executeQuery();
        while(rs.next()){
            DetailSewa = new detailSewa();
            DetailSewa.setIdUnit(rs.getString(FIELD_ID_UNIT));
            DetailSewa.setNoSewa(Integer.valueOf(rs.getString(FIELD_NO_SEWA)));
            DetailSewa.setHarga(Double.valueOf(rs.getString(FIELD_HARGA)));
            listDetailSewa.add(DetailSewa);
        }
        rs.close();
        st.close();
        System.out.println("Found "+listDetailSewa.size()+" record.");
        return listDetailSewa;
    }
    
    public List<detailSewa> getListDetailSewa() throws SQLException {
        return getListDetailSewa(null);
    }
    
    public int insertDetailSewa(detailSewa DetailSewa) throws SQLException {
        String sql = "INSERT `rentalMobil`.`detail_penyewaan` (`ID_UNIT`, `NO_SEWA`, `HARGA_SEWA`) VALUES (?, ?, ?);";
        System.out.println("Inserting new record...(detailSewa)");
        st = con.prepareStatement(sql);
        st.setString(1, DetailSewa.getIdUnit());
        st.setString(2, String.valueOf(DetailSewa.getNoSewa()));
        st.setString(3, String.valueOf(DetailSewa.getHarga()));
        int result = st.executeUpdate();
        st.close();
        System.out.println(String.valueOf(result)+" new record created. (detailSewa)");
        return result;
    }
    
    public int updateDetailSewa(detailSewa DetailSewa) throws SQLException {
        String sql = "UPDATE `rentalMobil`.`detail_penyewaan` SET `NO_SEWA`= ?, `HARGA`= ? WHERE (`ID_UNIT`= ?);";
        System.out.println("Updating new record...(detailSewa)");
        st = con.prepareStatement(sql);
        st.setString(1, String.valueOf(DetailSewa.getNoSewa()));
        st.setString(2, String.valueOf(DetailSewa.getHarga()));
        st.setString(3, DetailSewa.getIdUnit());
        int result = st.executeUpdate();
        st.close();
        System.out.println(String.valueOf(result)+" new record updated. (detailSewa)");
        return result;
    }
}
