package bd;

import java.sql.*;

import domain.BDTarea;
import domain.BDTrabajador; // solo si lo necesitas en el futuro
import java.util.HashSet;

public class TareaDAO {

	public static BDTarea buscarTareaPorNombre(String nombre) {
	    String sql = "SELECT id, nombre, tiempo_ejecucion, completada FROM tarea WHERE nombre = ?";
	    try (Connection conn = ConexionSQLite.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {
	        ps.setString(1, nombre);
	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                return new BDTarea(rs.getInt("id"), rs.getString("nombre"), rs.getInt("tiempo_ejecucion"), rs.getBoolean("completada"));
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	
    public static void insertarTarea(BDTarea t) {
    	// Comprobar si el nombre ya existe
	    if (buscarTareaPorNombre(t.getNombre()) != null) {
	        System.out.println("ERROR: La tarea " + t.getNombre() + " ya existe.");
	        return;
	    }
        String sql = "INSERT INTO tarea(nombre, tiempo_ejecucion, completada) "
                   + "VALUES (?, ?, ?)";

        try (Connection conn = ConexionSQLite.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, t.getNombre());
            ps.setInt(2, t.getDuracion());
            ps.setInt(3, t.isCompletada() ? 1 : 0);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Buscar tarea por ID
    public BDTarea buscarPorId(int id) {
        String sql = "SELECT id, nombre, tiempo_ejecucion, completada FROM tarea WHERE id = ?";

        try (Connection conn = ConexionSQLite.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int idTarea = rs.getInt("id");
                    String nombre = rs.getString("nombre");
                    int duracion = rs.getInt("tiempo_ejecucion");
                    boolean completada = rs.getInt("completada") == 1;

                    // De momento no cargamos trabajadores desde BD → conjunto vacío
                    HashSet<BDTrabajador> trabajadores = new HashSet<>();

                    // Construimos la BDTarea con los datos básicos
                    BDTarea t = new BDTarea(idTarea, nombre, duracion, trabajadores);

                    // Ajustamos el estado según la columna completada
                    if (completada) {
                        t.setEstado("finalizado");
                        t.setEjecucion(null);
                    } else {
                        t.setEstado("pendiente");   // o lo que quieras por defecto
                        t.setEjecucion(false);
                    }

                    return t;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace(); // igual que en TrabajadorDAO
        }

        return null;
    }

    // Marcar tarea completada en la BD
    public void marcarCompletada(int id) {
        String sql = "UPDATE tarea SET completada = 1 WHERE id = ?";

        try (Connection conn = ConexionSQLite.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static HashSet<BDTarea> getAllTareas() {
    	HashSet<BDTarea> listaTareas = new HashSet<>();
        String sql = "SELECT id, nombre, tiempo_ejecucion, completada FROM tarea";
        try (Connection conn = ConexionSQLite.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
            	int idTarea = rs.getInt("id");
                String nombre = rs.getString("nombre");
                int duracion = rs.getInt("tiempo_ejecucion");
                boolean completada = rs.getInt("completada") == 1;
                BDTarea tarea = new BDTarea (idTarea, nombre, duracion, completada);
                listaTareas.add(tarea);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaTareas;
    }
    
    public static void eliminarPorNombre(String nombre) {
        String sql = "DELETE FROM tarea WHERE nombre = ?";

        try (Connection conn = ConexionSQLite.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nombre);
            int filasBorradas = ps.executeUpdate();

            if (filasBorradas > 0) {
                System.out.println("Tarea '" + nombre + "' eliminada correctamente.");
            } else {
                System.out.println("No existe ninguna tarea con nombre '" + nombre + "'.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
