package domain;


public class BDTarea {
    private int id;
    private String nombre;
    private int tiempoEjecucion;        
    private boolean completada;
    private Integer trabajadorId;       
    private Integer adminIdCreador;     

    public BDTarea(int id, String nombre, int tiempoEjecucion,
                   boolean completada, Integer trabajadorId, Integer adminIdCreador) {
        this.id = id;
        this.nombre = nombre;
        this.tiempoEjecucion = tiempoEjecucion;
        this.completada = completada;
        this.trabajadorId = trabajadorId;
        this.adminIdCreador = adminIdCreador;
    }

    public BDTarea(String nombre, int tiempoEjecucion,
                   boolean completada, Integer trabajadorId, Integer adminIdCreador) {
        this(0, nombre, tiempoEjecucion, completada, trabajadorId, adminIdCreador);
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

    public int getTiempoEjecucion() {
        return tiempoEjecucion;
    }

    public void setTiempoEjecucion(int tiempoEjecucion) {
        this.tiempoEjecucion = tiempoEjecucion;
    }

    public boolean isCompletada() {
        return completada;
    }

    public void setCompletada(boolean completada) {
        this.completada = completada;
    }

    public Integer getTrabajadorId() {
        return trabajadorId;
    }

    public void setTrabajadorId(Integer trabajadorId) {
        this.trabajadorId = trabajadorId;
    }

    public Integer getAdminIdCreador() {
        return adminIdCreador;
    }

    public void setAdminIdCreador(Integer adminIdCreador) {
        this.adminIdCreador = adminIdCreador;
    }

    @Override
    public String toString() {
        return "TareaBD{id=" + id + ", nombre='" + nombre + "', completada=" + completada + "}";
    }
}


