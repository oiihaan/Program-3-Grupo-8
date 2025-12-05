package domain;

import java.time.LocalDateTime;

public class BDFichaje {

    private int id;
    private int idTrabajador;
    private LocalDateTime entrada;
    private LocalDateTime salida; // puede ser null

    public BDFichaje(int id, int idTrabajador,
                     LocalDateTime entrada, LocalDateTime salida) {
        this.id = id;
        this.idTrabajador = idTrabajador;
        this.entrada = entrada;
        this.salida = salida;
    }

    public int getId() {
        return id;
    }

    public int getIdTrabajador() {
        return idTrabajador;
    }

    public LocalDateTime getEntrada() {
        return entrada;
    }

    public LocalDateTime getSalida() {
        return salida;
    }

    public void setSalida(LocalDateTime salida) {
        this.salida = salida;
    }
}

