package com.doke.Model;

import java.text.SimpleDateFormat;

/**
 *
 * @author doke
 */
public class customer extends unit {
    
    String id_customer;
    String nama_depan;
    String nama_belakang;
    char jk;
    String alamat;
    String kontak;
    
    SimpleDateFormat inputDateFormatC;
    SimpleDateFormat outputDateFormatC;
    SimpleDateFormat dbDateFormatC;
    
    public static final char JK_LAKI = 'L';
    public static final char JK_PEREMPUAN = 'P';
    
    public customer(){
        inputDateFormatC = new SimpleDateFormat("dd-MM-yyyy");
        outputDateFormatC = new SimpleDateFormat("dd-MM-yyyy");
        dbDateFormatC = new SimpleDateFormat("yyyy-MM-dd");
    }
    
    public customer(String id_customer, String nama_depan){
        this();
        this.id_customer = id_customer;
        this.nama_depan = nama_depan;
        this.nama_belakang = "Kosong";
        this.jk = JK_LAKI;
        this.alamat = "Kosong";
        this.kontak = "Kosong";
    }
    
    public customer(String id_customer, String nama_depan, String nama_belakang, char jk, String alamat, String kontak){
        this();
        this.id_customer = id_customer;
        this.nama_depan = nama_depan;
        this.nama_belakang = nama_belakang;
        this.jk = JK_LAKI;
        this.alamat = alamat;
        this.kontak = kontak;
    }
    
    public void setId_customer(String id_customer){
        this.id_customer = id_customer;
    }
    
    public String getId_customer(){
        return id_customer;
    }
    
    public void setNama_depan(String nama_depan){
        this.nama_depan = nama_depan;
    }
    
    public String getNama_depan(){
        return nama_depan;
    }
    
    public void setNama_belakang(String nama_belakang){
        this.nama_belakang = nama_belakang;
    }
    
    public String getNama_belakang(){
        return nama_belakang;
    }
    
    public void setJk(char jk){
        this.jk = jk;
    }
    
    public char getJk(){
        return jk;
    }
    
    public void setAlamat(String alamat){
        this.alamat = alamat;
    }
    
    public String getAlamat(){
        return alamat;
    }
    
    public void setKontak(String kontak){
        this.kontak = kontak;
    }
    
    public String getKontak(){
        return kontak;
    }
}
