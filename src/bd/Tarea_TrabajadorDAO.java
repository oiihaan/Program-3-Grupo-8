package bd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import domain.BDTarea;

public class Tarea_TrabajadorDAO {
	
	public static void insertarTrabajadoresATarea(Integer idTrarea , Integer idTrabajador) {


        String sql = "INSERT INTO tarea_trabajador (id_tarea, id_trabajador) VALUES (?, ?)";

        try (Connection conn = ConexionSQLite.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idTrarea);
            ps.setInt(2,idTrabajador);

            ps.executeUpdate();

        } catch (SQLException e) {
        	System.out.println("Error al añadir");
            e.printStackTrace();
        }
    }

    public static void eliminarTrabajadorDeTarea(Integer idTarea, Integer idTrabajador) {

        String sql = "DELETE FROM tarea_trabajador WHERE id_tarea = ? AND id_trabajador = ?";

        try (Connection conn = ConexionSQLite.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idTarea);
            ps.setInt(2, idTrabajador);

            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al eliminar");
            e.printStackTrace();
        }
    }

}
