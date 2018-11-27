package com.doke.Model;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author doke
 */
public class sewaDataTable extends AbstractTableModel {
    
    private static final String[] columnNames = {"No Sewa", "ID Customer", "Lama Sewa", "Jaminan"};
    
    private static final int KOLOM_NO_SEWA = 0;
    private static final int KOLOM_ID_CUS = 1;
    private static final int KOLOM_LAMA_SEWA = 2;
    private static final int KOLOM_JAMINAN = 3;
    
    List<sewa> data;
    
    public sewaDataTable(List<sewa> data){
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
        sewa Sewa = data.get(rowIndex);
        switch(columnIndex){
            case KOLOM_NO_SEWA:
                return Sewa.getNoSewa();
            case KOLOM_ID_CUS:
                return Sewa.getIdCustomer();
            case KOLOM_LAMA_SEWA:
                return Sewa.getLamaSewa();
            case KOLOM_JAMINAN:
                return Sewa.getJaminan();
        }
        return null;
    }
    @Override
    public String getColumnName(int column){
        return columnNames[column];
    }
}
