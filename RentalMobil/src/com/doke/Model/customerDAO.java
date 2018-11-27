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
public class customerDAO {
    
    static customerDAO instance;
    
    private static final String DB_HOST = "localhost";
    private static final String DB_PORT = "3306";
    private static final String DB_NAME = "rentalMobil";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "";
    
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://"+DB_HOST+":"+DB_PORT+"/"+DB_NAME;
    
    public static final String FIELD_ID = "ID_CUSTOMER";
    public static final String FIELD_NAMA_DEPAN = "NAMA_DPN";
    public static final String FIELD_NAMA_BELAKANG = "NAMA_BLKG";
    public static final String FIELD_JK = "JK";
    public static final String FIELD_ALAMAT = "ALAMAT";
    public static final String FIELD_TELP = "TELP_CUSTOMER";
    
    private Connection con;
    private PreparedStatement st;
    
    public static customerDAO getInstance() throws SQLException {
        if(instance == null){
            instance = new customerDAO();
        }
        return instance;
    }
    
    List<customer> listCustomer;
    
    public customerDAO() throws SQLException {
        bukaKoneksiDB();
    }
    
    private Connection bukaKoneksiDB() throws SQLException {
        try{
            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to rentalMobil...(customerDAO)");
            con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            if(con != null){
                System.out.println("Successfully connect to rentalMobil...(customerDAO)");
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
                System.out.println("Connection close...(customerDAO)");
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
    
    public customer findCustomer(String kode) throws SQLException {
        customer customer = null;
        String sql = "SELECT * FROM customer WHERE ID_CUSTOMER = ?";
        System.out.println("Finding record...");
        st = con.prepareStatement(sql);
        st.setString(1, kode);
        ResultSet rs = st.executeQuery();
        while(rs.next()){
            customer = new customer();
            customer.setId_customer(rs.getString(FIELD_ID));
            customer.setNama_depan(rs.getString(FIELD_NAMA_DEPAN));
            customer.setNama_belakang(rs.getString(FIELD_NAMA_BELAKANG));
            customer.setJk(rs.getString(FIELD_JK).charAt(0));
            customer.setAlamat(rs.getString(FIELD_ALAMAT));
            customer.setKontak(rs.getString(FIELD_TELP));
            System.out.println("Record found ID : "+customer.getId_customer());
        }
        rs.close();
        st.close();
        return customer;
    }
    
    public List<customer> getListCustomer(String param) throws SQLException {
        listCustomer = new ArrayList<>();
        customer customer = null;
        String sql;
        System.out.println("Execute Query...");
        if(param == null || param.isEmpty()){
            sql = "SELECT ID_CUSTOMER, NAMA_DPN FROM customer";
            st = con.prepareStatement(sql);
        } else {
            sql = "SELECT ID_CUSTOMER, NAMA_DPN FROM customer WHERE ID_CUSTOMER LIKE ? OR UPPER(NAMA_DPN) LIKE UPPER(?)";
            st = con.prepareStatement(sql);
            st.setString(1, param);
            st.setString(2, "%"+param+"%");
        }
        ResultSet rs = st.executeQuery();
        while(rs.next()){
            customer = new customer();
            customer.setId_customer(rs.getString(FIELD_ID));
            customer.setNama_depan(rs.getString(FIELD_NAMA_DEPAN));
            listCustomer.add(customer);
        }
        rs.close();
        st.close();
        System.out.println("Found "+listCustomer.size()+" record");
        return listCustomer;
    }
    
    public List<customer> getListCustomer() throws SQLException {
        return getListCustomer(null);
    }
    
    public int insertCustomer(customer customer) throws SQLException {
        String sql = "INSERT `rentalMobil`.`customer` (`ID_CUSTOMER`, `NAMA_DPN`, `NAMA_BLKG`, `JK`, `ALAMAT`, `TELP_CUSTOMER`) "
                + "VALUES (?, ?, ?, ?, ?, ?);";
        System.out.println("Inserting new record...");
        st = con.prepareStatement(sql);
        st.setString(1, customer.getId_customer());
        st.setString(2, customer.getNama_depan());
        st.setString(3, customer.getNama_belakang());
        st.setString(4, String.valueOf(customer.getJk()));
        st.setString(5, customer.getAlamat());
        st.setString(6, customer.getKontak());
        int result = st.executeUpdate();
        st.close();
        System.out.println(String.valueOf(result)+" new record created. (customer)");
        return result;
    }
    
    public int updateCustomer(customer customer) throws SQLException {
        String sql = "UPDATE `rentalMobil`.`customer` SET `NAMA_DPN`= ?, `NAMA_BLKG`= ?, `JK`= ?, `ALAMAT`= ?, `TELP_CUSTOMER`= ? "
                + "WHERE (`ID_CUSTOMER`= ?);";
        System.out.println("Updating record...(customer)");
        st = con.prepareStatement(sql);
        st.setString(1, customer.getNama_depan());
        st.setString(2, customer.getNama_belakang());
        st.setString(3, String.valueOf(customer.getJk()));
        st.setString(4, customer.getAlamat());
        st.setString(5, customer.getKontak());
        st.setString(6, customer.getId_customer());
        int result = st.executeUpdate();
        st.close();
        System.out.println(String.valueOf(result)+" record updated. (customer)");
        return result;
    }
    
    public int deleteCustomer(String idCustomer) throws SQLException {
        String sql = "DELETE FROM customer WHERE ID_CUSTOMER = ?";
        st = con.prepareStatement(sql);
        st.setString(1, idCustomer);
        int result = st.executeUpdate();
        st.close();
        System.out.println(String.valueOf(result)+" record delete. (customer)");
        return result;
    }
    
    public customer autoIdCustomer(String kode) throws SQLException {
        customer customer = null;
        String sql = "SELECT MAX(ID_CUSTOMER) FROM customer";
        st = con.prepareStatement(sql);
        st.setString(1, kode);
        ResultSet rs = st.executeQuery();
        while(rs.next()){
            customer = new customer();
            customer.setId_customer(rs.getString(FIELD_ID));
        }
        rs.close();
        st.close();
        return customer;
    }
}
