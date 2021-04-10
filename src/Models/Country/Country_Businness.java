package Models.Country;

import Data.Connect;
import Models.CountryDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 *
 * @author Sonia Castro Martel
 */
public class Country_Businness {

    Connect conn;
    String sql;
    ResultSet rs = null;
    CountryDTO country;
    Connection conexion;
    PreparedStatement sentencia;

    public Country_Businness() throws ClassNotFoundException, SQLException {
        if (conn == null) {
            conn = new Connect();

        }
    }

    //Obtener el objeto
    public CountryDTO getCountryDTO() throws SQLException {

        //rs = conn.getRs();
        country = new CountryDTO();
        country.setId_pais(rs.getInt("id_pais"));
        country.setNombre(rs.getString("nombre"));
        country.setId_periodo(rs.getInt("id_periodo"));
        country.setAnio_fin(rs.getString("anio_fin"));
        country.setAnio_inicio(rs.getString("anio_inicio"));

        return country;
    }

    //BUSQUEDAS
    public void searchAll() throws SQLException {

//        conn.crearConsulta("select * from guerra");
//        rs = conn.getRs();
        sql = "Select * from pais";
        sentencia = conn.crearPrepareStatement(sql);
        rs = sentencia.executeQuery();

        while (rs.next()) {
            country = new CountryDTO();
            country.setId_pais(rs.getInt("id_pais"));
            country.setNombre(rs.getString("nombre"));

        }

    }

    public void searchAllIndependent() throws SQLException {

//        conn.crearConsulta("select * from guerra");
//        rs = conn.getRs();
        sql = "SELECT * FROM pais p, periodo_independecia pi where p.id_pais=pi.id_pais;";
        sentencia = conn.crearPrepareStatement(sql);
        rs = sentencia.executeQuery();

        while (rs.next()) {
            country = new CountryDTO();
            country.setId_pais(rs.getInt("id_pais"));
            country.setNombre(rs.getString("nombre"));
            country.setId_periodo(rs.getInt("id_periodo"));
            country.setAnio_inicio(rs.getString("anio_inicio"));
            country.setAnio_fin(rs.getString("anio_fin"));
        }

    }

    public void lightSearch(String nombre) throws SQLException {

        sql = "SELECT p.id_pais, p.nombre, pi.id_periodo, pi.anio_inicio, pi.anio_fin FROM pais p left join periodo_independecia pi on p.id_pais=pi.id_pais where p.nombre like? or pi.anio_inicio like? or pi.anio_fin like?";

        sentencia = conn.crearPrepareStatement(sql);
        sentencia.setString(1, "%" + nombre + "%");
        sentencia.setString(2, "%" + nombre + "%");
        sentencia.setString(3, "%" + nombre + "%");
        rs = sentencia.executeQuery();

    }

    public void findById(int valor) throws SQLException {

        sql = "select * from pais where id_pais=?";

        sentencia = conn.crearPrepareStatement(sql);
        sentencia.setInt(1, valor);
        rs = sentencia.executeQuery();

    }

    public void queriesI() throws SQLException {
        conn.crearConsulta("SELECT p.id_pais, p.nombre, pi.id_periodo, pi.anio_inicio, pi.anio_fin FROM pais p left join periodo_independecia"
                + " pi on p.id_pais=pi.id_pais");
        rs = conn.getRs();
        // rs.next();

    }

    //insertar registro
    public void insert(CountryDTO country) throws SQLException {

        sql = "insert into pais (nombre)values(?)";

        sentencia = conn.crearPrepareStatement(sql);
        sentencia.setString(1, country.getNombre());
        conn.actualizarBaseDatos(sentencia);

    }

    public void insertIndependencePeriod(CountryDTO country) throws SQLException {

        findAndSetCountryId(country);

        sql = "insert into periodo_independecia (id_pais, anio_inicio, anio_fin)values(?,?,?)";

        sentencia = conn.crearPrepareStatement(sql);
        sentencia.setInt(1, country.getId_pais());
        sentencia.setInt(2, Integer.parseInt(country.getAnio_inicio()));
        sentencia.setInt(3, Integer.parseInt(country.getAnio_fin()));

        //    sentencia.setString(2, country.getAnio_inicio());
        //    sentencia.setString(3, country.getAnio_fin());
        conn.actualizarBaseDatos(sentencia);

    }

    private void findAndSetCountryId(CountryDTO country) throws SQLException {
        sql = "select id_pais from pais where nombre=?";

        sentencia = conn.crearPrepareStatement(sql);
        sentencia.setString(1, country.getNombre());
        rs = sentencia.executeQuery();

        rs.next();
        country.setId_pais(rs.getInt("id_pais"));
    }

    //borrar registro
    public void delete(CountryDTO country) throws SQLException {

        sql = "DELETE FROM pais where id_pais=?";

        sentencia = conn.crearPrepareStatement(sql);
        sentencia.setInt(1, country.getId_pais());
        conn.actualizarBaseDatos(sentencia);

    }

    public void deleteIndependencePeriod(CountryDTO country) throws SQLException {
        findAndSetCountryId(country);
        sql = "DELETE FROM periodo_independecia where id_pais=?";

        sentencia = conn.crearPrepareStatement(sql);
        sentencia.setInt(1, country.getId_pais());
        conn.actualizarBaseDatos(sentencia);

    }

    //actualizar registro
    public void update(CountryDTO country) throws SQLException {

        sql = "update pais set nombre=? where id_pais=?";

        sentencia = conn.crearPrepareStatement(sql);
        sentencia.setString(1, country.getNombre());
        sentencia.setInt(2, country.getId_pais());

        System.out.println("" + sql);
        conn.actualizarBaseDatos(sentencia);

    }

    public void updateIndependencePeriod(CountryDTO country) throws SQLException {

        sql = "select COUNT(id_pais) 'r' from periodo_independecia where id_pais=?";

        sentencia = conn.crearPrepareStatement(sql);
        sentencia.setInt(1, country.getId_pais());
        rs = sentencia.executeQuery();

        rs.next();
        int aux = rs.getInt("r");

        if (aux > 0) {
            sql = "update periodo_independecia set anio_inicio=?, anio_fin=? where id_pais=?";

            sentencia = conn.crearPrepareStatement(sql);
            sentencia.setInt(1, Integer.parseInt(country.getAnio_inicio()));
            sentencia.setInt(2, Integer.parseInt(country.getAnio_fin()));
            sentencia.setInt(3, country.getId_pais());
            conn.actualizarBaseDatos(sentencia);

        } else {
            insertIndependencePeriod(country);

        }

    }

    //TABLEVIEW
    public int firstRow() throws SQLException {

        rs.first();
        return rs.getRow();
    }

    public int rowCount() throws SQLException {

        rs.last();
        return rs.getRow();

    }

    public void moveNext() throws SQLException {
        rs.next();

    }

    public void next(int i) throws SQLException {

        rs.absolute(i);

    }

    public int columnCount() throws SQLException {

        ResultSetMetaData md = rs.getMetaData();
        int count = md.getColumnCount();
        return count;
    }

    public String columnName(int column) throws SQLException {

        ResultSetMetaData md = rs.getMetaData();
        return md.getColumnName(column);

    }

}
