package bd;

import java.sql.*;

import domain.BDAdmin;

public class AdminDAO {

    // INSERTAR administrador
    public void insertar(BDAdmin a) {
        String sql = "INSERT INTO administrador(nombre, contraseña) VALUES (?, ?)";

        try (Connection conn = ConexionSQLite.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, a.getNombre());
            ps.setString(2, a.getContraseña());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // LOGIN 
    public BDAdmin login(String nombre, String contraseña) {
        String sql = "SELECT id, nombre, contraseña FROM administrador "
                   + "WHERE nombre = ? AND contraseña = ?";

        try (Connection conn = ConexionSQLite.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nombre);
            ps.setString(2, contraseña);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new BDAdmin(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("contraseña")
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Buscar por ID
    public BDAdmin buscarPorId(int id) {
        String sql = "SELECT id, nombre, contraseña FROM administrador WHERE id = ?";

        try (Connection conn = ConexionSQLite.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new BDAdmin(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("contraseña")
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace(); // lo mismo que en trabajadorDAO
        }

        return null;
    }
}
