package com.doke.Model;

import java.text.SimpleDateFormat;

/**
 *
 * @author doke
 */
public class detailSewa {
    
    String idUnit;
    int noSewa;
    double harga;
    
    SimpleDateFormat inputDateFormat;
    SimpleDateFormat outputDateFormat;
    SimpleDateFormat dbDateFormat;
    
    public detailSewa(){
        inputDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        outputDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dbDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    }
    
    public detailSewa(String idUnit){
        this();
        this.idUnit = idUnit;
        this.noSewa = 0;
        this.harga = 0;
    }
    
    public detailSewa(String idUnit, int noSewa){
        this();
        this.idUnit = idUnit;
        this.noSewa = noSewa;
        this.harga = 0;
    }
    
    public detailSewa(String idUnit, int noSewa, double harga){
        this();
        this.idUnit = idUnit;
        this.noSewa = noSewa;
        this.harga = harga;
    }
    
    public void setIdUnit(String idUnit){
        this.idUnit = idUnit;
    }
    
    public String getIdUnit(){
        return idUnit;
    }
    
    public void setNoSewa(int noSewa){
        this.noSewa = noSewa;
    }
    
    public int getNoSewa(){
        return noSewa;
    }
    
    public void setHarga(double harga){
        this.harga = harga;
    }
    
    public double getHarga(){
        return harga;
    }
}
