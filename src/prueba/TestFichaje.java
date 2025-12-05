package prueba;   

import java.time.LocalDateTime;

import bd.FichajeDAO;

public class TestFichaje {

    public static void main(String[] args) {
        int idTrabajador = 1; // usa un id que exista en tu tabla TRABAJADOR

        try {
            LocalDateTime ahora = LocalDateTime.now();

            FichajeDAO.crearFichajeEntrada(idTrabajador, ahora);

            System.out.println("Fichaje insertado correctamente para trabajador " + idTrabajador);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

