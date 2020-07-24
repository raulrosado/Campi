package cu.serpro.campi.entidades;

public class Servicios {

    private Integer id;
    private String nombreservicio;
    private String imagen;

    public Servicios() {
        this.id = id;
        this.nombreservicio = nombreservicio;
        this.imagen = imagen;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreservicio() {
        return nombreservicio;
    }

    public void setNombreservicio(String nombreservicio) {
        this.nombreservicio = nombreservicio;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}