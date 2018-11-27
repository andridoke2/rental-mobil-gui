package com.doke.Model;

import com.doke.Frame.RentalMobilFrame;
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
public class sewaDAO {
    
    static sewaDAO instance;
    
    private static final String DB_HOST = "localhost";
    private static final String DB_PORT = "3306";
    private static final String DB_NAME = "rentalMobil";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "";
    
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://"+DB_HOST+":"+DB_PORT+"/"+DB_NAME;
    
    public static final String FIELD_NO_SEWA = "NO_SEWA";
    public static final String FIELD_ID_CUSTOMER = "ID_CUSTOMER";
    public static final String FIELD_JML_HARI = "JML_HARI";
    public static final String FIELD_JAMINAN = "JAMINAN";
    
    private Connection con;
    private PreparedStatement st;
    
    public static sewaDAO getInstance() throws SQLException{
        if(instance == null){
            instance = new sewaDAO();
        }
        return instance;
    }
    
    List<sewa> listSewa;
    
    public sewaDAO() throws SQLException {
        bukaKoneksiDB();
    }
    
    private Connection bukaKoneksiDB() throws SQLException {
        try{
            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to rentalMobil...(sewaDAO)");
            con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            if(con != null){
                System.out.println("Successfully connect to rentalMobil...(sewaDAO)");
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
                System.out.println("Connection close...(sewaDAO)");
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
    
    public sewa findSewa(String kode) throws SQLException {
        sewa Sewa = null;
        String sql = "SELECT * FROM penyewaan WHERE NO_SEWA = ?";
        System.out.println("Finding record...");
        st = con.prepareStatement(sql);
        st.setString(1, kode);
        ResultSet rs = st.executeQuery();
        while(rs.next()){
            Sewa = new sewa();
            Sewa.setNoSewa(rs.getString(FIELD_NO_SEWA));
            Sewa.setIdCustomer(rs.getString(FIELD_ID_CUSTOMER));
            Sewa.setLamaSewa(Integer.valueOf(rs.getString(FIELD_JML_HARI)));
            Sewa.setJaminan(rs.getString(FIELD_JAMINAN));
            System.out.println("Record found No : "+Sewa.getNoSewa());
        }
        rs.close();
        st.close();
        return Sewa;
    }
    
    public sewa cetak() throws SQLException {
        sewa Sewa = null;
        String sql = "select p.NO_SEWA, c.NAMA_DPN, c.NAMA_BLKG, m.NAMA_JENIS, u.NAMA_UNIT, p.JML_HARI, p.JAMINAN, d.HARGA_SEWA "
                + "from penyewaan p inner join customer c on p.ID_CUSTOMER=c.ID_CUSTOMER right join detail_penyewaan  d on p.NO_SEWA=d.NO_SEWA "
                + "inner join unit u on d.ID_UNIT=u.ID_UNIT inner join merk_unit m on u.ID_JENIS=m.ID_JENIS order by p.NO_SEWA";
        System.out.println("Finding record...");
        st = con.prepareStatement(sql);
        ResultSet rs = st.executeQuery();
        while(rs.next()){
            Sewa = new sewa();
            Sewa.setNoSewa(rs.getString(1));
            Sewa.setNama_depan(rs.getString(2));
            Sewa.setNama_belakang(rs.getString(3));
            Sewa.setNamaJenis(rs.getString(4));
            Sewa.setNamaUnit(rs.getString(5));
            Sewa.setLamaSewa(Integer.valueOf(rs.getString(6)));
            Sewa.setJaminan(rs.getString(7));
            Sewa.setHarga(Double.valueOf(RentalMobilFrame.harga));
        }
        rs.close();
        st.close();
        return Sewa;
    }
    
    public sewa cetak(String x) throws SQLException {
        sewa Sewa = null;
        String sql = "select p.NO_SEWA, c.NAMA_DPN, c.NAMA_BLKG, m.NAMA_JENIS, u.NAMA_UNIT, p.JML_HARI, p.JAMINAN, d.HARGA_SEWA "
                + "from penyewaan p inner join customer c on p.ID_CUSTOMER=c.ID_CUSTOMER right join detail_penyewaan  d on p.NO_SEWA=d.NO_SEWA "
                + "inner join unit u on d.ID_UNIT=u.ID_UNIT inner join merk_unit m on u.ID_JENIS=m.ID_JENIS where p.NO_SEWA = ?";
        System.out.println("Finding record...");
        st = con.prepareStatement(sql);
        st.setString(1, x);
        ResultSet rs = st.executeQuery();
        while(rs.next()){
            Sewa = new sewa();
            Sewa.setNoSewa(rs.getString(1));
            Sewa.setNama_depan(rs.getString(2));
            Sewa.setNama_belakang(rs.getString(3));
            Sewa.setNamaJenis(rs.getString(4));
            Sewa.setNamaUnit(rs.getString(5));
            Sewa.setLamaSewa(Integer.valueOf(rs.getString(6)));
            Sewa.setJaminan(rs.getString(7));
            Sewa.setHarga(Double.valueOf(RentalMobilFrame.harga));
        }
        rs.close();
        st.close();
        return Sewa;
    }
    
    public List<sewa> getListSewa(String param) throws SQLException {
        listSewa = new ArrayList<>();
        sewa Sewa = null;
        String sql;
        System.out.println("Execute Query...");
        if(param == null || param.isEmpty()){
            sql = "SELECT * FROM penyewaan";
            st = con.prepareStatement(sql);
        } else {
            sql = "SELECT * FROM penyewaan WHERE NO_SEWA LIKE ? OR UPPER(ID_CUSTOMER) LIKE UPPER(?) OR UPPER(JML_HARI) LIKE UPPER(?) "
                    + "OR UPPER(JAMINAN) LIKE UPPER(?)";
            st = con.prepareStatement(sql);
            st.setString(1, param);
            st.setString(2, "%"+param+"%");
            st.setString(3, "%"+param+"%");
            st.setString(4, "%"+param+"%");
        }
        ResultSet rs = st.executeQuery();
        while(rs.next()){
            Sewa = new sewa();
            Sewa.setNoSewa(rs.getString(FIELD_NO_SEWA));
            Sewa.setIdCustomer(rs.getString(FIELD_ID_CUSTOMER));
            Sewa.setLamaSewa(Integer.valueOf(rs.getString(FIELD_JML_HARI)));
            Sewa.setJaminan(rs.getString(FIELD_JAMINAN));
            listSewa.add(Sewa);
        }
        rs.close();
        st.close();
        System.out.println("Found "+listSewa.size()+" record");
        return listSewa;
    }
    
    public List<sewa> getListSewa() throws SQLException {
        return getListSewa(null);
    }
    
    public int insertSewa(sewa Sewa) throws SQLException {
        String sql = "INSERT `rentalMobil`.`penyewaan` (`NO_SEWA`, `ID_CUSTOMER`, `JML_HARI`, `JAMINAN`) VALUES (?, ?, ?, ?);";
        System.out.println("Inserting new record...");
        st = con.prepareStatement(sql);
        st.setString(1, String.valueOf(Sewa.getNoSewa()));
        st.setString(2, Sewa.getIdCustomer());
        st.setString(3, String.valueOf(Sewa.getLamaSewa()));
        st.setString(4, Sewa.getJaminan());
        int result = st.executeUpdate();
        st.close();
        System.out.println(String.valueOf(result)+" new record created. (sewa)");
        return result;
    }
    
    public int updateSewa(sewa Sewa) throws SQLException {
        String sql = "UPDATE `rentalMobil`.`penyewaan` SET `ID_CUSTOMER`= ?, `JML_HARI`= ?, `JAMINAN`= ? WHERE (`NO_SEWA`= ?);";
        System.out.println("Updating new record...(sewa)");
        st = con.prepareStatement(sql);
        st.setString(1, Sewa.getIdCustomer());
        st.setString(2, String.valueOf(Sewa.getLamaSewa()));
        st.setString(3, Sewa.getJaminan());
        st.setString(4, String.valueOf(Sewa.getNoSewa()));
        int result = st.executeUpdate();
        st.close();
        System.out.println(String.valueOf(result)+" record updated. (sewa)");
        return result;
    }
}
