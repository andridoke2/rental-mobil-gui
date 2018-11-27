package com.doke.Model;

import java.text.SimpleDateFormat;

/**
 *
 * @author doke
 */
public class sewa extends customer {
    
    String noSewa;
    String idUnit;
    String idCustomer;
    int lamaSewa;
    String jaminan;
    
    SimpleDateFormat inputDateFormatS;
    SimpleDateFormat outputDateFormatS;
    SimpleDateFormat dbDateFormatS;
    
    public sewa(){
        inputDateFormatS = new SimpleDateFormat("dd-MM-yyyy");
        outputDateFormatS = new SimpleDateFormat("dd-MM-yyyy");
        dbDateFormatS = new SimpleDateFormat("yyyy-MM-dd");
    }
    
    public sewa(String noSewa){
        this();
        this.noSewa = noSewa;
        this.idUnit = "Kosong";
        this.idCustomer = "Kosong";
        this.lamaSewa = 0;
        this.jaminan = "Kosong";
    }
    
    public sewa(String noSewa, String idUnit){
        this();
        this.noSewa = noSewa;
        this.idUnit = idUnit;
        this.idCustomer = "Kosong";
        this.lamaSewa = 0;
        this.jaminan = "Kosong";
    }
    
    public sewa(String noSewa, String idUnit, String idCustomer){
        this();
        this.noSewa = noSewa;
        this.idUnit = idUnit;
        this.idCustomer = idCustomer;
        this.lamaSewa = 0;
        this.jaminan = "Kosong";
    }
    
    public sewa(String noSewa, String idUnit, String idCustomer, int lamaSewa, String jaminan){
        this();
        this.noSewa = noSewa;
        this.idUnit = idUnit;
        this.idCustomer = idCustomer;
        this.lamaSewa = lamaSewa;
        this.jaminan = jaminan;
    }
    
    public void setNoSewa(String noSewa){
        this.noSewa = noSewa;
    }
    
    public String getNoSewa(){
        return noSewa;
    }
    
    public void setIdUnit(String idUnit){
        this.idUnit = idUnit;
    }
    
    public String getIdUnit(){
        return idUnit;
    }
    
    public void setIdCustomer(String idCustomer){
        this.idCustomer = idCustomer;
    }
    
    public String getIdCustomer(){
        return idCustomer;
    }
    
    public void setLamaSewa(int lamaSewa){
        this.lamaSewa = lamaSewa;
    }
    
    public int getLamaSewa(){
        return lamaSewa;
    }
    
    public void setJaminan(String jaminan){
        this.jaminan = jaminan;
    }
    
    public String getJaminan(){
        return jaminan;
    }
}
