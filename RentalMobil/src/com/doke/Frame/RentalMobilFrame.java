package com.doke.Frame;

import com.doke.Koneksi.koneksi;
import com.doke.Model.customer;
import com.doke.Model.customerDAO;
import com.doke.Model.customerDataTable;
import com.doke.Model.detailSewa;
import com.doke.Model.detailSewaDAO;
import com.doke.Model.queue;
import com.doke.Model.sewa;
import com.doke.Model.sewaDAO;
import com.doke.Model.sewaDataTable;
import com.doke.Model.unit;
import com.doke.Model.unitDAO;
import com.doke.Model.unitDataTable;
import java.awt.CardLayout;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author doke
 */
public class RentalMobilFrame extends javax.swing.JFrame {

    static RentalMobilFrame instance = null;
    
    int modeForm;
    
    List<customer> dataCustomer;
    List<unit> dataUnit;
    List<sewa> dataSewa;
    
    customerDAO CustomerDAO;
    unitDAO UnitDAO;
    sewaDAO SewaDAO;
    detailSewaDAO DetailSewaDAO;
    
    static queue Queue = new queue();
    
    public static int harga = 250000;
    double total;
    
    public static final int MODE_BACKGROUND = 1;
    public static final int MODE_MENU = 2;
    
    public static final int MODE_CUSTOMER = 1;
    public static final int MODE_UNIT = 2;
    public static final int MODE_SEWA = 3;
    
    public static final int MODE_TABLE = 1;
    public static final int MODE_TAMPIL = 2;
    public static final int MODE_TAMBAH = 3;
    
    public static RentalMobilFrame getInstance(){
        if(instance == null){
            instance = new RentalMobilFrame();
        }
        return instance;
    }
    
    public RentalMobilFrame() {
        initComponents();
        this.modeForm = 0;
        try{
            CustomerDAO = customerDAO.getInstance();
            UnitDAO = unitDAO.getInstance();
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(this, "SQL ERROR "+ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
        tampilkanListCustomer(null);
        tampilkanListUnit(null);
        tampilkanListSewa(null);
        labelAwalMenu.setText("");
        labelTittleTambahCustomer.setText("Tambah data customer");
        labelTittleDetailCustomer.setText("Lihat detail customer");
        labelTittleTampilUnit.setText("Lihat detail unit");
        labelTittleTambahUnit.setText("Tambah data unit");
        labelTittleTambahSewa.setText("Tambah data sewa");
        labelTittleTampilSewa.setText("Lihat detail sewa");
        this.resetColor(panelMenuCustomer);
        this.resetColor(panelMenuUnit);
        this.resetColor(panelMenuSewa);
        this.ResetColor(panelBatalUnit);
        this.ResetColor(panelSimpanUnit);
        this.ResetColor(panelUbahDataUnit);
        this.ResetColor(panelTittleTambahUnit);
        this.ResetColor(panelTittleTampilUnit);
        this.resetColor(panelMenuDataUnit);
        this.resetColor(panelMenuTambahUnit);
        this.resetColor(panelMenuDetailUnit);
        this.ResetColor(panelTittleTambahCustomer);
        this.ResetColor(panelTittleDetailCustomer);
        this.ResetColor(panelTittleDetailCustomer);
        this.ResetColor(panelTittleTambahCustomer);
        this.ResetColor(panelBatalSimpanCustomer);
        this.ResetColor(panelSimpanCustomer);
        this.ResetColor(panelUbahCustomer);
        this.resetColor(panelBatalCustomer);
        this.resetColor(panelBaruCustomer);
        this.resetColor(panelDetailCustomer);
        this.ResetColor(panelBackground);
        this.resetColor(panelDetailSewa);
        this.resetColor(panelBaruSewa);
        this.resetColor(panelDataSewa);
        this.ResetColor(panelTittleSewa);
        this.ResetColor(panelBatalSewa);
        this.ResetColor(panelSimpanSewa);
        this.ResetColor(panelTittleTampilSewa);
    }
    
    public void SetColor(JButton b){
        b.setBackground(Color.green);
    }
    
    public void RSetColor(JButton b){
        b.setBackground(Color.WHITE);
    }
    
    public void setColor(JPanel p){
        p.setBackground(Color.green);
    }
    public void resetColor(JPanel p){
        p.setBackground(Color.WHITE);
    }
    public void ResetColor(JPanel p){
        p.setBackground(Color.gray);
    }
    
    public void aktifkanModeFormSewa(int modeForm){
        this.modeForm = modeForm;
        aturUlangFormSewa();
        this.setVisible(true);
    }
    
    private void aturUlangFormSewa(){
        CardLayout fl = (CardLayout) panelFormSewa.getLayout();
        switch(this.modeForm){
            case MODE_TABLE:
                fl.show(panelFormSewa, "cardTableSewa");
                break;
            case MODE_TAMPIL:
                fl.show(panelFormSewa, "cardTampilSewa");
                break;
            case MODE_TAMBAH:
                fl.show(panelFormSewa, "cardTambahSewa");
                labelNoSewaTambah.setText("");
                break;
        }
    }
    
    public void aktifkanModeFormUnit(int modeForm){
        this.modeForm = modeForm;
        aturUlangFormUnit();
        this.setVisible(true);
    }
    
    private void aturUlangFormUnit(){
        CardLayout fl = (CardLayout) panelFormUnit.getLayout();
        switch(this.modeForm){
            case MODE_TAMBAH:
                fl.show(panelFormUnit, "cardTambahUnit");
                labelIDUnitTambah.setText("");
                break;
            case MODE_TAMPIL:
                fl.show(panelFormUnit, "cardTampilUnit");
                break;
            case MODE_TABLE:
                fl.show(panelFormUnit, "cardTableUnit");
                break;
        }
    }
    
    public void aktifkanModeMenu(int modeForm){
        this.modeForm = modeForm;
        aturUlangMenu();
        this.setVisible(true);
    }
    private void aturUlangMenu(){
        CardLayout fl = (CardLayout) panelMenuBackground.getLayout();
        switch(this.modeForm){
            case MODE_BACKGROUND:
                fl.show(panelMenuBackground, "cardBackground");
                break;
            case MODE_MENU:
                fl.show(panelMenuBackground, "cardMenu");
                break;
        }
    }
    
    public void aktifkanModeFormMenu(int modeForm){
        this.modeForm = modeForm;
        aturUlangFormMenu();
        this.setVisible(true);
    }
    private void aturUlangFormMenu(){
        CardLayout fl = (CardLayout) panelFormMenu.getLayout();
        switch(this.modeForm){
            case MODE_CUSTOMER:
                fl.show(panelFormMenu, "cardCustomer");
                this.aktifkanFormCustomer(MODE_TABLE);
                break;
            case MODE_UNIT:
                fl.show(panelFormMenu, "cardUnit");
                this.aktifkanModeFormUnit(MODE_TABLE);
                break;
            case MODE_SEWA:
                fl.show(panelFormMenu, "cardSewa");
                this.aktifkanModeFormSewa(MODE_TABLE);
                break;
        }
    }
    
    public void aktifkanFormCustomer(int modeForm){
        this.modeForm = modeForm;
        aturUlangCustomer();
        this.setVisible(true);
    }
    private void aturUlangCustomer(){
        CardLayout fl = (CardLayout) panelFormCustomer.getLayout();
        switch(this.modeForm){
            case MODE_TABLE:
                fl.show(panelFormCustomer, "cardTableCustomer");
                buttonCariCustomer.setEnabled(true);
                buttonHapusCustomer.setEnabled(true);
                buttonRefreshCustomer.setEnabled(true);
                break;
            case MODE_TAMPIL:
                buttonCariCustomer.setEnabled(true);
                buttonHapusCustomer.setEnabled(false);
                buttonRefreshCustomer.setEnabled(false);
                fl.show(panelFormCustomer, "cardTampilCustomer");
                break;
            case MODE_TAMBAH:
                buttonCariCustomer.setEnabled(false);
                buttonHapusCustomer.setEnabled(false);
                buttonRefreshCustomer.setEnabled(false);
                labelIDCustomerTambah.setText("");
                fl.show(panelFormCustomer, "cardTambahCustomer");
                break;
        }
    }
    
    public void tampilkanCustomer(String kodeTampil){
        customer Customer = new customer();
        try{
            Customer = CustomerDAO.findCustomer(kodeTampil);
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(this, "SQL ERROR "+ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
        labelIDCustomer.setText(Customer.getId_customer());
        txtIDCustomer.setText(Customer.getId_customer());
        labelNamaDepanCustomer.setText(Customer.getNama_depan());
        txtNamaDepan.setText(Customer.getNama_depan());
        labelNamaBelakangCustomer.setText(Customer.getNama_belakang());
        txtNamaBelakang.setText(Customer.getNama_belakang());
        String jenisKelamin = String.valueOf(Customer.getJk());
        if(jenisKelamin.equals("L")){
            labelJenisKelaminCustomer.setText("Laki-laki");
            rdLaki.setSelected(true);
        } else if(jenisKelamin.equals("P")){
            labelJenisKelaminCustomer.setText("Perempuan");
            rdPerempuan.setSelected(true);
        }
        labelAlamatCustomer.setText(Customer.getAlamat());
        txtAlamat.setText(Customer.getAlamat());
        labelKontakCustomer.setText(Customer.getKontak());
        txtKontak.setText(Customer.getKontak());
        this.aktifkanFormCustomer(MODE_TAMPIL);
    }
    
    public void tampilkanUnit(String kodeTampil){
        unit Unit = new unit();
        try{
            Unit = UnitDAO.findUnit(kodeTampil);
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(this, "SQL ERROR "+ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
        labelIDUnit.setText(Unit.getIDUnit());
        txtIDUnit.setText(Unit.getIDUnit());
        labelIDJenisUnit.setText(Unit.getIDJenis());
        txtIDJenisUnit.setText(Unit.getIDJenis());
        labelNamaUnit.setText(Unit.getNamaUnit());
        txtNamaUnit.setText(Unit.getNamaUnit());
        labelTahunUnit.setText(String.valueOf(Unit.getTahun()));
        txtTahunUnit.setText(String.valueOf(Unit.getTahun()));
        this.aktifkanModeFormUnit(MODE_TAMPIL);
    }
    
    public void tampilkanSewa(String kodeTampil){
        sewa Sewa = new sewa();
        try{
            Sewa = SewaDAO.findSewa(kodeTampil);
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(this, "SQL ERROR "+ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
        labelNoSewa.setText(String.valueOf(Sewa.getNoSewa()));
    }
    
    public boolean validateField(){
        boolean valid = true;
        String pesanError = "Silahkan periksa kembali kesalahan berikut!";
        if(txtNamaDepan.getText().equals("")){
            pesanError += "\nNama depan harus diisi.";
            valid = false;
        }
        if(txtNamaBelakang.getText().equals("")){
            pesanError += "\nNama belakang harus diisi.";
            valid = false;
        }
        if(txtAlamat.getText().equals("")){
            pesanError += "\nAlamat harus diisi.";
            valid = false;
        }
        if(txtKontak.getText().equals("")){
            pesanError += "\nKontak harus diisi.";
            valid = false;
        }
        if(!valid){
            JOptionPane.showMessageDialog(this, pesanError, "Error Form", JOptionPane.WARNING_MESSAGE);
        }
        return valid;
    }
    
    public boolean validateFieldUnit(){
        boolean valid = true;
        String pesanError = "Silahkan periksa kembali kesalahan berikut!";
        if(txtIDJenisUnit.getText().equals("")){
            pesanError += "\nID Jenis harus diisi.";
            valid = false;
        }
        if(txtNamaUnit.getText().equals("")){
            pesanError += "\nNama Unit harus diisi.";
            valid = false;
        }
        if(txtTahunUnit.getText().equals("")){
            pesanError += "\nTahun Unit harus diisi.";
            valid = false;
        }
        if(!valid){
            JOptionPane.showMessageDialog(this, pesanError, "Error Form", JOptionPane.WARNING_MESSAGE);
        }
        return valid;
    }
    
    private void clearFieldTambahCustomer(){
        txtNamaDepan.setText("");
        txtNamaBelakang.setText("");
        txtAlamat.setText("");
        txtKontak.setText("");
        rdLaki.setSelected(false);
        rdPerempuan.setSelected(false);
    }
    
    private void clearFieldTambahUnit(){
        txtIDJenisUnit.setText("");
        txtNamaUnit.setText("");
        txtTahunUnit.setText("");
    }
    
    private void deleteCustomer(String idCustomer){
        customer Customer = new customer();
        try{
            Customer = CustomerDAO.findCustomer(idCustomer);
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(this, "SQL ERROR "+ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
        int hapus = JOptionPane.showConfirmDialog(this, "Apakah anda yakin ingin hapus data tersebut?", "Hapus Customer", JOptionPane.YES_NO_OPTION);
        if(hapus == JOptionPane.YES_OPTION){
            if(Customer != null){
                try{
                    CustomerDAO.deleteCustomer(idCustomer);
                } catch(SQLException ex){
                    JOptionPane.showMessageDialog(this, "SQL ERROR "+ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                    String berhasil = "Data gagal dihapus.";
                    JOptionPane.showMessageDialog(this, berhasil, "Gagal", JOptionPane.ERROR_MESSAGE);
                }
                String berhasil = "Data berhasil dihapus.";
                JOptionPane.showMessageDialog(this, berhasil, "Terhapus", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        if(Customer == null){
            JOptionPane.showMessageDialog(this, "Data tidak ditemukan!", "Tidak ada yang dihapus", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private void deleteCustomerKlikTable(){
        int hapus = JOptionPane.showConfirmDialog(this, "Apakah anda yakin ingin hapus data tersebut?", "Hapus Customer", JOptionPane.YES_NO_OPTION);
        if(hapus == JOptionPane.YES_OPTION){
            int baris = tableCustomer.getSelectedRow();
            if(baris != -1){
                String idCustomer = tableCustomer.getValueAt(baris, 0).toString();
                String sql = "DELETE FROM customer WHERE ID_CUSTOMER='"+idCustomer+"'";
                int status = 0;
                status = koneksi.execute(sql);
                if(status == 1){
                    JOptionPane.showMessageDialog(this, "Data berhasil dihapus.", "Terhapus", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Data gagal dihapus.", "Gagal", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    
    private void cariCustomer(String idCustomer){
        customer Customer = new customer();
        try{
            Customer = CustomerDAO.findCustomer(idCustomer);
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(this, "SQL ERROR "+ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
        if(Customer == null){
            JOptionPane.showMessageDialog(this, "Data tidak ditemukan!", "Kosong", JOptionPane.WARNING_MESSAGE);
        }
        if(Customer != null){
            if(this.modeForm == MODE_TABLE){
                tampilkanListCustomer(txtCariCustomer.getText());
            } else if(this.modeForm == MODE_TAMPIL){
                tampilkanListCustomer(txtCariCustomer.getText());
                tampilkanCustomer(txtCariCustomer.getText());
            }
        }
    }
    
    private boolean validateSewa(){
        boolean valid = true;
        String pesan = "Silahkan periksa kembali kesalahan berikut :";
        if(txtIDCustomerSewa.getText().equals("")){
            pesan += "\nID Customer harus diisi.";
            valid = false;
        }
        if(txtIDUnitSewa.getText().equals("")){
            pesan += "\nID Unit harus diisi.";
            valid = false;
        }
        if(txtLamaHariSewa.getText().equals("")){
            pesan += "\nLasa sewa harus diisi.";
            valid = false;
        }
        if(txtJaminanSewa.getText().equals("")){
            pesan += "\nJaminan harus diisi.";
            valid = false;
        }
        if(!valid){
            JOptionPane.showMessageDialog(this, pesan, "Lengkapi Form", JOptionPane.WARNING_MESSAGE);
        }
        return valid;
    }
    
    public void cetakNota(String noSewa){
        sewa Sewa = new sewa();
        try{
            Sewa = SewaDAO.cetak(noSewa);
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(this, "SQL ERROR "+ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
        cetakDetailSewa.append("\tNota\n"
                + "No Sewa \t:  "+Sewa.getNoSewa()+"\n"
                        + "Nama Penyewan \t:  "+Sewa.getNama_depan()+" "+Sewa.getNama_belakang()+"\n");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        panelDasar = new javax.swing.JPanel();
        panelMenuBackground = new javax.swing.JPanel();
        panelBackground = new javax.swing.JPanel();
        labelBackground = new javax.swing.JLabel();
        panelMenu = new javax.swing.JPanel();
        panelMenuCustomer = new javax.swing.JPanel();
        menuCustomer = new javax.swing.JLabel();
        panelMenuUnit = new javax.swing.JPanel();
        menuUnit = new javax.swing.JLabel();
        panelMenuSewa = new javax.swing.JPanel();
        menuSewa = new javax.swing.JLabel();
        panelFormMenu = new javax.swing.JPanel();
        panelMenuAwal = new javax.swing.JPanel();
        labelAwalMenu = new javax.swing.JLabel();
        panelCustomer = new javax.swing.JPanel();
        labelCariCustomer = new javax.swing.JLabel();
        buttonKeluarCustomer = new javax.swing.JButton();
        txtCariCustomer = new javax.swing.JTextField();
        buttonCariCustomer = new javax.swing.JButton();
        buttonRefreshCustomer = new javax.swing.JButton();
        buttonHapusCustomer = new javax.swing.JButton();
        panelFormCustomer = new javax.swing.JPanel();
        panelTambahCustomer = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        rdLaki = new javax.swing.JRadioButton();
        rdPerempuan = new javax.swing.JRadioButton();
        txtNamaDepan = new javax.swing.JTextField();
        txtNamaBelakang = new javax.swing.JTextField();
        txtAlamat = new javax.swing.JTextField();
        txtKontak = new javax.swing.JTextField();
        panelSimpanCustomer = new javax.swing.JPanel();
        labelSimpanCustomer = new javax.swing.JLabel();
        panelBatalSimpanCustomer = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        panelTittleTambahCustomer = new javax.swing.JPanel();
        labelTittleTambahCustomer = new javax.swing.JLabel();
        labelIDCustomerTambah = new javax.swing.JLabel();
        txtIDCustomer = new javax.swing.JLabel();
        panelTampilCustomer = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        labelIDCustomer = new javax.swing.JLabel();
        labelNamaDepanCustomer = new javax.swing.JLabel();
        labelNamaBelakangCustomer = new javax.swing.JLabel();
        labelJenisKelaminCustomer = new javax.swing.JLabel();
        labelAlamatCustomer = new javax.swing.JLabel();
        labelKontakCustomer = new javax.swing.JLabel();
        panelUbahCustomer = new javax.swing.JPanel();
        labelUbahCustomer = new javax.swing.JLabel();
        panelTittleDetailCustomer = new javax.swing.JPanel();
        labelTittleDetailCustomer = new javax.swing.JLabel();
        panelTableCustomer = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableCustomer = new javax.swing.JTable();
        labelJumlahRecordCustomer = new javax.swing.JLabel();
        panelDetailCustomer = new javax.swing.JPanel();
        labelDetailCustomer = new javax.swing.JLabel();
        panelBaruCustomer = new javax.swing.JPanel();
        labelBaruCustomer = new javax.swing.JLabel();
        panelBatalCustomer = new javax.swing.JPanel();
        labelBatalCustomer = new javax.swing.JLabel();
        panelUnit = new javax.swing.JPanel();
        labelCariUnit = new javax.swing.JLabel();
        buttonKeluarUnit = new javax.swing.JButton();
        txtCariUnit = new javax.swing.JTextField();
        buttonCariUnit = new javax.swing.JButton();
        buttonRefreshUnit = new javax.swing.JButton();
        buttonHapusUnit = new javax.swing.JButton();
        panelMenuDetailUnit = new javax.swing.JPanel();
        labelDetailUnit = new javax.swing.JLabel();
        panelMenuTambahUnit = new javax.swing.JPanel();
        labelTambahUnit = new javax.swing.JLabel();
        panelMenuDataUnit = new javax.swing.JPanel();
        labelDataUnit = new javax.swing.JLabel();
        labelJumlahRecordUnit = new javax.swing.JLabel();
        panelFormUnit = new javax.swing.JPanel();
        panelTambahUnit = new javax.swing.JPanel();
        panelTittleTambahUnit = new javax.swing.JPanel();
        labelTittleTambahUnit = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        txtIDJenisUnit = new javax.swing.JTextField();
        txtNamaUnit = new javax.swing.JTextField();
        txtTahunUnit = new javax.swing.JTextField();
        panelSimpanUnit = new javax.swing.JPanel();
        labelSimpanUnit = new javax.swing.JLabel();
        panelBatalUnit = new javax.swing.JPanel();
        labelBatalUnit = new javax.swing.JLabel();
        labelIDUnitTambah = new javax.swing.JLabel();
        txtIDUnit = new javax.swing.JLabel();
        panelTampilUnit = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        panelTittleTampilUnit = new javax.swing.JPanel();
        labelTittleTampilUnit = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        labelIDUnit = new javax.swing.JLabel();
        labelIDJenisUnit = new javax.swing.JLabel();
        labelNamaUnit = new javax.swing.JLabel();
        labelTahunUnit = new javax.swing.JLabel();
        panelUbahDataUnit = new javax.swing.JPanel();
        labelUbahDataUnit = new javax.swing.JLabel();
        panelTableUnit = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableUnit = new javax.swing.JTable();
        panelSewa = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        buttonKeluarSewa = new javax.swing.JButton();
        txtCariSewa = new javax.swing.JTextField();
        buttonCariSewa = new javax.swing.JButton();
        buttonRefreshSewa = new javax.swing.JButton();
        buttonHapusSewa = new javax.swing.JButton();
        labelJumlahRecordSewa = new javax.swing.JLabel();
        panelDetailSewa = new javax.swing.JPanel();
        labelDetailSewa = new javax.swing.JLabel();
        panelBaruSewa = new javax.swing.JPanel();
        labelBaruSewa = new javax.swing.JLabel();
        panelDataSewa = new javax.swing.JPanel();
        labelDataSewa = new javax.swing.JLabel();
        panelFormSewa = new javax.swing.JPanel();
        panelTableSewa = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableSewa = new javax.swing.JTable();
        panelTambahSewa = new javax.swing.JPanel();
        panelTittleSewa = new javax.swing.JPanel();
        labelTittleTambahSewa = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        txtIDCustomerSewa = new javax.swing.JTextField();
        txtIDUnitSewa = new javax.swing.JTextField();
        txtLamaHariSewa = new javax.swing.JTextField();
        txtJaminanSewa = new javax.swing.JTextField();
        panelSimpanSewa = new javax.swing.JPanel();
        labelSimpanSewa = new javax.swing.JLabel();
        panelBatalSewa = new javax.swing.JPanel();
        labelBatalSewa = new javax.swing.JLabel();
        labelNoSewaTambah = new javax.swing.JLabel();
        txtNoSewa = new javax.swing.JLabel();
        panelTampilSewa = new javax.swing.JPanel();
        panelTittleTampilSewa = new javax.swing.JPanel();
        labelTittleTampilSewa = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        cetakDetailSewa = new javax.swing.JTextArea();
        jLabel18 = new javax.swing.JLabel();
        labelIDUnitSewa = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        labelNoSewa = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        labelHargaSewa = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        labelCetakSewa = new javax.swing.JLabel();
        panelUbahSewa = new javax.swing.JPanel();
        labelUbahSewa = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        file = new javax.swing.JMenu();
        menu = new javax.swing.JMenu();
        menuRental = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelMenuBackground.setLayout(new java.awt.CardLayout());

        labelBackground.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelBackgroundMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelBackgroundMouseEntered(evt);
            }
        });

        javax.swing.GroupLayout panelBackgroundLayout = new javax.swing.GroupLayout(panelBackground);
        panelBackground.setLayout(panelBackgroundLayout);
        panelBackgroundLayout.setHorizontalGroup(
            panelBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelBackground, javax.swing.GroupLayout.DEFAULT_SIZE, 815, Short.MAX_VALUE)
        );
        panelBackgroundLayout.setVerticalGroup(
            panelBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelBackground, javax.swing.GroupLayout.DEFAULT_SIZE, 528, Short.MAX_VALUE)
        );

        panelMenuBackground.add(panelBackground, "cardBackground");

        panelMenuCustomer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelMenuCustomerMouseClicked(evt);
            }
        });

        menuCustomer.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        menuCustomer.setText("Customer");
        menuCustomer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuCustomerMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                menuCustomerMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                menuCustomerMouseEntered(evt);
            }
        });
        panelMenuCustomer.add(menuCustomer);

        panelMenuUnit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelMenuUnitMouseClicked(evt);
            }
        });

        menuUnit.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        menuUnit.setText("Unit");
        menuUnit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuUnitMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                menuUnitMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                menuUnitMouseEntered(evt);
            }
        });
        panelMenuUnit.add(menuUnit);

        panelMenuSewa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelMenuSewaMouseClicked(evt);
            }
        });

        menuSewa.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        menuSewa.setText("Sewa");
        menuSewa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuSewaMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                menuSewaMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                menuSewaMouseEntered(evt);
            }
        });
        panelMenuSewa.add(menuSewa);

        panelFormMenu.setLayout(new java.awt.CardLayout());

        javax.swing.GroupLayout panelMenuAwalLayout = new javax.swing.GroupLayout(panelMenuAwal);
        panelMenuAwal.setLayout(panelMenuAwalLayout);
        panelMenuAwalLayout.setHorizontalGroup(
            panelMenuAwalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelAwalMenu, javax.swing.GroupLayout.DEFAULT_SIZE, 689, Short.MAX_VALUE)
        );
        panelMenuAwalLayout.setVerticalGroup(
            panelMenuAwalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelAwalMenu, javax.swing.GroupLayout.DEFAULT_SIZE, 528, Short.MAX_VALUE)
        );

        panelFormMenu.add(panelMenuAwal, "cardMenuAwal");

        labelCariCustomer.setText("Cari ID/Nama");

        buttonKeluarCustomer.setText("Keluar");
        buttonKeluarCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonKeluarCustomerActionPerformed(evt);
            }
        });

        buttonCariCustomer.setText("Cari");
        buttonCariCustomer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                buttonCariCustomerMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buttonCariCustomerMouseEntered(evt);
            }
        });
        buttonCariCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCariCustomerActionPerformed(evt);
            }
        });

        buttonRefreshCustomer.setText("Refresh");
        buttonRefreshCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRefreshCustomerActionPerformed(evt);
            }
        });

        buttonHapusCustomer.setText("Hapus");
        buttonHapusCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonHapusCustomerActionPerformed(evt);
            }
        });

        panelFormCustomer.setLayout(new java.awt.CardLayout());

        panelTambahCustomer.setBackground(new java.awt.Color(254, 254, 254));

        jLabel5.setText("Nama Depan");

        jLabel6.setText("Nama Belakang");

        jLabel7.setText("Jenis Kelamin");

        jLabel8.setText("Alamat");

        jLabel9.setText("Kontak");

        buttonGroup1.add(rdLaki);
        rdLaki.setText("Laki-laki");

        buttonGroup1.add(rdPerempuan);
        rdPerempuan.setText("Perempuan");

        labelSimpanCustomer.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        labelSimpanCustomer.setText("Simpan");
        labelSimpanCustomer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelSimpanCustomerMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelSimpanCustomerMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelSimpanCustomerMouseEntered(evt);
            }
        });
        panelSimpanCustomer.add(labelSimpanCustomer);

        jLabel16.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel16.setText("Batal");
        jLabel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel16MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel16MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel16MouseEntered(evt);
            }
        });
        panelBatalSimpanCustomer.add(jLabel16);

        panelTittleTambahCustomer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelTittleTambahCustomerMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelTittleTambahCustomerMouseEntered(evt);
            }
        });

        labelTittleTambahCustomer.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        labelTittleTambahCustomer.setText("Tambah data customer");
        labelTittleTambahCustomer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelTittleTambahCustomerMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelTittleTambahCustomerMouseEntered(evt);
            }
        });
        panelTittleTambahCustomer.add(labelTittleTambahCustomer);

        labelIDCustomerTambah.setText("ID Customer");

        txtIDCustomer.setText("ID Customer");

        javax.swing.GroupLayout panelTambahCustomerLayout = new javax.swing.GroupLayout(panelTambahCustomer);
        panelTambahCustomer.setLayout(panelTambahCustomerLayout);
        panelTambahCustomerLayout.setHorizontalGroup(
            panelTambahCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelTittleTambahCustomer, javax.swing.GroupLayout.DEFAULT_SIZE, 665, Short.MAX_VALUE)
            .addGroup(panelTambahCustomerLayout.createSequentialGroup()
                .addGap(121, 121, 121)
                .addGroup(panelTambahCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(labelIDCustomerTambah))
                .addGap(50, 50, 50)
                .addGroup(panelTambahCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTambahCustomerLayout.createSequentialGroup()
                        .addComponent(rdLaki)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rdPerempuan))
                    .addGroup(panelTambahCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(panelTambahCustomerLayout.createSequentialGroup()
                            .addComponent(panelSimpanCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelBatalSimpanCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(txtKontak, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtAlamat, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelTambahCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtIDCustomer, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtNamaBelakang, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtNamaDepan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelTambahCustomerLayout.setVerticalGroup(
            panelTambahCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTambahCustomerLayout.createSequentialGroup()
                .addComponent(panelTittleTambahCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(panelTambahCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelIDCustomerTambah)
                    .addComponent(txtIDCustomer))
                .addGap(18, 18, 18)
                .addGroup(panelTambahCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtNamaDepan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelTambahCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtNamaBelakang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelTambahCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(rdLaki)
                    .addComponent(rdPerempuan))
                .addGap(18, 18, 18)
                .addGroup(panelTambahCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelTambahCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtKontak, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelTambahCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelSimpanCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelBatalSimpanCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(94, Short.MAX_VALUE))
        );

        panelFormCustomer.add(panelTambahCustomer, "cardTambahCustomer");

        panelTampilCustomer.setBackground(new java.awt.Color(254, 254, 254));

        jLabel10.setText("ID Customer");

        jLabel11.setText("Nama Depan");

        jLabel12.setText("Nama Belakang");

        jLabel13.setText("Jenis Kelamin");

        jLabel14.setText("Alamat");

        jLabel15.setText("Kontak");

        labelIDCustomer.setText("ID Customer");

        labelNamaDepanCustomer.setText("Nama Depan");

        labelNamaBelakangCustomer.setText("Nama Belakang");

        labelJenisKelaminCustomer.setText("Jenis Kelamin");

        labelAlamatCustomer.setText("Alamat");

        labelKontakCustomer.setText("Kontak");

        labelUbahCustomer.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        labelUbahCustomer.setText("Ubah data");
        labelUbahCustomer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelUbahCustomerMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelUbahCustomerMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelUbahCustomerMouseEntered(evt);
            }
        });
        panelUbahCustomer.add(labelUbahCustomer);

        panelTittleDetailCustomer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelTittleDetailCustomerMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelTittleDetailCustomerMouseEntered(evt);
            }
        });

        labelTittleDetailCustomer.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        labelTittleDetailCustomer.setText("Lihat detail data customer");
        labelTittleDetailCustomer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelTittleDetailCustomerMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelTittleDetailCustomerMouseEntered(evt);
            }
        });
        panelTittleDetailCustomer.add(labelTittleDetailCustomer);

        javax.swing.GroupLayout panelTampilCustomerLayout = new javax.swing.GroupLayout(panelTampilCustomer);
        panelTampilCustomer.setLayout(panelTampilCustomerLayout);
        panelTampilCustomerLayout.setHorizontalGroup(
            panelTampilCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTampilCustomerLayout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addGroup(panelTampilCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15))
                .addGap(50, 50, 50)
                .addGroup(panelTampilCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTampilCustomerLayout.createSequentialGroup()
                        .addComponent(panelUbahCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelTampilCustomerLayout.createSequentialGroup()
                        .addGroup(panelTampilCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(labelIDCustomer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelNamaDepanCustomer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelNamaBelakangCustomer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelJenisKelaminCustomer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelAlamatCustomer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelKontakCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 149, Short.MAX_VALUE))))
            .addComponent(panelTittleDetailCustomer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelTampilCustomerLayout.setVerticalGroup(
            panelTampilCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTampilCustomerLayout.createSequentialGroup()
                .addComponent(panelTittleDetailCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addGroup(panelTampilCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(labelIDCustomer))
                .addGap(18, 18, 18)
                .addGroup(panelTampilCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(labelNamaDepanCustomer))
                .addGap(18, 18, 18)
                .addGroup(panelTampilCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(labelNamaBelakangCustomer))
                .addGap(18, 18, 18)
                .addGroup(panelTampilCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(labelJenisKelaminCustomer))
                .addGap(18, 18, 18)
                .addGroup(panelTampilCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(labelAlamatCustomer))
                .addGap(18, 18, 18)
                .addGroup(panelTampilCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(labelKontakCustomer))
                .addGap(18, 18, 18)
                .addComponent(panelUbahCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(146, Short.MAX_VALUE))
        );

        panelFormCustomer.add(panelTampilCustomer, "cardTampilCustomer");

        tableCustomer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tableCustomer);

        javax.swing.GroupLayout panelTableCustomerLayout = new javax.swing.GroupLayout(panelTableCustomer);
        panelTableCustomer.setLayout(panelTableCustomerLayout);
        panelTableCustomerLayout.setHorizontalGroup(
            panelTableCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 665, Short.MAX_VALUE)
        );
        panelTableCustomerLayout.setVerticalGroup(
            panelTableCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
        );

        panelFormCustomer.add(panelTableCustomer, "cardTableCustomer");

        labelJumlahRecordCustomer.setText("terdapat 999 record");

        panelDetailCustomer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelDetailCustomerMouseClicked(evt);
            }
        });

        labelDetailCustomer.setText("Detail customer");
        labelDetailCustomer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelDetailCustomerMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelDetailCustomerMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelDetailCustomerMouseEntered(evt);
            }
        });
        panelDetailCustomer.add(labelDetailCustomer);

        panelBaruCustomer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelBaruCustomerMouseClicked(evt);
            }
        });

        labelBaruCustomer.setText("Tambah data baru");
        labelBaruCustomer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelBaruCustomerMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelBaruCustomerMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelBaruCustomerMouseEntered(evt);
            }
        });
        panelBaruCustomer.add(labelBaruCustomer);

        panelBatalCustomer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelBatalCustomerMouseClicked(evt);
            }
        });

        labelBatalCustomer.setText("Data customer");
        labelBatalCustomer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelBatalCustomerMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelBatalCustomerMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelBatalCustomerMouseEntered(evt);
            }
        });
        panelBatalCustomer.add(labelBatalCustomer);

        javax.swing.GroupLayout panelCustomerLayout = new javax.swing.GroupLayout(panelCustomer);
        panelCustomer.setLayout(panelCustomerLayout);
        panelCustomerLayout.setHorizontalGroup(
            panelCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCustomerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelFormCustomer, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCustomerLayout.createSequentialGroup()
                        .addComponent(labelCariCustomer)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCariCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonCariCustomer)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonRefreshCustomer)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonHapusCustomer)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonKeluarCustomer))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCustomerLayout.createSequentialGroup()
                        .addComponent(panelDetailCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panelBaruCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panelBatalCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelJumlahRecordCustomer)))
                .addContainerGap())
        );
        panelCustomerLayout.setVerticalGroup(
            panelCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCustomerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCariCustomer)
                    .addComponent(buttonKeluarCustomer)
                    .addComponent(txtCariCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonCariCustomer)
                    .addComponent(buttonRefreshCustomer)
                    .addComponent(buttonHapusCustomer))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelFormCustomer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelJumlahRecordCustomer, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelDetailCustomer, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelBaruCustomer, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelBatalCustomer, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        panelFormMenu.add(panelCustomer, "cardCustomer");

        labelCariUnit.setText("Cari ID/Nama");

        buttonKeluarUnit.setText("Keluar");
        buttonKeluarUnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonKeluarUnitActionPerformed(evt);
            }
        });

        buttonCariUnit.setText("Cari");
        buttonCariUnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCariUnitActionPerformed(evt);
            }
        });

        buttonRefreshUnit.setText("Refresh");
        buttonRefreshUnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRefreshUnitActionPerformed(evt);
            }
        });

        buttonHapusUnit.setText("Hapus");

        labelDetailUnit.setText("Detail unit");
        labelDetailUnit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelDetailUnitMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelDetailUnitMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelDetailUnitMouseEntered(evt);
            }
        });
        panelMenuDetailUnit.add(labelDetailUnit);

        labelTambahUnit.setText("Tambah data baru");
        labelTambahUnit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelTambahUnitMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelTambahUnitMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelTambahUnitMouseEntered(evt);
            }
        });
        panelMenuTambahUnit.add(labelTambahUnit);

        labelDataUnit.setText("Data unit");
        labelDataUnit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelDataUnitMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelDataUnitMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelDataUnitMouseEntered(evt);
            }
        });
        panelMenuDataUnit.add(labelDataUnit);

        labelJumlahRecordUnit.setText("terdapat 999 record");

        panelFormUnit.setLayout(new java.awt.CardLayout());

        panelTambahUnit.setBackground(new java.awt.Color(254, 254, 254));

        labelTittleTambahUnit.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        labelTittleTambahUnit.setText("Tambah data unit");
        labelTittleTambahUnit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelTittleTambahUnitMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelTittleTambahUnitMouseEntered(evt);
            }
        });
        panelTittleTambahUnit.add(labelTittleTambahUnit);

        jLabel22.setText("ID Jenis");

        jLabel23.setText("Nama Unit");

        jLabel24.setText("Tahun");

        labelSimpanUnit.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        labelSimpanUnit.setText("Simpan");
        labelSimpanUnit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelSimpanUnitMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelSimpanUnitMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelSimpanUnitMouseEntered(evt);
            }
        });
        panelSimpanUnit.add(labelSimpanUnit);

        labelBatalUnit.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        labelBatalUnit.setText("Batal");
        labelBatalUnit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelBatalUnitMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelBatalUnitMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelBatalUnitMouseEntered(evt);
            }
        });
        panelBatalUnit.add(labelBatalUnit);

        labelIDUnitTambah.setText("ID Unit");

        txtIDUnit.setText("ID Unit");

        javax.swing.GroupLayout panelTambahUnitLayout = new javax.swing.GroupLayout(panelTambahUnit);
        panelTambahUnit.setLayout(panelTambahUnitLayout);
        panelTambahUnitLayout.setHorizontalGroup(
            panelTambahUnitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelTittleTambahUnit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelTambahUnitLayout.createSequentialGroup()
                .addGap(140, 140, 140)
                .addGroup(panelTambahUnitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22)
                    .addComponent(jLabel23)
                    .addComponent(jLabel24)
                    .addComponent(labelIDUnitTambah))
                .addGap(62, 62, 62)
                .addGroup(panelTambahUnitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelTambahUnitLayout.createSequentialGroup()
                        .addComponent(panelSimpanUnit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                        .addComponent(panelBatalUnit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtIDJenisUnit)
                    .addComponent(txtNamaUnit)
                    .addComponent(txtTahunUnit)
                    .addComponent(txtIDUnit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(261, Short.MAX_VALUE))
        );
        panelTambahUnitLayout.setVerticalGroup(
            panelTambahUnitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTambahUnitLayout.createSequentialGroup()
                .addComponent(panelTittleTambahUnit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(panelTambahUnitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelIDUnitTambah)
                    .addComponent(txtIDUnit))
                .addGap(18, 18, 18)
                .addGroup(panelTambahUnitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(txtIDJenisUnit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelTambahUnitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(txtNamaUnit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelTambahUnitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(txtTahunUnit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelTambahUnitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelSimpanUnit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelBatalUnit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 175, Short.MAX_VALUE))
        );

        panelFormUnit.add(panelTambahUnit, "cardTambahUnit");

        panelTampilUnit.setBackground(new java.awt.Color(254, 254, 254));

        jLabel2.setText("ID Unit");

        labelTittleTampilUnit.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        labelTittleTampilUnit.setText("Lihat detail unit");
        labelTittleTampilUnit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelTittleTampilUnitMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelTittleTampilUnitMouseEntered(evt);
            }
        });
        panelTittleTampilUnit.add(labelTittleTampilUnit);

        jLabel1.setText("ID Jenis");

        jLabel4.setText("Nama Unit");

        jLabel17.setText("Tahun");

        labelIDUnit.setText("ID Unit");

        labelIDJenisUnit.setText("ID Jenis");

        labelNamaUnit.setText("Nama Unit");

        labelTahunUnit.setText("Tahun");

        labelUbahDataUnit.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        labelUbahDataUnit.setText("Ubah data");
        labelUbahDataUnit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelUbahDataUnitMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelUbahDataUnitMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelUbahDataUnitMouseEntered(evt);
            }
        });
        panelUbahDataUnit.add(labelUbahDataUnit);

        javax.swing.GroupLayout panelTampilUnitLayout = new javax.swing.GroupLayout(panelTampilUnit);
        panelTampilUnit.setLayout(panelTampilUnitLayout);
        panelTampilUnitLayout.setHorizontalGroup(
            panelTampilUnitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelTittleTampilUnit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelTampilUnitLayout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addGroup(panelTampilUnitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4)
                    .addComponent(jLabel17))
                .addGap(80, 80, 80)
                .addGroup(panelTampilUnitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelUbahDataUnit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelTahunUnit)
                    .addComponent(labelNamaUnit)
                    .addComponent(labelIDJenisUnit)
                    .addComponent(labelIDUnit))
                .addContainerGap(349, Short.MAX_VALUE))
        );
        panelTampilUnitLayout.setVerticalGroup(
            panelTampilUnitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTampilUnitLayout.createSequentialGroup()
                .addComponent(panelTittleTampilUnit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addGroup(panelTampilUnitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(labelIDUnit))
                .addGap(18, 18, 18)
                .addGroup(panelTampilUnitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(labelIDJenisUnit))
                .addGap(18, 18, 18)
                .addGroup(panelTampilUnitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(labelNamaUnit))
                .addGap(18, 18, 18)
                .addGroup(panelTampilUnitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(labelTahunUnit))
                .addGap(18, 18, 18)
                .addComponent(panelUbahDataUnit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(191, Short.MAX_VALUE))
        );

        panelFormUnit.add(panelTampilUnit, "cardTampilUnit");

        tableUnit.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tableUnit);

        javax.swing.GroupLayout panelTableUnitLayout = new javax.swing.GroupLayout(panelTableUnit);
        panelTableUnit.setLayout(panelTableUnitLayout);
        panelTableUnitLayout.setHorizontalGroup(
            panelTableUnitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 665, Short.MAX_VALUE)
        );
        panelTableUnitLayout.setVerticalGroup(
            panelTableUnitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
        );

        panelFormUnit.add(panelTableUnit, "cardTableUnit");

        javax.swing.GroupLayout panelUnitLayout = new javax.swing.GroupLayout(panelUnit);
        panelUnit.setLayout(panelUnitLayout);
        panelUnitLayout.setHorizontalGroup(
            panelUnitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUnitLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelUnitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelFormUnit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelUnitLayout.createSequentialGroup()
                        .addComponent(labelCariUnit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCariUnit, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonCariUnit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonRefreshUnit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonHapusUnit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonKeluarUnit))
                    .addGroup(panelUnitLayout.createSequentialGroup()
                        .addComponent(panelMenuDetailUnit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panelMenuTambahUnit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panelMenuDataUnit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelJumlahRecordUnit)))
                .addContainerGap())
        );
        panelUnitLayout.setVerticalGroup(
            panelUnitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUnitLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelUnitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCariUnit)
                    .addComponent(buttonKeluarUnit)
                    .addComponent(txtCariUnit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonCariUnit)
                    .addComponent(buttonRefreshUnit)
                    .addComponent(buttonHapusUnit))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelFormUnit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelUnitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelMenuDetailUnit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelMenuTambahUnit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelMenuDataUnit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelJumlahRecordUnit, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

        panelFormMenu.add(panelUnit, "cardUnit");

        jLabel3.setText("Cari ID Customer/ID Unit");

        buttonKeluarSewa.setText("Keluar");
        buttonKeluarSewa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonKeluarSewaActionPerformed(evt);
            }
        });

        buttonCariSewa.setText("Cari");

        buttonRefreshSewa.setText("Refresh");

        buttonHapusSewa.setText("Hapus");

        labelJumlahRecordSewa.setText("terdapat 999 record");

        labelDetailSewa.setText("Detail sewa");
        labelDetailSewa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelDetailSewaMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelDetailSewaMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelDetailSewaMouseEntered(evt);
            }
        });
        panelDetailSewa.add(labelDetailSewa);

        labelBaruSewa.setText("Sewa baru");
        labelBaruSewa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelBaruSewaMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelBaruSewaMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelBaruSewaMouseEntered(evt);
            }
        });
        panelBaruSewa.add(labelBaruSewa);

        labelDataSewa.setText("Data sewa");
        labelDataSewa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelDataSewaMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelDataSewaMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelDataSewaMouseEntered(evt);
            }
        });
        panelDataSewa.add(labelDataSewa);

        panelFormSewa.setLayout(new java.awt.CardLayout());

        tableSewa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(tableSewa);

        javax.swing.GroupLayout panelTableSewaLayout = new javax.swing.GroupLayout(panelTableSewa);
        panelTableSewa.setLayout(panelTableSewaLayout);
        panelTableSewaLayout.setHorizontalGroup(
            panelTableSewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 665, Short.MAX_VALUE)
        );
        panelTableSewaLayout.setVerticalGroup(
            panelTableSewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
        );

        panelFormSewa.add(panelTableSewa, "cardTableSewa");

        panelTambahSewa.setBackground(new java.awt.Color(254, 254, 254));

        labelTittleTambahSewa.setText("Tambah data sewa");
        labelTittleTambahSewa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelTittleTambahSewaMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelTittleTambahSewaMouseEntered(evt);
            }
        });
        panelTittleSewa.add(labelTittleTambahSewa);

        jLabel19.setText("ID Customer");

        jLabel20.setText("ID Unit");

        jLabel21.setText("Lama Sewa (Hari)");

        jLabel25.setText("Jaminan");

        labelSimpanSewa.setText("Sewa");
        labelSimpanSewa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelSimpanSewaMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelSimpanSewaMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelSimpanSewaMouseEntered(evt);
            }
        });
        panelSimpanSewa.add(labelSimpanSewa);

        labelBatalSewa.setText("Batal");
        labelBatalSewa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                labelBatalSewaMousePressed(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelBatalSewaMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelBatalSewaMouseEntered(evt);
            }
        });
        panelBatalSewa.add(labelBatalSewa);

        labelNoSewaTambah.setText("No Sewa");

        txtNoSewa.setText("No Sewa");

        javax.swing.GroupLayout panelTambahSewaLayout = new javax.swing.GroupLayout(panelTambahSewa);
        panelTambahSewa.setLayout(panelTambahSewaLayout);
        panelTambahSewaLayout.setHorizontalGroup(
            panelTambahSewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelTittleSewa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 665, Short.MAX_VALUE)
            .addGroup(panelTambahSewaLayout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(panelTambahSewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addComponent(jLabel20)
                    .addComponent(jLabel21)
                    .addComponent(jLabel25)
                    .addComponent(labelNoSewaTambah))
                .addGap(63, 63, 63)
                .addGroup(panelTambahSewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelTambahSewaLayout.createSequentialGroup()
                        .addComponent(panelSimpanSewa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelBatalSewa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtIDCustomerSewa)
                    .addComponent(txtIDUnitSewa)
                    .addComponent(txtLamaHariSewa)
                    .addComponent(txtJaminanSewa, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                    .addComponent(txtNoSewa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelTambahSewaLayout.setVerticalGroup(
            panelTambahSewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTambahSewaLayout.createSequentialGroup()
                .addComponent(panelTittleSewa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(panelTambahSewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNoSewaTambah)
                    .addComponent(txtNoSewa))
                .addGap(18, 18, 18)
                .addGroup(panelTambahSewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(txtIDCustomerSewa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelTambahSewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txtIDUnitSewa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelTambahSewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(txtLamaHariSewa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelTambahSewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(txtJaminanSewa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelTambahSewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelSimpanSewa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelBatalSewa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 129, Short.MAX_VALUE))
        );

        panelFormSewa.add(panelTambahSewa, "cardTambahSewa");

        panelTampilSewa.setBackground(new java.awt.Color(254, 254, 254));

        panelTittleTampilSewa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelTittleTampilSewaMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelTittleTampilSewaMouseEntered(evt);
            }
        });

        labelTittleTampilSewa.setText("Tampil detail sewa");
        labelTittleTampilSewa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelTittleTampilSewaMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelTittleTampilSewaMouseEntered(evt);
            }
        });
        panelTittleTampilSewa.add(labelTittleTampilSewa);

        cetakDetailSewa.setColumns(20);
        cetakDetailSewa.setRows(5);
        jScrollPane4.setViewportView(cetakDetailSewa);

        jLabel18.setText("ID Unit");

        labelIDUnitSewa.setText("ID Unit");

        jLabel27.setText("No Sewa");

        labelNoSewa.setText("No Sewa");

        jLabel29.setText("Harga");

        labelHargaSewa.setText("Harga");

        labelCetakSewa.setText("Cetak Nota");
        jPanel1.add(labelCetakSewa);

        labelUbahSewa.setText("Ubah");
        labelUbahSewa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelUbahSewaMouseClicked(evt);
            }
        });
        panelUbahSewa.add(labelUbahSewa);

        javax.swing.GroupLayout panelTampilSewaLayout = new javax.swing.GroupLayout(panelTampilSewa);
        panelTampilSewa.setLayout(panelTampilSewaLayout);
        panelTampilSewaLayout.setHorizontalGroup(
            panelTampilSewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelTittleTampilSewa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 665, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTampilSewaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTampilSewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTampilSewaLayout.createSequentialGroup()
                        .addGroup(panelTampilSewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(jLabel27)
                            .addComponent(jLabel29))
                        .addGap(42, 42, 42)
                        .addGroup(panelTampilSewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelHargaSewa)
                            .addComponent(labelNoSewa)
                            .addComponent(labelIDUnitSewa)))
                    .addGroup(panelTampilSewaLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(panelUbahSewa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(35, 35, 35)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelTampilSewaLayout.setVerticalGroup(
            panelTampilSewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTampilSewaLayout.createSequentialGroup()
                .addComponent(panelTittleTampilSewa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelTampilSewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelTampilSewaLayout.createSequentialGroup()
                        .addGroup(panelTampilSewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(labelIDUnitSewa))
                        .addGap(18, 18, 18)
                        .addGroup(panelTampilSewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(labelNoSewa))
                        .addGap(18, 18, 18)
                        .addGroup(panelTampilSewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel29)
                            .addComponent(labelHargaSewa))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelTampilSewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(panelUbahSewa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 29, Short.MAX_VALUE))
        );

        panelFormSewa.add(panelTampilSewa, "cardTampilSewa");

        javax.swing.GroupLayout panelSewaLayout = new javax.swing.GroupLayout(panelSewa);
        panelSewa.setLayout(panelSewaLayout);
        panelSewaLayout.setHorizontalGroup(
            panelSewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSewaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelFormSewa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelSewaLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCariSewa, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonCariSewa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonRefreshSewa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonHapusSewa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonKeluarSewa))
                    .addGroup(panelSewaLayout.createSequentialGroup()
                        .addComponent(panelDetailSewa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panelBaruSewa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panelDataSewa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelJumlahRecordSewa)))
                .addContainerGap())
        );
        panelSewaLayout.setVerticalGroup(
            panelSewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSewaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonKeluarSewa)
                    .addGroup(panelSewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(txtCariSewa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buttonCariSewa)
                        .addComponent(buttonRefreshSewa)
                        .addComponent(buttonHapusSewa)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelFormSewa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelSewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelJumlahRecordSewa, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelDetailSewa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelBaruSewa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelDataSewa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        panelFormMenu.add(panelSewa, "cardSewa");

        javax.swing.GroupLayout panelMenuLayout = new javax.swing.GroupLayout(panelMenu);
        panelMenu.setLayout(panelMenuLayout);
        panelMenuLayout.setHorizontalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(panelMenuUnit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelMenuCustomer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelMenuSewa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelFormMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelMenuLayout.setVerticalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFormMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelMenuLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(panelMenuCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelMenuUnit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(panelMenuSewa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelMenuBackground.add(panelMenu, "cardMenu");

        javax.swing.GroupLayout panelDasarLayout = new javax.swing.GroupLayout(panelDasar);
        panelDasar.setLayout(panelDasarLayout);
        panelDasarLayout.setHorizontalGroup(
            panelDasarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelMenuBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelDasarLayout.setVerticalGroup(
            panelDasarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelMenuBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        file.setText("File");
        menuBar.add(file);

        menu.setText("Menu");

        menuRental.setText("Menu");
        menuRental.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuRentalActionPerformed(evt);
            }
        });
        menu.add(menuRental);

        menuBar.add(menu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelDasar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelDasar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void labelBackgroundMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelBackgroundMouseEntered
        resetColor(panelBackground);
    }//GEN-LAST:event_labelBackgroundMouseEntered

    private void labelBackgroundMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelBackgroundMouseExited
        ResetColor(panelBackground);
    }//GEN-LAST:event_labelBackgroundMouseExited

    private void menuCustomerMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuCustomerMouseEntered
        setColor(panelMenuCustomer);
    }//GEN-LAST:event_menuCustomerMouseEntered

    private void menuCustomerMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuCustomerMouseExited
        resetColor(panelMenuCustomer);
    }//GEN-LAST:event_menuCustomerMouseExited

    private void menuUnitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuUnitMouseEntered
        setColor(panelMenuUnit);
    }//GEN-LAST:event_menuUnitMouseEntered

    private void menuUnitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuUnitMouseExited
        resetColor(panelMenuUnit);
    }//GEN-LAST:event_menuUnitMouseExited

    private void menuSewaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuSewaMouseClicked
        this.aktifkanModeFormMenu(MODE_SEWA);
    }//GEN-LAST:event_menuSewaMouseClicked

    private void menuSewaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuSewaMouseEntered
        setColor(panelMenuSewa);
    }//GEN-LAST:event_menuSewaMouseEntered

    private void menuSewaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuSewaMouseExited
        resetColor(panelMenuSewa);
    }//GEN-LAST:event_menuSewaMouseExited

    private void menuRentalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuRentalActionPerformed
        this.aktifkanModeMenu(MODE_MENU);
    }//GEN-LAST:event_menuRentalActionPerformed

    private void menuCustomerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuCustomerMouseClicked
        this.aktifkanModeFormMenu(MODE_CUSTOMER);
    }//GEN-LAST:event_menuCustomerMouseClicked

    private void menuUnitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuUnitMouseClicked
        this.aktifkanModeFormMenu(MODE_UNIT);
    }//GEN-LAST:event_menuUnitMouseClicked

    private void panelMenuCustomerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelMenuCustomerMouseClicked
        this.aktifkanModeFormMenu(MODE_CUSTOMER);
    }//GEN-LAST:event_panelMenuCustomerMouseClicked

    private void panelMenuUnitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelMenuUnitMouseClicked
        this.aktifkanModeFormMenu(MODE_UNIT);
    }//GEN-LAST:event_panelMenuUnitMouseClicked

    private void panelMenuSewaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelMenuSewaMouseClicked
        this.aktifkanModeFormMenu(MODE_SEWA);
    }//GEN-LAST:event_panelMenuSewaMouseClicked

    private void buttonKeluarSewaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonKeluarSewaActionPerformed
        this.aktifkanModeMenu(MODE_BACKGROUND);
    }//GEN-LAST:event_buttonKeluarSewaActionPerformed

    private void buttonKeluarUnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonKeluarUnitActionPerformed
        this.aktifkanModeMenu(MODE_BACKGROUND);
    }//GEN-LAST:event_buttonKeluarUnitActionPerformed

    private void buttonKeluarCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonKeluarCustomerActionPerformed
        this.aktifkanModeMenu(MODE_BACKGROUND);
    }//GEN-LAST:event_buttonKeluarCustomerActionPerformed

    private void labelDetailCustomerMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelDetailCustomerMouseEntered
        setColor(panelDetailCustomer);
    }//GEN-LAST:event_labelDetailCustomerMouseEntered

    private void labelBaruCustomerMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelBaruCustomerMouseEntered
        setColor(panelBaruCustomer);
    }//GEN-LAST:event_labelBaruCustomerMouseEntered

    private void labelDetailCustomerMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelDetailCustomerMouseExited
        resetColor(panelDetailCustomer);
    }//GEN-LAST:event_labelDetailCustomerMouseExited

    private void labelBaruCustomerMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelBaruCustomerMouseExited
        resetColor(panelBaruCustomer);
    }//GEN-LAST:event_labelBaruCustomerMouseExited

    private void labelDetailCustomerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelDetailCustomerMouseClicked
        this.aktifkanFormCustomer(MODE_TAMPIL);
        tampilkanCustomer(tableCustomer.getValueAt(tableCustomer.getSelectedRow(), 0).toString());
    }//GEN-LAST:event_labelDetailCustomerMouseClicked

    private void labelBaruCustomerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelBaruCustomerMouseClicked
        this.aktifkanFormCustomer(MODE_TAMBAH);
        txtIDCustomer.setText("");
        clearFieldTambahCustomer();
    }//GEN-LAST:event_labelBaruCustomerMouseClicked

    private void labelBatalCustomerMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelBatalCustomerMouseEntered
        setColor(panelBatalCustomer);
    }//GEN-LAST:event_labelBatalCustomerMouseEntered

    private void labelBatalCustomerMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelBatalCustomerMouseExited
        resetColor(panelBatalCustomer);
    }//GEN-LAST:event_labelBatalCustomerMouseExited

    private void labelBatalCustomerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelBatalCustomerMouseClicked
        this.aktifkanFormCustomer(MODE_TABLE);
    }//GEN-LAST:event_labelBatalCustomerMouseClicked

    private void panelBatalCustomerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBatalCustomerMouseClicked
        this.aktifkanFormCustomer(MODE_TABLE);
    }//GEN-LAST:event_panelBatalCustomerMouseClicked

    private void panelBaruCustomerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBaruCustomerMouseClicked
        this.aktifkanFormCustomer(MODE_TAMBAH);
    }//GEN-LAST:event_panelBaruCustomerMouseClicked

    private void panelDetailCustomerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelDetailCustomerMouseClicked
        this.aktifkanFormCustomer(MODE_TAMPIL);
    }//GEN-LAST:event_panelDetailCustomerMouseClicked

    private void labelUbahCustomerMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelUbahCustomerMouseEntered
        setColor(panelUbahCustomer);
    }//GEN-LAST:event_labelUbahCustomerMouseEntered

    private void labelUbahCustomerMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelUbahCustomerMouseExited
        ResetColor(panelUbahCustomer);
    }//GEN-LAST:event_labelUbahCustomerMouseExited

    private void labelSimpanCustomerMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelSimpanCustomerMouseEntered
        setColor(panelSimpanCustomer);
    }//GEN-LAST:event_labelSimpanCustomerMouseEntered

    private void labelSimpanCustomerMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelSimpanCustomerMouseExited
        ResetColor(panelSimpanCustomer);
    }//GEN-LAST:event_labelSimpanCustomerMouseExited

    private void jLabel16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseClicked
        if(txtIDCustomer.getText().equals("")){
            this.aktifkanFormCustomer(MODE_TABLE);
        } else {
            this.aktifkanFormCustomer(MODE_TAMPIL);
        }
    }//GEN-LAST:event_jLabel16MouseClicked

    private void jLabel16MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseEntered
        setColor(panelBatalSimpanCustomer);
    }//GEN-LAST:event_jLabel16MouseEntered

    private void jLabel16MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseExited
        ResetColor(panelBatalSimpanCustomer);
    }//GEN-LAST:event_jLabel16MouseExited

    private void buttonCariCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCariCustomerActionPerformed
        cariCustomer(txtCariCustomer.getText());
    }//GEN-LAST:event_buttonCariCustomerActionPerformed

    private void buttonRefreshCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRefreshCustomerActionPerformed
        txtCariCustomer.setText("");
        tampilkanListCustomer(null);
    }//GEN-LAST:event_buttonRefreshCustomerActionPerformed

    private void labelSimpanCustomerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelSimpanCustomerMouseClicked
        if(validateField()){
            customer CustomerEdit = new customer();
            String sql = "SELECT MAX(ID_CUSTOMER) FROM customer";
            ResultSet rs = koneksi.executeQuery(sql);
            try{
                while(rs.next()){
                    String maxKode = rs.getString(1);
                    int count = 1000;
                    if(maxKode == null){
                        count++;
                        CustomerEdit.setId_customer(String.valueOf(count));
                    }
                    if(maxKode != null){
                        count = Integer.valueOf(maxKode);
                        count++;
                        if(txtIDCustomer.getText().equals("")){
                            CustomerEdit.setId_customer(String.valueOf(count));
                        } else {
                            CustomerEdit.setId_customer(txtIDCustomer.getText());
                        }
                    }
                }
            } catch(SQLException ex){
                Logger.getLogger(RentalMobilFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            String namaDepan = txtNamaDepan.getText();
            CustomerEdit.setNama_depan(namaDepan);
            String namaBelakang = txtNamaBelakang.getText();
            CustomerEdit.setNama_belakang(namaBelakang);
            String alamat = txtAlamat.getText();
            CustomerEdit.setAlamat(alamat);
            String kontak = txtKontak.getText();
            CustomerEdit.setKontak(kontak);
            char JK = 0;
            if(rdLaki.isSelected()){
                JK = 'L';
            } else if(rdPerempuan.isSelected()){
                JK = 'P';
            }
            CustomerEdit.setJk(JK);
            
            customer Customer = new customer();
            try{
                Customer = CustomerDAO.findCustomer(CustomerEdit.getId_customer());
            } catch(SQLException ex){
                JOptionPane.showMessageDialog(this, "SQL ERROR "+ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
            
            if(Customer == null){
                try{
                    CustomerDAO.insertCustomer(CustomerEdit);
                } catch(SQLException ex){
                    JOptionPane.showMessageDialog(this, "SQL ERROR "+ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
                String berhasil = "Data berhasil disimpan";
                JOptionPane.showMessageDialog(this, berhasil, "Tersimpan", JOptionPane.INFORMATION_MESSAGE);
            } else {
                int ada = JOptionPane.showConfirmDialog(this, "Data dengan ID : "+CustomerEdit.getId_customer()+" sudah terdaftar!"
                        + "\nApakah anda ingin mengubah data tersebut?", "Ubah data", JOptionPane.YES_NO_OPTION);
                if(ada == JOptionPane.YES_OPTION){
                    try{
                        CustomerDAO.updateCustomer(CustomerEdit);
                        JOptionPane.showMessageDialog(this, "Data berhasil diubah", "Ubah", JOptionPane.INFORMATION_MESSAGE);
                    } catch(SQLException ex){
                        JOptionPane.showMessageDialog(this, "SQL ERROR "+ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                        ex.printStackTrace();
                    }
                }
            }
            tampilkanListCustomer(null);
            tampilkanCustomer(CustomerEdit.getId_customer());
            this.aktifkanFormCustomer(MODE_TAMPIL);
        }
    }//GEN-LAST:event_labelSimpanCustomerMouseClicked

    private void labelTittleTambahCustomerMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelTittleTambahCustomerMouseEntered
        setColor(panelTittleTambahCustomer);
    }//GEN-LAST:event_labelTittleTambahCustomerMouseEntered

    private void labelTittleTambahCustomerMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelTittleTambahCustomerMouseExited
        ResetColor(panelTittleTambahCustomer);
    }//GEN-LAST:event_labelTittleTambahCustomerMouseExited

    private void labelUbahCustomerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelUbahCustomerMouseClicked
        this.aktifkanFormCustomer(MODE_TAMBAH);
        labelIDCustomerTambah.setText("ID Customer");
    }//GEN-LAST:event_labelUbahCustomerMouseClicked

    private void labelTittleDetailCustomerMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelTittleDetailCustomerMouseEntered
        setColor(panelTittleDetailCustomer);
    }//GEN-LAST:event_labelTittleDetailCustomerMouseEntered

    private void labelTittleDetailCustomerMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelTittleDetailCustomerMouseExited
        ResetColor(panelTittleDetailCustomer);
    }//GEN-LAST:event_labelTittleDetailCustomerMouseExited

    private void panelTittleDetailCustomerMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelTittleDetailCustomerMouseEntered
        setColor(panelTittleDetailCustomer);
    }//GEN-LAST:event_panelTittleDetailCustomerMouseEntered

    private void panelTittleDetailCustomerMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelTittleDetailCustomerMouseExited
        ResetColor(panelTittleDetailCustomer);
    }//GEN-LAST:event_panelTittleDetailCustomerMouseExited

    private void panelTittleTambahCustomerMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelTittleTambahCustomerMouseEntered
        setColor(panelTittleTambahCustomer);
    }//GEN-LAST:event_panelTittleTambahCustomerMouseEntered

    private void panelTittleTambahCustomerMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelTittleTambahCustomerMouseExited
        ResetColor(panelTittleTambahCustomer);
    }//GEN-LAST:event_panelTittleTambahCustomerMouseExited

    private void labelDetailUnitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelDetailUnitMouseEntered
        setColor(panelMenuDetailUnit);
    }//GEN-LAST:event_labelDetailUnitMouseEntered

    private void labelDetailUnitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelDetailUnitMouseExited
        resetColor(panelMenuDetailUnit);
    }//GEN-LAST:event_labelDetailUnitMouseExited

    private void labelTambahUnitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelTambahUnitMouseEntered
        setColor(panelMenuTambahUnit);
    }//GEN-LAST:event_labelTambahUnitMouseEntered

    private void labelTambahUnitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelTambahUnitMouseExited
        resetColor(panelMenuTambahUnit);
    }//GEN-LAST:event_labelTambahUnitMouseExited

    private void labelDataUnitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelDataUnitMouseEntered
        setColor(panelMenuDataUnit);
    }//GEN-LAST:event_labelDataUnitMouseEntered

    private void labelDataUnitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelDataUnitMouseExited
        resetColor(panelMenuDataUnit);
    }//GEN-LAST:event_labelDataUnitMouseExited

    private void labelDetailUnitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelDetailUnitMouseClicked
        this.aktifkanModeFormUnit(MODE_TAMPIL);
        tampilkanUnit(tableCustomer.getValueAt(tableCustomer.getSelectedRow(), 0).toString());
    }//GEN-LAST:event_labelDetailUnitMouseClicked

    private void labelTambahUnitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelTambahUnitMouseClicked
        this.aktifkanModeFormUnit(MODE_TAMBAH);
        txtIDUnit.setText("");
        clearFieldTambahUnit();
    }//GEN-LAST:event_labelTambahUnitMouseClicked

    private void labelDataUnitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelDataUnitMouseClicked
        this.aktifkanModeFormUnit(MODE_TABLE);
    }//GEN-LAST:event_labelDataUnitMouseClicked

    private void labelTittleTampilUnitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelTittleTampilUnitMouseExited
        ResetColor(panelTittleTampilUnit);
    }//GEN-LAST:event_labelTittleTampilUnitMouseExited

    private void labelTittleTampilUnitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelTittleTampilUnitMouseEntered
        setColor(panelTittleTampilUnit);
    }//GEN-LAST:event_labelTittleTampilUnitMouseEntered

    private void labelTittleTambahUnitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelTittleTambahUnitMouseEntered
        setColor(panelTittleTambahUnit);
    }//GEN-LAST:event_labelTittleTambahUnitMouseEntered

    private void labelTittleTambahUnitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelTittleTambahUnitMouseExited
        ResetColor(panelTittleTambahUnit);
    }//GEN-LAST:event_labelTittleTambahUnitMouseExited

    private void labelUbahDataUnitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelUbahDataUnitMouseEntered
        setColor(panelUbahDataUnit);
    }//GEN-LAST:event_labelUbahDataUnitMouseEntered

    private void labelUbahDataUnitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelUbahDataUnitMouseExited
        ResetColor(panelUbahDataUnit);
    }//GEN-LAST:event_labelUbahDataUnitMouseExited

    private void labelSimpanUnitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelSimpanUnitMouseEntered
        setColor(panelSimpanUnit);
    }//GEN-LAST:event_labelSimpanUnitMouseEntered

    private void labelSimpanUnitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelSimpanUnitMouseExited
        ResetColor(panelSimpanUnit);
    }//GEN-LAST:event_labelSimpanUnitMouseExited

    private void labelBatalUnitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelBatalUnitMouseExited
        ResetColor(panelBatalUnit);
    }//GEN-LAST:event_labelBatalUnitMouseExited

    private void labelBatalUnitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelBatalUnitMouseEntered
        setColor(panelBatalUnit);
    }//GEN-LAST:event_labelBatalUnitMouseEntered

    private void labelBatalUnitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelBatalUnitMouseClicked
        if(txtIDUnit.getText().equals("")){
            this.aktifkanModeFormUnit(MODE_TABLE);
        } else {
            this.aktifkanModeFormUnit(MODE_TAMPIL);
        }
    }//GEN-LAST:event_labelBatalUnitMouseClicked

    private void labelSimpanUnitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelSimpanUnitMouseClicked
        if(validateFieldUnit()){
            unit UnitEdit = new unit();
            String sql = "SELECT MAX(ID_UNIT) FROM unit";
            ResultSet rs = koneksi.executeQuery(sql);
            try{
                while(rs.next()){
                    String maxKode = rs.getString(1);
                    int count = 1000;
                    if(maxKode == null){
                        count++;
                        UnitEdit.setIDUnit(String.valueOf(count));
                    }
                    if(maxKode != null){
                        count = Integer.valueOf(maxKode);
                        count++;
                        if(txtIDUnit.getText().equals("")){
                            UnitEdit.setIDUnit(String.valueOf(count));
                        } else {
                            UnitEdit.setIDUnit(txtIDUnit.getText());
                        }
                    }
                }
            } catch(SQLException ex){
                Logger.getLogger(RentalMobilFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            String ID_JENIS = txtIDJenisUnit.getText();
            UnitEdit.setIDJenis(ID_JENIS);
            String NAMA_UNIT = txtNamaUnit.getText();
            UnitEdit.setNamaUnit(NAMA_UNIT);
            String TAHUN = txtTahunUnit.getText();
            UnitEdit.setTahun(Integer.valueOf(TAHUN));
            
            unit Unit = new unit();
            try{
                Unit = UnitDAO.findUnit(UnitEdit.getIDUnit());
            } catch(SQLException ex){
                JOptionPane.showMessageDialog(this, "SQL ERROR "+ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
            if(Unit == null){
                try{
                    UnitDAO.insertUnit(UnitEdit);
                } catch(SQLException ex){
                    JOptionPane.showMessageDialog(this, "SQL ERROR "+ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
                String berhasil = "Data berhasil disimpan";
                JOptionPane.showMessageDialog(this, berhasil, "Tersimpan", JOptionPane.INFORMATION_MESSAGE);
            } else {
                int ada = JOptionPane.showConfirmDialog(this, "Unit dengan ID : "+UnitEdit.getIDUnit()+" sudah terdaftar!"
                        + "\nApakah anda ingin mengubah data tersebut?", "Ubah data", JOptionPane.YES_NO_OPTION);
                if(ada == JOptionPane.YES_OPTION){
                    try{
                        UnitDAO.updateUnit(UnitEdit);
                        JOptionPane.showMessageDialog(this, "Data berhasil diubah", "Ubah", JOptionPane.INFORMATION_MESSAGE);
                    } catch(SQLException ex){
                        JOptionPane.showMessageDialog(this, "SQL ERROR "+ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                        ex.printStackTrace();
                    }
                }
            }
            tampilkanListUnit(null);
            tampilkanUnit(UnitEdit.getIDUnit());
            this.aktifkanModeFormUnit(MODE_TAMPIL);
        }
    }//GEN-LAST:event_labelSimpanUnitMouseClicked

    private void buttonCariUnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCariUnitActionPerformed
        if(this.modeForm == MODE_TABLE){
            tampilkanListUnit(txtCariUnit.getText());
        } else if(this.modeForm == MODE_TAMPIL){
            tampilkanListUnit(txtCariUnit.getText());
            tampilkanUnit(txtCariUnit.getText());
        }
    }//GEN-LAST:event_buttonCariUnitActionPerformed

    private void buttonRefreshUnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRefreshUnitActionPerformed
        txtCariUnit.setText("");
        tampilkanListUnit(null);
    }//GEN-LAST:event_buttonRefreshUnitActionPerformed

    private void labelUbahDataUnitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelUbahDataUnitMouseClicked
        this.aktifkanModeFormUnit(MODE_TAMBAH);
        labelIDUnitTambah.setText("ID Unit");
    }//GEN-LAST:event_labelUbahDataUnitMouseClicked

    private void labelDetailSewaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelDetailSewaMouseEntered
        setColor(panelDetailSewa);
    }//GEN-LAST:event_labelDetailSewaMouseEntered

    private void labelDetailSewaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelDetailSewaMouseExited
        resetColor(panelDetailSewa);
    }//GEN-LAST:event_labelDetailSewaMouseExited

    private void labelBaruSewaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelBaruSewaMouseEntered
        setColor(panelBaruSewa);
    }//GEN-LAST:event_labelBaruSewaMouseEntered

    private void labelBaruSewaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelBaruSewaMouseExited
        resetColor(panelBaruSewa);
    }//GEN-LAST:event_labelBaruSewaMouseExited

    private void labelDataSewaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelDataSewaMouseEntered
        setColor(panelDataSewa);
    }//GEN-LAST:event_labelDataSewaMouseEntered

    private void labelDataSewaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelDataSewaMouseExited
        resetColor(panelDataSewa);
    }//GEN-LAST:event_labelDataSewaMouseExited

    private void labelDetailSewaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelDetailSewaMouseClicked
        this.aktifkanModeFormSewa(MODE_TAMPIL);
    }//GEN-LAST:event_labelDetailSewaMouseClicked

    private void labelBaruSewaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelBaruSewaMouseClicked
        this.aktifkanModeFormSewa(MODE_TAMBAH);
        txtNoSewa.setText("");
    }//GEN-LAST:event_labelBaruSewaMouseClicked

    private void labelDataSewaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelDataSewaMouseClicked
        this.aktifkanModeFormSewa(MODE_TABLE);
    }//GEN-LAST:event_labelDataSewaMouseClicked

    private void labelTittleTambahSewaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelTittleTambahSewaMouseEntered
        setColor(panelTittleSewa);
    }//GEN-LAST:event_labelTittleTambahSewaMouseEntered

    private void labelTittleTambahSewaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelTittleTambahSewaMouseExited
        ResetColor(panelTittleSewa);
    }//GEN-LAST:event_labelTittleTambahSewaMouseExited

    private void labelBatalSewaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelBatalSewaMouseEntered
        setColor(panelBatalSewa);
    }//GEN-LAST:event_labelBatalSewaMouseEntered

    private void labelBatalSewaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelBatalSewaMouseExited
        ResetColor(panelBatalSewa);
    }//GEN-LAST:event_labelBatalSewaMouseExited

    private void labelSimpanSewaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelSimpanSewaMouseEntered
        setColor(panelSimpanSewa);
    }//GEN-LAST:event_labelSimpanSewaMouseEntered

    private void labelSimpanSewaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelSimpanSewaMouseExited
        ResetColor(panelSimpanSewa);
    }//GEN-LAST:event_labelSimpanSewaMouseExited

    private void labelBatalSewaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelBatalSewaMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_labelBatalSewaMousePressed

    private void labelTittleTampilSewaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelTittleTampilSewaMouseEntered
        setColor(panelTittleTampilSewa);
    }//GEN-LAST:event_labelTittleTampilSewaMouseEntered

    private void labelTittleTampilSewaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelTittleTampilSewaMouseExited
        ResetColor(panelTittleTampilSewa);
    }//GEN-LAST:event_labelTittleTampilSewaMouseExited

    private void panelTittleTampilSewaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelTittleTampilSewaMouseEntered
        setColor(panelTittleTampilSewa);
    }//GEN-LAST:event_panelTittleTampilSewaMouseEntered

    private void panelTittleTampilSewaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelTittleTampilSewaMouseExited
        ResetColor(panelTittleTampilSewa);
    }//GEN-LAST:event_panelTittleTampilSewaMouseExited

    private void buttonHapusCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonHapusCustomerActionPerformed
        if(txtCariCustomer.getText().equals("")){
            deleteCustomerKlikTable();
            tampilkanListCustomer(null);
        } else {
            deleteCustomer(txtCariCustomer.getText());
            txtCariCustomer.setText("");
            tampilkanListCustomer(null);
        }
    }//GEN-LAST:event_buttonHapusCustomerActionPerformed

    private void labelSimpanSewaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelSimpanSewaMouseClicked
        if(validateSewa()){
            sewa SewaEdit = new sewa();
            detailSewa DetailEdit = new detailSewa();
            String sql = "SELECT MAX(NO_SEWA) FROM penyewaan";
            ResultSet rs = koneksi.executeQuery(sql);
            try{
                while(rs.next()){
                    String maxKode = rs.getString(1);
                    int count = 0;
                    if(maxKode == null){
                        count++;
                        SewaEdit.setNoSewa(String.valueOf(count));
                        DetailEdit.setNoSewa(count);
                    }
                    if(maxKode != null){
                        count = Integer.valueOf(maxKode);
                        count++;
                        if(txtNoSewa.getText().equals("")){
                            SewaEdit.setNoSewa(String.valueOf(count));
                            DetailEdit.setNoSewa(count);
                        } else {
                            SewaEdit.setNoSewa(txtNoSewa.getText());
                            DetailEdit.setNoSewa(Integer.valueOf(txtNoSewa.getText()));
                        }
                    }
                }
            } catch(SQLException ex){
                Logger.getLogger(RentalMobilFrame.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
            
            int id_unit;
            
            do{
                String idUnit = txtIDUnitSewa.getText();
                id_unit = Integer.valueOf(idUnit);
                SewaEdit.setIdUnit(idUnit);
                DetailEdit.setIdUnit(txtIDUnitSewa.getText());
            } while(Queue.cek(id_unit) != false);
            
            SewaEdit.setIdCustomer(txtIDCustomerSewa.getText());
            SewaEdit.setLamaSewa(Integer.valueOf(txtLamaHariSewa.getText()));
            total = harga * Integer.parseInt(txtLamaHariSewa.getText());
            DetailEdit.setHarga(total);
            SewaEdit.setJaminan(txtJaminanSewa.getText());
            
            String SQL = "INSERT INTO penyewaan (NO_SEWA,ID_CUSTOMER,JML_HARI,JAMINAN) VALUES ('"+SewaEdit.getNoSewa()+"','"+SewaEdit.getIdCustomer()+"',"
                    + "'"+SewaEdit.getLamaSewa()+"','"+SewaEdit.getJaminan()+"')";
            int status = koneksi.execute(SQL);
            SQL = "INSERT INTO detail_penyewaan (ID_UNIT,NO_SEWA,HARGA_SEWA) VALUES ('"+DetailEdit.getIdUnit()+"','"+DetailEdit.getNoSewa()+"','"+total+"')";
            status = koneksi.execute(SQL);
            if(status == 1){
                Queue.push(id_unit);
                cetakNota(SewaEdit.getNoSewa());
            } else {
                JOptionPane.showMessageDialog(this, "Data gagal disimpan", "Gagal", JOptionPane.WARNING_MESSAGE);
            }
            
            /*sewa Sewa = new sewa();
            detailSewa DetailSewa = new detailSewa();
            
            try {
                Sewa = SewaDAO.findSewa(SewaEdit.getNoSewa());
                DetailSewa = DetailSewaDAO.findDetailSewa(DetailEdit.getIdUnit());
            } catch(SQLException ex){
                JOptionPane.showMessageDialog(this, "SQL ERROR "+ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
            
            if(Sewa == null && DetailSewa == null){
                try{
                    SewaDAO.insertSewa(SewaEdit);
                    DetailSewaDAO.insertDetailSewa(DetailEdit);
                    Queue.push(id_unit);
                } catch(SQLException ex){
                    JOptionPane.showMessageDialog(this, "SQL ERROR "+ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
                String berhasil = "Data berhasil disimpan";
                JOptionPane.showMessageDialog(this, berhasil, "Tersimpan", JOptionPane.INFORMATION_MESSAGE);
            } else {
                int ada = JOptionPane.showConfirmDialog(this, "Data dengan No Sewa : "+SewaEdit.getNoSewa()+" sudah terdaftar!"
                        + "\nApakah anda ingin mengubah data tersebut?", "Ubah data", JOptionPane.YES_NO_OPTION);
                if(ada == JOptionPane.YES_OPTION){
                    try{
                        SewaDAO.updateSewa(SewaEdit);
                        DetailSewaDAO.updateDetailSewa(DetailEdit);
                        Queue.push(id_unit);
                        JOptionPane.showMessageDialog(this, "Data berhasil diubah", "Ubah data", JOptionPane.INFORMATION_MESSAGE);
                    } catch(SQLException ex){
                        JOptionPane.showMessageDialog(this, "SQL ERROR "+ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                        ex.printStackTrace();
                    }
                }
            }*/
            tampilkanListSewa(null);
            tampilkanSewa(SewaEdit.getNoSewa());
            this.aktifkanModeFormSewa(MODE_TAMPIL);
            cetakNota(SewaEdit.getNoSewa());
        }
    }//GEN-LAST:event_labelSimpanSewaMouseClicked

    private void labelUbahSewaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelUbahSewaMouseClicked
        this.aktifkanModeFormSewa(MODE_TAMBAH);
        labelNoSewaTambah.setText("No Sewa");
    }//GEN-LAST:event_labelUbahSewaMouseClicked

    private void buttonCariCustomerMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonCariCustomerMouseEntered
        SetColor(buttonCariCustomer);
    }//GEN-LAST:event_buttonCariCustomerMouseEntered

    private void buttonCariCustomerMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonCariCustomerMouseExited
        RSetColor(buttonCariCustomer);
    }//GEN-LAST:event_buttonCariCustomerMouseExited

    private void tampilkanJumlahRecordCustomer(){
        labelJumlahRecordCustomer.setText("terdapat "+ tableCustomer.getModel().getRowCount()+" record");
    }
    
    private void tampilkanJumlahRecordUnit(){
        labelJumlahRecordUnit.setText("terdapat "+ tableUnit.getModel().getRowCount()+" record");
    }
    
    private void tampilkanJumlahRecordSewa(){
        labelJumlahRecordSewa.setText("terdapat "+tableSewa.getModel().getRowCount()+" record");
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(RentalMobilFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RentalMobilFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RentalMobilFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RentalMobilFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RentalMobilFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCariCustomer;
    private javax.swing.JButton buttonCariSewa;
    private javax.swing.JButton buttonCariUnit;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton buttonHapusCustomer;
    private javax.swing.JButton buttonHapusSewa;
    private javax.swing.JButton buttonHapusUnit;
    private javax.swing.JButton buttonKeluarCustomer;
    private javax.swing.JButton buttonKeluarSewa;
    private javax.swing.JButton buttonKeluarUnit;
    private javax.swing.JButton buttonRefreshCustomer;
    private javax.swing.JButton buttonRefreshSewa;
    private javax.swing.JButton buttonRefreshUnit;
    private javax.swing.JTextArea cetakDetailSewa;
    private javax.swing.JMenu file;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel labelAlamatCustomer;
    private javax.swing.JLabel labelAwalMenu;
    private javax.swing.JLabel labelBackground;
    private javax.swing.JLabel labelBaruCustomer;
    private javax.swing.JLabel labelBaruSewa;
    private javax.swing.JLabel labelBatalCustomer;
    private javax.swing.JLabel labelBatalSewa;
    private javax.swing.JLabel labelBatalUnit;
    private javax.swing.JLabel labelCariCustomer;
    private javax.swing.JLabel labelCariUnit;
    private javax.swing.JLabel labelCetakSewa;
    private javax.swing.JLabel labelDataSewa;
    private javax.swing.JLabel labelDataUnit;
    private javax.swing.JLabel labelDetailCustomer;
    private javax.swing.JLabel labelDetailSewa;
    private javax.swing.JLabel labelDetailUnit;
    private javax.swing.JLabel labelHargaSewa;
    private javax.swing.JLabel labelIDCustomer;
    private javax.swing.JLabel labelIDCustomerTambah;
    private javax.swing.JLabel labelIDJenisUnit;
    private javax.swing.JLabel labelIDUnit;
    private javax.swing.JLabel labelIDUnitSewa;
    private javax.swing.JLabel labelIDUnitTambah;
    private javax.swing.JLabel labelJenisKelaminCustomer;
    private javax.swing.JLabel labelJumlahRecordCustomer;
    private javax.swing.JLabel labelJumlahRecordSewa;
    private javax.swing.JLabel labelJumlahRecordUnit;
    private javax.swing.JLabel labelKontakCustomer;
    private javax.swing.JLabel labelNamaBelakangCustomer;
    private javax.swing.JLabel labelNamaDepanCustomer;
    private javax.swing.JLabel labelNamaUnit;
    private javax.swing.JLabel labelNoSewa;
    private javax.swing.JLabel labelNoSewaTambah;
    private javax.swing.JLabel labelSimpanCustomer;
    private javax.swing.JLabel labelSimpanSewa;
    private javax.swing.JLabel labelSimpanUnit;
    private javax.swing.JLabel labelTahunUnit;
    private javax.swing.JLabel labelTambahUnit;
    private javax.swing.JLabel labelTittleDetailCustomer;
    private javax.swing.JLabel labelTittleTambahCustomer;
    private javax.swing.JLabel labelTittleTambahSewa;
    private javax.swing.JLabel labelTittleTambahUnit;
    private javax.swing.JLabel labelTittleTampilSewa;
    private javax.swing.JLabel labelTittleTampilUnit;
    private javax.swing.JLabel labelUbahCustomer;
    private javax.swing.JLabel labelUbahDataUnit;
    private javax.swing.JLabel labelUbahSewa;
    private javax.swing.JMenu menu;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JLabel menuCustomer;
    private javax.swing.JMenuItem menuRental;
    private javax.swing.JLabel menuSewa;
    private javax.swing.JLabel menuUnit;
    private javax.swing.JPanel panelBackground;
    private javax.swing.JPanel panelBaruCustomer;
    private javax.swing.JPanel panelBaruSewa;
    private javax.swing.JPanel panelBatalCustomer;
    private javax.swing.JPanel panelBatalSewa;
    private javax.swing.JPanel panelBatalSimpanCustomer;
    private javax.swing.JPanel panelBatalUnit;
    private javax.swing.JPanel panelCustomer;
    private javax.swing.JPanel panelDasar;
    private javax.swing.JPanel panelDataSewa;
    private javax.swing.JPanel panelDetailCustomer;
    private javax.swing.JPanel panelDetailSewa;
    private javax.swing.JPanel panelFormCustomer;
    private javax.swing.JPanel panelFormMenu;
    private javax.swing.JPanel panelFormSewa;
    private javax.swing.JPanel panelFormUnit;
    private javax.swing.JPanel panelMenu;
    private javax.swing.JPanel panelMenuAwal;
    private javax.swing.JPanel panelMenuBackground;
    private javax.swing.JPanel panelMenuCustomer;
    private javax.swing.JPanel panelMenuDataUnit;
    private javax.swing.JPanel panelMenuDetailUnit;
    private javax.swing.JPanel panelMenuSewa;
    private javax.swing.JPanel panelMenuTambahUnit;
    private javax.swing.JPanel panelMenuUnit;
    private javax.swing.JPanel panelSewa;
    private javax.swing.JPanel panelSimpanCustomer;
    private javax.swing.JPanel panelSimpanSewa;
    private javax.swing.JPanel panelSimpanUnit;
    private javax.swing.JPanel panelTableCustomer;
    private javax.swing.JPanel panelTableSewa;
    private javax.swing.JPanel panelTableUnit;
    private javax.swing.JPanel panelTambahCustomer;
    private javax.swing.JPanel panelTambahSewa;
    private javax.swing.JPanel panelTambahUnit;
    private javax.swing.JPanel panelTampilCustomer;
    private javax.swing.JPanel panelTampilSewa;
    private javax.swing.JPanel panelTampilUnit;
    private javax.swing.JPanel panelTittleDetailCustomer;
    private javax.swing.JPanel panelTittleSewa;
    private javax.swing.JPanel panelTittleTambahCustomer;
    private javax.swing.JPanel panelTittleTambahUnit;
    private javax.swing.JPanel panelTittleTampilSewa;
    private javax.swing.JPanel panelTittleTampilUnit;
    private javax.swing.JPanel panelUbahCustomer;
    private javax.swing.JPanel panelUbahDataUnit;
    private javax.swing.JPanel panelUbahSewa;
    private javax.swing.JPanel panelUnit;
    private javax.swing.JRadioButton rdLaki;
    private javax.swing.JRadioButton rdPerempuan;
    private javax.swing.JTable tableCustomer;
    private javax.swing.JTable tableSewa;
    private javax.swing.JTable tableUnit;
    private javax.swing.JTextField txtAlamat;
    private javax.swing.JTextField txtCariCustomer;
    private javax.swing.JTextField txtCariSewa;
    private javax.swing.JTextField txtCariUnit;
    private javax.swing.JLabel txtIDCustomer;
    private javax.swing.JTextField txtIDCustomerSewa;
    private javax.swing.JTextField txtIDJenisUnit;
    private javax.swing.JLabel txtIDUnit;
    private javax.swing.JTextField txtIDUnitSewa;
    private javax.swing.JTextField txtJaminanSewa;
    private javax.swing.JTextField txtKontak;
    private javax.swing.JTextField txtLamaHariSewa;
    private javax.swing.JTextField txtNamaBelakang;
    private javax.swing.JTextField txtNamaDepan;
    private javax.swing.JTextField txtNamaUnit;
    private javax.swing.JLabel txtNoSewa;
    private javax.swing.JTextField txtTahunUnit;
    // End of variables declaration//GEN-END:variables

    public void tampilkanListCustomer(String param){
        try{
            dataCustomer = customerDAO.getInstance().getListCustomer(param);
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        tableCustomer.setModel(new customerDataTable(dataCustomer));
        if(tableCustomer.getModel().getRowCount() > 0){
            tableCustomer.setRowSelectionInterval(0, 0);
        }
        tampilkanJumlahRecordCustomer();
    }
    
    public void tampilkanListUnit(String param){
        try{
            dataUnit = unitDAO.getInstance().getListUnit(param);
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        tableUnit.setModel(new unitDataTable(dataUnit));
        if(tableUnit.getModel().getRowCount() > 0){
            tableUnit.setRowSelectionInterval(0, 0);
        }
        tampilkanJumlahRecordUnit();
    }
    
    public void tampilkanListSewa(String param){
        try{
            dataSewa = sewaDAO.getInstance().getListSewa(param);
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        tableSewa.setModel(new sewaDataTable(dataSewa));
        if(tableSewa.getModel().getRowCount() > 0){
            tableSewa.setRowSelectionInterval(0, 0);
        }
        tampilkanJumlahRecordSewa();
    }
}
