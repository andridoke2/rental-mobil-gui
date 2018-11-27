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
public class unitDAO {
    
    static unitDAO instance;
    
    private static final String DB_HOST = "localhost";
    private static final String DB_PORT = "3306";
    private static final String DB_NAME = "rentalMobil";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "";
    
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://"+DB_HOST+":"+DB_PORT+"/"+DB_NAME;
    
    public static final String FIELD_ID_UNIT = "ID_UNIT";
    public static final String FIELD_ID_JENIS = "ID_JENIS";
    public static final String FIELD_NAMA_UNIT = "NAMA_UNIT";
    public static final String FIELD_TAHUN_UNIT = "TAHUN";
    
    private Connection con;
    private PreparedStatement st;
    
    public static unitDAO getInstance() throws SQLException {
        if(instance == null){
            instance = new unitDAO();
        }
        return instance;
    }
    
    List<unit> listUnit;
    
    public unitDAO() throws SQLException {
        bukaKoneksiDB();
    }
    
    private Connection bukaKoneksiDB() throws SQLException {
        try{
            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to rentalMobil...(unitDAO)");
            con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            if(con != null){
                System.out.println("Successfully connect to rentalMobil...(unitDAO)");
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
                System.out.println("Connection close...(unitDAO)");
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
    
    public unit findUnit(String kode) throws SQLException {
        unit Unit = null;
        String sql = "SELECT * FROM unit WHERE ID_UNIT = ?";
        System.out.println("Finding record...");
        st = con.prepareStatement(sql);
        st.setString(1, kode);
        ResultSet rs = st.executeQuery();
        while(rs.next()){
            Unit = new unit();
            Unit.setIDUnit(rs.getString(FIELD_ID_UNIT));
            Unit.setIDJenis(rs.getString(FIELD_ID_JENIS));
            Unit.setNamaUnit(rs.getString(FIELD_NAMA_UNIT));
            Unit.setTahun(Integer.valueOf(rs.getString(FIELD_TAHUN_UNIT)));
            System.out.println("Record Found ID : "+Unit.getIDUnit());
        }
        rs.close();
        st.close();
        return Unit;
    }
    
    public List<unit> getListUnit(String param) throws SQLException {
        listUnit = new ArrayList<>();
        unit Unit = null;
        String sql;
        System.out.println("Execute Query...");
        if(param == null || param.isEmpty()){
            sql = "SELECT * FROM unit";
            st = con.prepareStatement(sql);
        } else {
            sql = "SELECT * FROM unit WHERE ID_UNIT LIKE ? OR UPPER(ID_JENIS) LIKE UPPER(?) OR UPPER(NAMA_UNIT) LIKE UPPER(?)"
                    + " OR UPPER(TAHUN) LIKE UPPER(?)";
            st = con.prepareStatement(sql);
            st.setString(1, param);
            st.setString(2, "%"+param+"%");
            st.setString(3, "%"+param+"%");
            st.setString(4, "%"+param+"%");
        }
        ResultSet rs = st.executeQuery();
        while(rs.next()){
            Unit = new unit();
            Unit.setIDUnit(rs.getString(FIELD_ID_UNIT));
            Unit.setIDJenis(rs.getString(FIELD_ID_JENIS));
            Unit.setNamaUnit(rs.getString(FIELD_NAMA_UNIT));
            Unit.setTahun(Integer.valueOf(rs.getString(FIELD_TAHUN_UNIT)));
            listUnit.add(Unit);
        }
        rs.close();
        st.close();
        System.out.println("Found "+listUnit.size()+" record.");
        return listUnit;
    }
    
    public List<unit> getListUnit() throws SQLException {
        return getListUnit(null);
    }
    
    public int insertUnit(unit Unit) throws SQLException {
        String sql = "INSERT `rentalMobil`.`unit` (`ID_UNIT`, `ID_JENIS`, `NAMA_UNIT`, `TAHUN`) VALUES (?, ?, ?, ?);";
        System.out.println("Inserting new record...");
        st = con.prepareStatement(sql);
        st.setString(1, Unit.getIDUnit());
        st.setString(2, Unit.getIDJenis());
        st.setString(3, Unit.getNamaUnit());
        st.setString(4, String.valueOf(Unit.getTahun()));
        int result = st.executeUpdate();
        st.close();
        System.out.println(String.valueOf(result)+" new record created. (unit)");
        return result;
    }
    
    public int updateUnit(unit Unit) throws SQLException {
        String sql = "UPDATE `rentalMobil`.`unit` SET `ID_JENIS`= ?, `NAMA_UNIT`= ?, `TAHUN`= ? WHERE (`ID_UNIT`= ?);";
        System.out.println("Updating record...(unit)");
        st = con.prepareStatement(sql);
        st.setString(1, Unit.getIDJenis());
        st.setString(2, Unit.getNamaUnit());
        st.setString(3, String.valueOf(Unit.getTahun()));
        st.setString(4, Unit.getIDUnit());
        int result = st.executeUpdate();
        st.close();
        System.out.println(String.valueOf(result)+" record updated. (unit)");
        return result;
    }
    
    public unit autoIDUnit(String kode) throws SQLException {
        unit Unit = null;
        String sql = "SELECT MAX(ID_UNIT) FROM unit";
        st = con.prepareStatement(sql);
        st.setString(1, kode);
        ResultSet rs = st.executeQuery();
        while(rs.next()){
            Unit = new unit();
            Unit.setIDUnit(rs.getString(FIELD_ID_UNIT));
        }
        rs.close();
        st.close();
        return Unit;
    }
}
