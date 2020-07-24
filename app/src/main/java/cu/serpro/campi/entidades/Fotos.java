package cu.serpro.campi.entidades;

public class Fotos {

    private Integer id;
    private Integer idcampismo;
    private String nombre;

    public Fotos() {
        this.id = id;
        this.idcampismo = idcampismo;
        this.nombre = nombre;
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
}