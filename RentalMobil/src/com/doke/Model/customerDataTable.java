package com.doke.Model;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author doke
 */
public class customerDataTable extends AbstractTableModel {
    
    private static final String[] columnNames = {"ID Customer","Nama Depan"};
    
    private static final int KOLOM_ID = 0;
    private static final int KOLOM_NAMA = 1;
    
    List<customer> data;
    
    public customerDataTable(List<customer> data){
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
        customer customer = data.get(rowIndex);
        switch(columnIndex){
            case KOLOM_ID:
                return customer.getId_customer();
            case KOLOM_NAMA:
                return customer.getNama_depan();
        }
        return null;
    }
    
    @Override
    public String getColumnName(int column){
        return columnNames[column];
    }
}
