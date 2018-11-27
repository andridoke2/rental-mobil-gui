package com.doke.Model;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author doke
 */
public class detailSewaDataTable extends AbstractTableModel {
    
    private static final String[] columnNames = {"ID Unit", "No Sewa", "Harga"};
    
    private static final int KOLOM_ID_UNIT = 0;
    private static final int KOLOM_NO_SEWA = 1;
    private static final int KOLOM_HARGA = 2;
    
    List<detailSewa> data;
    
    public detailSewaDataTable(List<detailSewa> data){
        super();
        this.data = data;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        detailSewa DetailSewa = data.get(rowIndex);
        switch(columnIndex){
            case KOLOM_ID_UNIT:
                return DetailSewa.getIdUnit();
            case KOLOM_NO_SEWA:
                return DetailSewa.getNoSewa();
            case KOLOM_HARGA:
                return DetailSewa.getHarga();
        }
        return null;
    }
    
    @Override
    public String getColumnName(int column){
        return columnNames[column];
    }
}
