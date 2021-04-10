package Models;

/**
 *
 * @author grupo1
 */
public class WarDTO {
    private int id_guerra;
    private String anio_inicio;
    private String anio_fin;
    private String nombre;

    public WarDTO() {
        
    }

    public WarDTO(int id_guerra, String anio_inicio, String anio_fin, String nombre) {
        this.id_guerra = id_guerra;
        this.anio_inicio = anio_inicio;
        this.anio_fin = anio_fin;
        this.nombre = nombre;
    }

    public int getId_guerra() {
        return id_guerra;
    }

    public void setId_guerra(int id_guerra) {
        this.id_guerra = id_guerra;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "CountryDTO{" + "id_guerra=" + id_guerra + ", anio_inicio=" + anio_inicio + ", anio_fin=" + anio_fin + ", nombre=" + nombre + '}';
    }

    
    
    
    
}
