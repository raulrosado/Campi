package cu.serpro.campi.utilidades;

public class utilidades {

    public static final Integer grid = 2;

    public static final String TABLA_CAMPISMOS = "campismos";

    public static final String Id_campismos = "id";
    public static final String Camp_titulo = "titulo";
    public static final String Camp_descripcion = "descripcion";
    public static final String Camp_imagen = "imagen";
    public static final String Camp_localizacion = "localizacion";
    public static final String Camp_telefono = "telefono";
    public static final String Camp_ubicacion = "ubicacion";
    public static final String Camp_direccion = "direccion";
    public static final String Camp_servicios = "servicios";
    public static final String Camp_categoria = "categoria";
    public static final String Camp_tipoturismo = "tipoturismo";
    public static final String Camp_municipio = "municipio";
    public static final String Camp_provincia = "provincia";
    public static final String Camp_estado = "estado";

    public static final String TABLA_PRECIOS = "precios";
    public static final String Id_precio = "id";
    public static final String Id_prec_idcampismo = "idcampismo";
    public static final String Prec_tipoetapa = "tipoetapa";
    public static final String Prec_dos = "dos";
    public static final String Prec_tre = "tre";
    public static final String Prec_cuatro = "cuatro";
    public static final String Prec_cinco = "cinco";
    public static final String Prec_seis = "seis";
    public static final String Prec_siete = "siete";
    public static final String Prec_ocho = "ocho";

    public static final String TABLA_FOTOS = "fotos";
    public static final String Id_foto = "id";
    public static final String Id_foto_idcampismo = "idcampismo";
    public static final String Fot_nombre = "nombre";

    public static final String TABLA_SERVICIOS = "servicios";
    public static final String Id_servicio = "id";
    public static final String Serv_nombre = "nombreservicio";
    public static final String Serv_imagen = "imagen";

    public static final String TABLA_LUGARES = "lugaresinteres";
    public static final String Id_lugares = "id";
    public static final String Id_Lug_idcampismo = "idcampismo";
    public static final String Lug_nombre = "nombre";
    public static final String Lug_imagen = "imagen";

    public static final String TABLA_OFICINAS = "oficinas";
    public static final String Id_oficinasrecervas = "id";
    public static final String OR_nombre = "nombre";
    public static final String OR_imagen = "imagen";
    public static final String OR_ubicacion = "ubicacion";
    public static final String OR_correo = "correo";
    public static final String OR_telefono = "telefono";
    public static final String OR_direccion = "direccion";
    public static final String OR_municipio = "municipio";
    public static final String OR_provincia = "provincia";

    public static final String TABLA_PROVINCIAS = "provincias";
    public static final String Id_provincias = "id";
    public static final String Prov_nombre = "nombre";
    public static final String Prov_cantCamp = "cantCamp";

    public static final String CREAR_PRECIO = "CREATE TABLE "+TABLA_PRECIOS+" ("+Id_precio+" INTEGER PRIMARY KEY, "+Id_prec_idcampismo+" INTEGER, "+Prec_tipoetapa+" TEXT" +", "+Prec_dos+" TEXT, "+Prec_tre+" TEXT, "+Prec_cuatro+" TEXT, "+Prec_cinco+" TEXT, "+Prec_seis+" TEXT, "+Prec_siete+" TEXT, "+Prec_ocho+" TEXT)";
    public static final String CREAR_FOTOS = "CREATE TABLE "+TABLA_FOTOS+" ("+Id_foto+" INTEGER PRIMARY KEY, "+Id_foto_idcampismo+" INTEGER, "+Fot_nombre+" TEXT)";
    public static final String CREAR_SERVICIOS = "CREATE TABLE "+TABLA_SERVICIOS+" ("+Id_servicio+" INTEGER PRIMARY KEY, "+Serv_nombre+" TEXT, "+Serv_imagen+" TEXT)";
    public static final String CREAR_LUGARES = "CREATE TABLE "+TABLA_LUGARES+" ("+Id_lugares+" INTEGER PRIMARY KEY, "+Id_Lug_idcampismo+" INTEGER, "+Lug_nombre+" TEXT, "+Lug_imagen+" TEXT)";
    public static final String CREAR_CAMPISMOS = "CREATE TABLE "+TABLA_CAMPISMOS+" ("+Id_campismos+" INTEGER PRIMARY KEY, "+Camp_titulo+" TEXT, "+Camp_descripcion+" TEXT, "+Camp_imagen+" TEXT, "+Camp_localizacion+" TEXT, "+Camp_telefono+" TEXT, "+Camp_ubicacion+" TEXT, "+Camp_direccion+" TEXT, "+Camp_servicios+" TEXT, "+Camp_categoria+" INTEGER, "+Camp_tipoturismo+" TEXT, "+Camp_municipio+" TEXT, "+Camp_provincia+" TEXT, "+Camp_estado+" INTEGER)";
    public static final String CREAR_OFICINASRESERVAS = "CREATE TABLE "+TABLA_OFICINAS+" ("+Id_oficinasrecervas+" INTEGER PRIMARY KEY, "+OR_nombre+" TEXT, "+OR_imagen+" TEXT, "+OR_ubicacion+" TEXT, "+OR_correo+" TEXT, "+OR_telefono+" TEXT, "+OR_direccion+" TEXT, "+OR_municipio+" TEXT, "+OR_provincia+" TEXT)";
    public static final String CREAR_PROVINCIAS = "CREATE TABLE "+TABLA_PROVINCIAS+" ("+Id_provincias+" INTEGER PRIMARY KEY, "+Prov_nombre+" TEXT, "+Prov_cantCamp+" INTEGER)";


}
