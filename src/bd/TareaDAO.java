package bd;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.swing.JOptionPane;

import domain.BDTarea;
import domain.BDTrabajador;

public class TareaDAO {
	
	
//	Metodos que podemos implementar si queremos:
//		actualizar la duración
//		asignar trabajadores
//		quitar trabajadores
//		recuperar trabajadores asignados

    
    // BUSCAR TAREA POR NOMBRE
    public static BDTarea buscarTareaPorNombre(String nombre) {
        String sql = "SELECT id, nombre, duracion, estado FROM tarea WHERE nombre = ?";
        try (Connection conn = ConexionSQLite.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nombre);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return crearTareaDesdeResultSet(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    //Buscar tareas por nombre
    public static List<BDTarea> buscarTareasPorNombre(String nombre) {
        String sql = "SELECT id, nombre, duracion, estado FROM tarea WHERE nombre LIKE ?";

        ArrayList<BDTarea> tareas = new ArrayList<>();

        try (Connection conn = ConexionSQLite.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            // Buscamos por nombre con comodines
            ps.setString(1, "%" + nombre + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    tareas.add(crearTareaDesdeResultSet(rs));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tareas;
    }
    



   
    // INSERTAR TAREA
    public static void insertarTarea(BDTarea t) {

        // Comprobar duplicados
        if (buscarTareaPorNombre(t.getNombre()) != null) {
        	JOptionPane.showMessageDialog(null, 
        		    "ERROR: La tarea '" + t.getNombre() + "' ya existe.", 
        		    "Tarea Duplicada", 
        		    JOptionPane.ERROR_MESSAGE);
        		return;
        }

        String sql = "INSERT INTO tarea (nombre, duracion, estado) VALUES (?, ?, ?)";

        try (Connection conn = ConexionSQLite.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, t.getNombre());
            ps.setInt(2, t.getDuracion());
            ps.setString(3, t.getEstado());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // BUSCAR POR ID
    public BDTarea buscarPorId(int id) {
        String sql = "SELECT id, nombre, duracion, estado FROM tarea WHERE id = ?";

        try (Connection conn = ConexionSQLite.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return crearTareaDesdeResultSet(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    // ACTUALIZAR ESTADO A FINALIZADO
    public static void marcarCompletada(int id) {
        String sql = "UPDATE tarea SET estado = 'finalizado' WHERE id = ?";

        try (Connection conn = ConexionSQLite.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // ACTUALIZAR ESTADO A FINALIZADO
    public static void marcarPendiente(int id) {
        String sql = "UPDATE tarea SET estado = 'pendiente' WHERE id = ?";

        try (Connection conn = ConexionSQLite.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // ACTUALIZAR ESTADO A FINALIZADO
    public static void marcarEjecutando(int id) {
        String sql = "UPDATE tarea SET estado = 'ejecutando' WHERE id = ?";

        try (Connection conn = ConexionSQLite.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // OBTENER TODAS LAS TAREAS
    public static HashSet<BDTarea> getAllTareas() {

        HashSet<BDTarea> lista = new HashSet<>();
        String sql = "SELECT id, nombre, duracion, estado FROM tarea";

        try (Connection conn = ConexionSQLite.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(crearTareaDesdeResultSet(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    
    // ELIMINAR POR NOMBRE
    public static void eliminarPorNombre(String nombre) {
        String sql = "DELETE FROM tarea WHERE nombre = ?";

        try (Connection conn = ConexionSQLite.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nombre);
            int filas = ps.executeUpdate();

            if (filas > 0)
                System.out.println("Tarea '" + nombre + "' eliminada.");
            else
                System.out.println("No existe ninguna tarea con nombre '" + nombre + "'");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // CREAR OBJETO BDTarea DESDE LA TABLA DE LA BBDD
    private static BDTarea crearTareaDesdeResultSet(ResultSet rs) throws SQLException {

        int id = rs.getInt("id");
        String nombre = rs.getString("nombre");
        int duracion = rs.getInt("duracion");
        String estado = rs.getString("estado");

        // conjunto vacío de trabajadores por ahora
        HashSet<BDTrabajador> trabajadores = new HashSet<>();

        BDTarea t = new BDTarea(id, nombre, duracion, estado, trabajadores);
        t.setEstado(estado);

        return t;
    }
    
    // PARA SACAR LAS TAREAS QUE TIENE UN TRABAJADOR, PARA PONERLO EN LA JLIST DE V VER TRATABAJADORES, AL CLICKAR.
    // Conseguir las tareas ejecutando del trabajador, PARA EL LOGOUT

    public static ArrayList<BDTarea> getTareasEjecutandoDeTrabajador(int idTrabajador) {
        ArrayList<BDTarea> tareas = new ArrayList<>();

        String sql =
            "SELECT t.id, t.nombre, t.duracion, t.estado " +
            "FROM tarea t " +
            "JOIN tarea_trabajador tt ON t.id = tt.id_tarea " +
            "WHERE tt.id_trabajador = ? " +
            "AND t.estado = 'ejecutando'";

        try (Connection conn = ConexionSQLite.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idTrabajador);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    tareas.add(crearTareaDesdeResultSet(rs));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tareas;
    }
    
 // PARA SACAR LAS TAREAS QUE TIENE UN TRABAJADOR
    public static ArrayList<BDTarea> getTareasDeTrabajador(int idTrabajador) {
        ArrayList<BDTarea> tareas = new ArrayList<>();

        String sql = 
            "SELECT t.id, t.nombre, t.duracion, t.estado " +
            "FROM tarea t " +
            "JOIN tarea_trabajador tt ON t.id = tt.id_tarea " +
            "WHERE tt.id_trabajador = ?";

        try (Connection conn = ConexionSQLite.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idTrabajador);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    tareas.add(crearTareaDesdeResultSet(rs));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tareas;
    }


    
    

}
