
package Models.War;

import Models.WarDTO;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author grupo1
 */
public class War_TableView extends AbstractTableModel {

    WarDTO war;
    War_Businness bussiness;
    private Object object;

    public War_TableView(War_Businness bussiness) {
        this.war = war;
        this.bussiness = bussiness;
        
        
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        try {

            if (columnIndex == 0) {
                bussiness.next(rowIndex + 1);
                war = bussiness.getWarDTO();
                return war.getId_guerra();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en getValueAt");
        }
        if (columnIndex == 1) {
            return war.getAnio_inicio();
        }
        if (columnIndex == 2) {
            return war.getAnio_fin();
        }
        if (columnIndex == 3) {
            return war.getNombre();
        }
        return object;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    @Override
    public String getColumnName(int column) {
        String name = "";
        try {
            name = bussiness.columnName(column + 1);
        } catch (SQLException ex) {
            Logger.getLogger(War_Businness.class.getName()).log(Level.SEVERE, null, ex);
        }

        return name;

    }

    @Override
    public int getColumnCount() {
        int columna = 0;
        try {
            columna = bussiness.columnCount();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al contar las Columnas");
        }
        return columna;
    }

    @Override
    public int getRowCount() {
        int fila = 0;
        try {
            fila = bussiness.rowCount();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al contar las filas");
        }
        return fila;
    }

}

