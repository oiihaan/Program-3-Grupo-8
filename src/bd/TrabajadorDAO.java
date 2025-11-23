package bd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import domain.BDTrabajador;

public class TrabajadorDAO {

	public static BDTrabajador buscarPorNombre(String nombre) {
	    String sql = "SELECT id, nombre, contraseña FROM trabajador WHERE nombre = ?";
	    try (Connection conn = ConexionSQLite.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {
	        ps.setString(1, nombre);
	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                return new BDTrabajador(rs.getInt("id"),rs.getString("nombre"),
	                		rs.getString("contraseña"));
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}

    // INSERTAR un trabajador a la base de datos
	public static void insertarTrabajador(BDTrabajador t) {
	    // Comprobar si el nombre ya existe
	    if (buscarPorNombre(t.getNombre()) != null) {
	        System.out.println("ERROR: El trabajador con nombre '" + t.getNombre() + "' ya existe.");
	        return;
	    }
	    
	    String sql = "INSERT INTO trabajador(nombre, contraseña) VALUES (?, ?)";
	    try (Connection conn = ConexionSQLite.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {
	        ps.setString(1, t.getNombre());
	        ps.setString(2, t.getContraseyna());
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
    public static HashSet<BDTrabajador> getAllTrabajadores() {
    	HashSet<BDTrabajador> lista = new HashSet<>();
        String sql = "SELECT id, nombre, contraseña FROM trabajador";
        try (Connection conn = ConexionSQLite.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String contraseña = rs.getString("contraseña");
                BDTrabajador t = new BDTrabajador(id, nombre, contraseña);
                lista.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    }


