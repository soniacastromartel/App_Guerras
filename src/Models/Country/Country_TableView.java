package Models.Country;

import Models.CountryDTO;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author grupo1
 */
public class Country_TableView extends AbstractTableModel {

    CountryDTO country;
    Country_Businness bussiness;
    private Object object;

    //Constructor
    public Country_TableView(Country_Businness bussiness) {
        this.country = country;
        this.bussiness = bussiness;
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
    public Object getValueAt(int rowIndex, int columIndex) {
        try {
            if (columIndex == 0) {
                bussiness.next(rowIndex + 1);
                country = bussiness.getCountryDTO();
                return country.getId_pais();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en getValueAt");
        }
        if (columIndex == 1) {
            return country.getNombre();
        }
        if (columIndex == 2) {
            return country.getId_periodo();
        }
        if (columIndex == 3) {
            return country.getAnio_inicio();
        }
        if (columIndex == 4) {
            return country.getAnio_fin();
        }
        return object;
    }

    @Override
    public String getColumnName(int column) {
        String name = "";
        try {
            name = bussiness.columnName(column + 1);
        } catch (SQLException ex) {
            Logger.getLogger(Country_TableView.class.getName()).log(Level.SEVERE, null, ex);
        }
        return name;
    }

}
