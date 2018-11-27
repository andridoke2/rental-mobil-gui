package com.doke.Model;

import com.doke.Frame.RentalMobilFrame;
import com.doke.Koneksi.koneksi;
import java.awt.Component;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author doke
 */
public class queue {
    
    public static int ukuran = 100, top = 0;
    public static int[] queue = new int[ukuran];
    
    public static boolean isEmpty(){
        return top <= 0;
    }
    
    static boolean isFull(){
        return top >= ukuran;
    }
    
    public static void push(int i){
        if(isFull()){
            //Component RentalMobilFrame = null;
            //JOptionPane.showMessageDialog(RentalMobilFrame, "Maaf antrian penuh", "Penuh", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("Maaf antrian penuh!");
        } else {
            queue[top++] = i;
        }
    }
    
    public static void pop(int x){
        if(cek2(x) == true){
            if(isEmpty()){
                //Component RentalMobilFrame = null;
                //JOptionPane.showMessageDialog(RentalMobilFrame, "Maaf antrian kosong!", "Kosong", JOptionPane.INFORMATION_MESSAGE);
                System.out.println("Maaf antrian kosong");
            } else {
                int i = 1;
                String sql = "SELECT c.NAMA_DPN FROM unit u INNER JOIN detail_penyewaan d ON u.ID_UNIT=d.ID_UNIT "
                        + "INNER JOIN penyewaan p ON p.NO_SEWA=d.NO_SEWA "
                        + "INNER JOIN customer c ON c.ID_CUSTOMER=p.ID_CUSTOMER "
                        + "WHERE u.ID_UNIT='"+ x +"'";
                ResultSet rs = koneksi.executeQuery(sql);
                try{
                    while(rs.next()){
                        //Component RentalMobilFrame = null;
                        //JOptionPane.showMessageDialog(RentalMobilFrame, "Terimakasih saudara "+rs.getString(1)+" atas kerjasamanya!");
                        System.out.println("Trimakasih saudara "+rs.getString(1)+" atas kerjasamanya!");
                    }
                } catch(SQLException ex){
                    Logger.getLogger(RentalMobilFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                sql = "SELECT NO_SEWA FROM detail_penyewaan WHERE ID_UNIT='"+ x +"'";
                rs = koneksi.executeQuery(sql);
                try{
                    while(rs.next()){
                        sql = "DELETE FROM detail_penyewaan WHERE ID_UNIT='"+ x +"'";
                        int status = koneksi.execute(sql);
                        sql = "DELETE FROM penyewaan WHERE NO_SEWA='"+ rs.getString(1)+"'";
                        int status1 = koneksi.execute(sql);
                        if((status & status1) == 1){
                            while(i < top){
                                queue[i - 1] = queue[i];
                                i++;
                            }
                            top--;
                        } else {
                            //Component RentalMobilFrame = null;
                            //JOptionPane.showMessageDialog(RentalMobilFrame, "Data gagal dihapus!", "Gagal", JOptionPane.INFORMATION_MESSAGE);
                            System.out.println("Data gagal dihapus!");
                        }
                        rs.getString(1);
                    }
                } catch(SQLException ex){
                    Logger.getLogger(RentalMobilFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public static boolean cek(int i){
        boolean hasil = false;
        int temp = 0;
        while(temp < top){
            if(i == queue[temp]){
                hasil = true;
                break;
            }
            temp++;
        }
        if(hasil){
            System.out.println("Unit sedang tidak tersedia!");
            //Component RentalMobilFrame = null;
            //JOptionPane.showMessageDialog(RentalMobilFrame, "Unit sedang tidak tersedia!", "Kosong", JOptionPane.INFORMATION_MESSAGE);
        }
        return hasil;
    }
    
    public static boolean cek2(int i){
        boolean hasil = false;
        int temp = 0;
        while(temp < top){
            if(i == queue[temp]){
                hasil = true;
                break;
            }
            temp++;
        }
        return hasil;
    }
}
