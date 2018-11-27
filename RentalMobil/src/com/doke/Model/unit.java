package com.doke.Model;

import java.text.SimpleDateFormat;

/**
 *
 * @author doke
 */
public class unit {
    
    String id_unit;
    String nama_unit;
    String id_jenis;
    int tahun;
    String namaJenis;
    double harga;
    
    SimpleDateFormat inputDateFormat;
    SimpleDateFormat outputDateFormat;
    SimpleDateFormat dbDateFormat;
    
    public unit(){
        inputDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        outputDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dbDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    }
    
    public unit(String id_unit){
        this();
        this.id_unit = id_unit;
        this.nama_unit = "Kosong";
        this.id_jenis = "Kosong";
        this.tahun = 0;
    }
    
    public unit(String id, String unit){
        this();
        this.id_unit = id;
        this.nama_unit = unit;
        this.id_jenis = "Kosong";
        this.tahun = 0;
    }
    
    public unit(String id, String unit, String jenis, int tahun){
        this();
        this.id_unit = id;
        this.nama_unit = unit;
        this.id_jenis = jenis;
        this.tahun = tahun;
    }
    
    public void setIDUnit(String id){
        this.id_unit = id;
    }
    
    public String getIDUnit(){
        return id_unit;
    }
    
    public void setNamaUnit(String unit){
        this.nama_unit = unit;
    }
    
    public String getNamaUnit(){
        return nama_unit;
    }
    
    public void setIDJenis(String jenis){
        this.id_jenis = jenis;
    }
    
    public String getIDJenis(){
        return id_jenis;
    }
    
    public void setTahun(int tahun){
        this.tahun = tahun;
    }
    
    public int getTahun(){
        return tahun;
    }
    
    public void setNamaJenis(String namaJenis){
        this.namaJenis = namaJenis;
    }
    
    public String getNamaJenis(){
        return namaJenis;
    }
    
    public void setHarga(double harga){
        this.harga = harga;
    }
    
    public double getHarga(){
        return harga;
    }
}
