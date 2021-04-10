package Models;

/**
 *
 * @author grupo1
 */
public class CountryDTO {
    private int id_pais;
    private String nombre;
    private int id_periodo;
    private String anio_inicio;
    private String anio_fin;

    public CountryDTO() {
        
    }

    public CountryDTO(int id_pais, String nombre) {
        this.id_pais = id_pais;
        this.nombre = nombre;
    }
    
    

    public CountryDTO(int id_pais, String nombre, int id_periodo, String anio_inicio, String anio_fin) {
        this.id_pais = id_pais;
        this.nombre = nombre;
        this.id_periodo = id_periodo;
        this.anio_inicio = anio_inicio;
        this.anio_fin = anio_fin;
    }

    public int getId_pais() {
        return id_pais;
    }

    public void setId_pais(int id_pais) {
        this.id_pais = id_pais;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId_periodo() {
        return id_periodo;
    }

    public void setId_periodo(int id_periodo) {
        this.id_periodo = id_periodo;
    }

    public String getAnio_inicio() {
        return anio_inicio;
    }

    public void setAnio_inicio(String anio_inicio) {
        this.anio_inicio = anio_inicio;
    }

    public String getAnio_fin() {
        return anio_fin;
    }

    public void setAnio_fin(String anio_fin) {
        this.anio_fin = anio_fin;
    }

    @Override
    public String toString() {
        return "CountryDTO{" + "id_pais=" + id_pais + ", nombre=" + nombre + ", id_periodo=" + id_periodo + ", anio_inicio=" + anio_inicio + ", anio_fin=" + anio_fin + '}';
    }
    
    
    
    
    
    
    
    
}
