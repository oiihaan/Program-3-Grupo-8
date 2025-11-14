package bd;

import java.sql.*;

import domain.BDTarea;

public class TareaDAO {

    // INSERTAR tarea
    public void insertar(BDTarea t) {
        String sql = "INSERT INTO tarea(nombre, tiempo_ejecucion, completada, trabajador_id, admin_id_creador) "
                   + "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConexionSQLite.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, t.getNombre());
            ps.setInt(2, t.getTiempoEjecucion());
            ps.setInt(3, t.isCompletada() ? 1 : 0);

            if (t.getTrabajadorId() == null)
                ps.setNull(4, Types.INTEGER);
            else
                ps.setInt(4, t.getTrabajadorId());

            if (t.getAdminIdCreador() == null)
                ps.setNull(5, Types.INTEGER);
            else
                ps.setInt(5, t.getAdminIdCreador());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Buscar tarea por ID
    public BDTarea buscarPorId(int id) {
        String sql = "SELECT * FROM tarea WHERE id = ?";

        try (Connection conn = ConexionSQLite.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new BDTarea(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getInt("tiempo_ejecucion"),
                        rs.getInt("completada") == 1,
                        (Integer) rs.getObject("trabajador_id"),
                        (Integer) rs.getObject("admin_id_creador")
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace(); //lo mismo que en trabajadorDAO
        }

        return null;
    }

    // Marcar tarea completada
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
}
