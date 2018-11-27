package com.doke.Model;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author doke
 */
public class unitDataTable extends AbstractTableModel {
    
    private static final String[] columnNames = {"ID Unit", "ID Jenis", "Nama Unit", "Tahun"};
    
    private static final int KOLOM_ID_UNIT = 0;
    private static final int KOLOM_ID_JENIS = 1;
    private static final int KOLOM_NAMA_UNIT = 2;
    private static final int KOLOM_TAHUN = 3;
    
    List<unit> data;
    
    public unitDataTable(List<unit> data){
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
        unit Unit = data.get(rowIndex);
        switch(columnIndex){
            case KOLOM_ID_UNIT:
                return Unit.getIDUnit();
            case KOLOM_ID_JENIS:
                return Unit.getIDJenis();
            case KOLOM_NAMA_UNIT:
                return Unit.getNamaUnit();
            case KOLOM_TAHUN:
                return Unit.getTahun();
        }
        return null;
    }
    
    @Override
    public String getColumnName(int column){
        return columnNames[column];
    }
}
