package com.doke.Main;

import com.doke.Frame.RentalMobilFrame;
import static com.doke.Frame.RentalMobilFrame.MODE_BACKGROUND;
import java.nio.file.Paths;

/**
 *
 * @author doke
 */
public class RentalMobil {
    
    static RentalMobil instance = null;

    public static final String DIR_APLIKASI = Paths.get("").toAbsolutePath().toString();
    public static final String DIR_FOTO = DIR_APLIKASI + "\\photos\\";
    public static final String DIR_FOTO_DUMMY = DIR_APLIKASI + "\\dummyphotos\\";
    public static final String FOTO_DEFAULT_URL = DIR_FOTO + "default.jpg";
    
    public static String username;
    public static String password;
    public static int level;
    
    public static RentalMobil getInstance(){
        if(instance == null){
            instance = new RentalMobil();
        }
        return instance;
    }
    
    public static void main(String[] args) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RentalMobil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RentalMobil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RentalMobil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RentalMobil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //SimanisApp.getInstance().getLoginController().displayLoginFrame();
                RentalMobilFrame mainFrame = RentalMobilFrame.getInstance();
                //menuLogin.aktifkanModeForm(MODE_AWAL);
                mainFrame.setVisible(true);
                mainFrame.setLocationRelativeTo(null);
                mainFrame.aktifkanModeMenu(MODE_BACKGROUND);
            }
        });
    }
}
