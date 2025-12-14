package bd;

import java.sql.*;
import java.util.HashSet;

import domain.BDAdmin;
import domain.BDTrabajador;

public class AdminDAO {

    // LOGIN ADMIN
	// Crea un Admin a partir de una fila del ResultSet
    private static BDAdmin crearAdminDesdeResultSet(ResultSet rs) throws SQLException {

        int id = rs.getInt("id");
        String nombre = rs.getString("nombre");
        String contrasenya = rs.getString("contrasenya");

        return new BDAdmin(
                id,
                nombre,
                contrasenya
        );
    }

    // BUSCAR ADMIN POR Nombre
    public static BDAdmin buscarPorUsuario(String usuario) {

        String sql = "SELECT id, nombre, contrasenya FROM admin WHERE nombre = ?";

        try (Connection conn = ConexionSQLite.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, usuario);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return crearAdminDesdeResultSet(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // INSERTAR ADMIN
    public static void insertarAdmin(BDAdmin a) {

        if (buscarPorUsuario(a.getNombre()) != null) {
            System.out.println("ERROR: El admin con usuario '" + a.getNombre() + "' ya existe.");
            return;
        }

        String sql = "INSERT INTO admin (nombre, contrasenya) VALUES (?, ?)";

        try (Connection conn = ConexionSQLite.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, a.getNombre());
            ps.setString(2, a.getContraseyna());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // LOGIN ADMIN
    public BDAdmin login(String usuario, String contrasenya) {

        String sql = "SELECT id, nombre, apellido, usuario, contrasenya FROM admin WHERE usuario = ? AND contrasenya = ?";

        try (Connection conn = ConexionSQLite.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, usuario);
            ps.setString(2, contrasenya);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return crearAdminDesdeResultSet(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // BUSCAR POR ID
    public BDAdmin buscarPorId(int id) {

        String sql = "SELECT id, nombre, apellido, usuario, contrasenya FROM admin WHERE id = ?";

        try (Connection conn = ConexionSQLite.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return crearAdminDesdeResultSet(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // OBTENER TODOS LOS ADMINS
    public static HashSet<BDAdmin> getAllAdmins() {

        HashSet<BDAdmin> lista = new HashSet<>();
        String sql = "SELECT id, nombre, contrasenya FROM admin";

        try (Connection conn = ConexionSQLite.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(crearAdminDesdeResultSet(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    // ELIMINAR ADMIN POR USUARIO
    public static void eliminarPorUsuario(String usuario) {

        String sql = "DELETE FROM admin WHERE usuario = ?";

        try (Connection conn = ConexionSQLite.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, usuario);

            int filas = ps.executeUpdate();

            if (filas > 0) {
                System.out.println("Admin con usuario '" + usuario + "' eliminado.");
            } else {
                System.out.println("No existe admin con usuario '" + usuario + "'.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}



