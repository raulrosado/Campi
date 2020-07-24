package cu.serpro.campi.entidades;

public class Provincias {

    private Integer id;
    private String nombre;
    private String cantCamp;
    private String imagen;

    public Provincias() {
        this.id = id;
        this.nombre = nombre;
        this.cantCamp = cantCamp;
        this.imagen = imagen;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCantCamp() {
        return cantCamp;
    }

    public void setCantCamp(String cantCamp) {
        this.cantCamp = cantCamp;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}