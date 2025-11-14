package bd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.BDTrabajador;

public class TrabajadorDAO {

    // INSERTAR un trabajador a la base de datos
    public void insertar(BDTrabajador t) {
        String sql = "INSERT INTO trabajador(nombre, contraseña) VALUES (?, ?)";

        try (Connection conn = ConexionSQLite.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, t.getNombre());
            ps.setString(2, t.getContraseña());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // LOGIN: buscar trabajador por nombre y contraseña
    public BDTrabajador login(String nombre, String contraseña) {
        String sql = "SELECT id, nombre, contraseña FROM trabajador "
                   + "WHERE nombre = ? AND contraseña = ?";

        try (Connection conn = ConexionSQLite.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nombre);
            ps.setString(2, contraseña);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new BDTrabajador(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("contraseña")
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace(); //aquí estaria bien sacar esto en una ventana nueva de error si nos da tiempo
        }

        return null;
    }

    // BUSCAR por ID en la BBDD
    public BDTrabajador buscarPorId(int id) {
        String sql = "SELECT id, nombre, contraseña FROM trabajador WHERE id = ?";

        try (Connection conn = ConexionSQLite.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new BDTrabajador(
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
}
