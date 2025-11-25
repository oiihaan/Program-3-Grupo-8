package bd;

import java.sql.*;

import domain.BDAdmin;

public class AdminDAO {

    // LOGIN ADMIN
    public BDAdmin login(String nombre, String contrasenya) {

        String sql = "SELECT id, nombre, contraseyna FROM admin WHERE nombre = ? AND contraseyna = ?";

        try (Connection conn = ConexionSQLite.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, nombre);
            pst.setString(2, contrasenya);

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return new BDAdmin(rs.getInt("id"), rs.getString("nombre"), rs.getString("contraseyna"));
                }
            }

        } catch (SQLException e) {
            System.out.println("Error en login de admin");
            e.printStackTrace();
        }

        return null; //si el login no funciona
    }
}
