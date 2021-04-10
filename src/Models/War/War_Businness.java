package Models.War;

import Data.Connect;
import Models.DetailsWarDTO;
import Models.WarDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Sonia Castro (soniacastromartel@gmail.com)
 */
public class War_Businness {

    Connect conn;
    String sql;
    ResultSet rs = null;
    WarDTO war;
    DetailsWarDTO detailsWar;
    Connection conexion;
    PreparedStatement sentencia;

    public War_Businness() throws ClassNotFoundException, SQLException {
        if (conn == null) {
            conn = new Connect();

        }
    }

    //Obtener el objeto
    public WarDTO getWarDTO() {
        try {
// rs = conn.getRs();
            war = new WarDTO();
            war.setId_guerra(rs.getInt("id_guerra"));
            war.setNombre(rs.getString("nombre"));
            war.setAnio_inicio(rs.getString("anio_inicio"));
            war.setAnio_fin(rs.getString("anio_fin"));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Oh, oh. Hubo un error!");
        }

        return war;
    }

    //BUSQUEDAS
    public void searchAll() throws SQLException {

//        conn.crearConsulta("select * from guerra");
//        rs = conn.getRs();
        sql = "Select * from guerra";
        sentencia = conn.crearPrepareStatement(sql);
        rs = sentencia.executeQuery();

        while (rs.next()) {
            war = new WarDTO();
            war.setId_guerra(rs.getInt("id_guerra"));
            war.setNombre(rs.getString("nombre"));
            war.setAnio_inicio(rs.getString("anio_inicio"));
            war.setAnio_fin(rs.getString("anio_fin"));
        }

    }

    public void queriesI() throws SQLException {
        conn.crearConsulta("select * from guerra");
        rs = conn.getRs();
        // rs.next();

    }

    public void lightSearch(String nombre) throws SQLException {

        sql = "select * from guerra where nombre like? or anio_inicio like? or anio_fin like?";

        sentencia = conn.crearPrepareStatement(sql);
        sentencia.setString(1, "%" + nombre + "%");
        sentencia.setString(2, "%" + nombre + "%");
        sentencia.setString(3, "%" + nombre + "%");
        rs = sentencia.executeQuery();

    }

    public void findById(int valor) throws SQLException {

        sql = "select * from guerra where id_guerra=?";

        sentencia = conn.crearPrepareStatement(sql);
        sentencia.setInt(1, valor);
        rs = sentencia.executeQuery();

    }

    //insertar registro
    public void insert(WarDTO war) throws SQLException {

        sql = "insert into guerra (id_guerra,nombre,anio_inicio,anio_fin)values(?,?,?,?)";

        sentencia = conn.crearPrepareStatement(sql);
        sentencia.setInt(1, war.getId_guerra());
        sentencia.setString(3, war.getAnio_inicio());
        sentencia.setString(4, war.getAnio_fin());        
        sentencia.setString(2, war.getNombre());
        conn.actualizarBaseDatos(sentencia);
    }

    //borrar registro
    public void delete(WarDTO war) throws SQLException {

        sql = "DELETE FROM guerra where id_guerra=?";

        sentencia = conn.crearPrepareStatement(sql);
        sentencia.setInt(1, war.getId_guerra());
        conn.actualizarBaseDatos(sentencia);

    }

    //actualizar registro
    public void update(WarDTO war) throws SQLException {

        sql = "update guerra set nombre=?,anio_inicio=?,anio_fin=? where id_guerra=?";

        sentencia = conn.crearPrepareStatement(sql);
        sentencia.setInt(4, war.getId_guerra());
        sentencia.setString(2, war.getAnio_inicio());
        sentencia.setString(3, war.getAnio_fin());
        sentencia.setString(1, war.getNombre());

        conn.actualizarBaseDatos(sentencia);

    }
    
    
    //PARA CONTROLLERConsults
    public void searchAllDataFromWar(WarDTO war) throws SQLException {

//        conn.crearConsulta("select * from guerra");
//        rs = conn.getRs();
        sql = "SELECT guerra.nombre 'nombre_guerra',guerra.anio_inicio 'inicio_guerra',guerra.anio_fin 'fin_guerra', "
                + "  contendiente.nombre 'nombre_contendiente',contendiente.ganador 'ganador_contendiente', "
                + "    pais.nombre 'nombre_pais',periodo_independecia.anio_inicio 'inicio_independiente',periodo_independecia.anio_fin 'fin_independiente', "
                + "    union_bandos.fecha_union 'inicio_unioncont',union_bandos.fecha_abandono 'abandono_unioncont' "
                + "    FROM guerra "
                + "RIGHT JOIN contendiente USING(id_guerra) "
                + "RIGHT JOIN union_bandos USING (id_contendiente) "
                + "RIGHT JOIN pais USING(id_pais) "
                + "LEFT JOIN periodo_independecia USING (id_pais)"
                + "WHERE guerra.id_guerra=?";
        sentencia = conn.crearPrepareStatement(sql);
        sentencia.setInt(1, war.getId_guerra());
        rs = sentencia.executeQuery();
  
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
    
    
    
    public ArrayList<DetailsWarDTO> returnDetails() throws SQLException{
                
        ArrayList<DetailsWarDTO> detailsWarList = new ArrayList<>();
        
        while (rs.next()) {
            detailsWar= new DetailsWarDTO(rs.getString("nombre_guerra"), rs.getString("inicio_guerra"), rs.getString("fin_guerra"), rs.getString("nombre_contendiente"), rs.getString("ganador_contendiente"), rs.getString("nombre_pais"), rs.getString("inicio_independiente"), rs.getString("fin_independiente"), rs.getString("inicio_unioncont"), rs.getString("abandono_unioncont"));
            detailsWarList.add(detailsWar);
        }
        return detailsWarList;
    }//Fin de returnDetails

  
}//Find e clase principal
