package bd;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import domain.BDTrabajador;
import domain.BDTarea;

public class TrabajadorDAO {


    //crea BDTrabajador desde la tabla de la BBDD
    private static BDTrabajador crearTrabajadorDesdeResultSet(ResultSet rs) throws SQLException {

        int id = rs.getInt("id");
        String nombre = rs.getString("nombre");
        String contrasenya = rs.getString("contrasenya");

        // Estos datos no los recoge la base de datos (son una relación que se hace despues)así que los iniciamos vacíos
        HashSet<BDTarea> tareasAsignadas = new HashSet<>();
        LocalDateTime entrada = null;
        HashMap<LocalDate, ArrayList<LocalDateTime>> registrosFichaje = new HashMap<>();

        return new BDTrabajador(
                id,
                nombre,
                contrasenya,
                tareasAsignadas,
                entrada,
                registrosFichaje
        );
    }



    // BUSCAR TRABAJADOR POR NOMBRE
    public static BDTrabajador buscarPorNombre(String nombre) {

        String sql = "SELECT id, nombre, contrasenya FROM trabajador WHERE nombre = ?";

        try (Connection conn = ConexionSQLite.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nombre);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return crearTrabajadorDesdeResultSet(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    // INSERTAR TRABAJADOR
    public static void insertarTrabajador(BDTrabajador t) {

        if (buscarPorNombre(t.getNombre()) != null) {
            System.out.println("ERROR: El trabajador '" + t.getNombre() + "' ya existe.");
            return;
        }

        String sql = "INSERT INTO trabajador (nombre, contrasenya) VALUES (?, ?)";

        try (Connection conn = ConexionSQLite.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, t.getNombre());
            ps.setString(2, t.getContraseyna());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    // LOGIN TRABAJADOR
    public BDTrabajador login(String nombre, String contrasenya) {

        String sql = "SELECT id, nombre, contraseyna FROM trabajador WHERE nombre = ? AND contrasenya = ?";

        try (Connection conn = ConexionSQLite.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nombre);
            ps.setString(2, contrasenya);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return crearTrabajadorDesdeResultSet(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }



    // BUSCAR POR ID
    public BDTrabajador buscarPorId(int id) {

        String sql = "SELECT id, nombre, contraseyna FROM trabajador WHERE id = ?";

        try (Connection conn = ConexionSQLite.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return crearTrabajadorDesdeResultSet(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }



    // OBTENER TODOS LOS TRABAJADORES
    public static HashSet<BDTrabajador> getAllTrabajadores() {

        HashSet<BDTrabajador> lista = new HashSet<>();
        String sql = "SELECT id, nombre, contrasenya FROM trabajador";

        try (Connection conn = ConexionSQLite.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(crearTrabajadorDesdeResultSet(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }



    // ELIMINAR TRABAJADOR POR NOMBRE
    public static void eliminarPorNombre(String nombre) {

        String sql = "DELETE FROM trabajador WHERE nombre = ?";

        try (Connection conn = ConexionSQLite.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nombre);

            int filas = ps.executeUpdate();

            if (filas > 0)
                System.out.println("Trabajador '" + nombre + "' eliminado.");
            else
                System.out.println("No existe trabajador con nombre '" + nombre + "'.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
