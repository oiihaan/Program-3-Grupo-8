package domain;


	

public class BDTrabajador {
    private int id;
    private String nombre;
    private String contraseña;

    public BDTrabajador(int id, String nombre, String contraseña) {
        this.id = id;
        this.nombre = nombre;
        this.contraseña = contraseña;
    }

    public BDTrabajador(String nombre, String contraseña) {
        this(0, nombre, contraseña);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    @Override
    public String toString() {
        return "TrabajadorBD{id=" + id + ", nombre='" + nombre + "'}";
    }
}


