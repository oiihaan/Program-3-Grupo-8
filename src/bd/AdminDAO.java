package bd;

import java.sql.*;

import domain.BDAdmin;
import domain.BDTrabajador;

public class AdminDAO {
	
	public static BDAdmin buscarPorNombre(String nombre) {
	    String sql = "SELECT id, nombre, contraseña FROM administrador WHERE nombre = ?";
	    try (Connection conn = ConexionSQLite.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {
	        ps.setString(1, nombre);
	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                return new BDAdmin(rs.getInt("id"),rs.getString("nombre"),
	                		rs.getString("contraseña"));
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	

    // INSERTAR administrador
    public static void insertar(BDAdmin a) {
    	// Comprobar si el nombre ya existe
	    if (buscarPorNombre(a.getNombre()) != null) {
	        System.out.println("ERROR: El admin con nombre '" + a.getNombre() + "' ya existe.");
	        return;
	    }
    	String sql = "INSERT INTO administrador(nombre, contraseña) VALUES (?, ?)";
        try (Connection conn = ConexionSQLite.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, a.getNombre());
            ps.setString(2, a.getContraseyna());
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
