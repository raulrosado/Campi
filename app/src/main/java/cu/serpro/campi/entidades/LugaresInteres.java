package cu.serpro.campi.entidades;

public class LugaresInteres {

    private Integer id;
    private Integer idcampismo;
    private String nombre;
    private String imagen;

    public LugaresInteres() {
        this.id = id;
        this.idcampismo = idcampismo;
        this.nombre = nombre;
        this.imagen = imagen;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdcampismo() {
        return idcampismo;
    }

    public void setIdcampismo(Integer idcampismo) {
        this.idcampismo = idcampismo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}