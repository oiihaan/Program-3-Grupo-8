package bd.reset;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LimpiadorBDD {

    private static final String DB_PATH = "jdbc:sqlite:data/ficha_program3.db";

    public static void ejecutarLimpieza() {
        
//String sql que borra todos los datos almacenados en las tablas
        String sqlScript = 
            "PRAGMA foreign_keys = OFF; " + //Desactivar llaves foráneas para que no de errores
            "DELETE FROM FICHAJE; " +           
            "DELETE FROM tarea_trabajador; " +  
            "DELETE FROM tarea; " +             
            "DELETE FROM trabajador; " +        
            "DELETE FROM admin; " +                  
            "DELETE FROM sqlite_sequence; " +   //Tabla interna de SQLite (pone a 0 los contadores de ID etc)
            
            "PRAGMA foreign_keys = ON;";    //Reactivar llaves foráneas

        try (Connection conn = DriverManager.getConnection(DB_PATH);
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sqlScript);
            System.out.println("Base de datos limpiada");

        } catch (SQLException e) {
            System.err.println("ERROR al limpiar la base de datos: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public static void insertarDatosInicio() {

        try (Connection conn = DriverManager.getConnection(DB_PATH)){
        		// Statement stmt = conn.createStatement() // Manera como tenia oihan por si queremos volver
        		

            conn.setAutoCommit(false); //Para que se metan todos de golpe o si falla algo no nos meta nada
            
            // 1) Admin
            insertAdmin(conn, "Julio", "123");
           

            // 2) Trabajadores (nombre + contrasenya)
            int juan = insertTrabajador(conn, "Juan Perez", "pass1");
            int ana  = insertTrabajador(conn, "Ana Lopez", "pass2");
            int unai = insertTrabajador(conn, "Unai", "uu");
            int laura = insertTrabajador(conn, "Laura Martin", "laura");
            int iker  = insertTrabajador(conn, "Iker Ruiz", "iker");
            int marta = insertTrabajador(conn, "Marta Gomez", "marta");
            int asier = insertTrabajador(conn, "Asier Garcia", "asier");
            int eneko = insertTrabajador(conn, "Eneko Gil", "aupa");

            // 3) Tareas (nombre, duracion, estado)
            int t1  = insertTarea(conn, "Reparar Impresora", 30,  "finalizado");
            int t2  = insertTarea(conn, "Actualizar Software", 60, "pendiente");
            int t3  = insertTarea(conn, "Montar Estantería", 120, "ejecutando");
            int t4  = insertTarea(conn, "Inventario almacén", 90, "pendiente");
            int t5  = insertTarea(conn, "Revisar seguridad", 45,  "finalizado");
            int t6  = insertTarea(conn, "Soporte tickets", 180, "ejecutando");
            int t7  = insertTarea(conn, "Limpieza zona A", 60,   "pendiente");
            int t8  = insertTarea(conn, "Migración PC recepción", 150, "pendiente");
            int t9  = insertTarea(conn, "Cableado puesto 3", 75,  "finalizado");
            int t10 = insertTarea(conn, "Formación onboarding", 240, "pendiente");
            int t11 = insertTarea(conn, "Revisión extintores", 35, "finalizado");
            int t12 = insertTarea(conn, "Backup servidores", 50,  "ejecutando");

            // 4) Asignaciones tarea_trabajador (muchas, para probar JList/relaciones)
            // Juan
            linkTareaTrabajador(conn, t1, juan);
            linkTareaTrabajador(conn, t12, juan);
            linkTareaTrabajador(conn, t8, juan);

            // Ana
            linkTareaTrabajador(conn, t2, ana);
            linkTareaTrabajador(conn, t6, ana);

            // Unai
            linkTareaTrabajador(conn, t3, unai);
            linkTareaTrabajador(conn, t9, unai);

            // Laura
            linkTareaTrabajador(conn, t4, laura);
            linkTareaTrabajador(conn, t10, laura);

            // Iker
            linkTareaTrabajador(conn, t5, iker);
            linkTareaTrabajador(conn, t11, iker);

            // Marta
            linkTareaTrabajador(conn, t7, marta);
            linkTareaTrabajador(conn, t6, marta);

            // Asier
            linkTareaTrabajador(conn, t8, asier);
            linkTareaTrabajador(conn, t12, asier);

            // Eneko
            linkTareaTrabajador(conn, t10, eneko);
            linkTareaTrabajador(conn, t2, eneko);

            // 5) Fichajes (muchos y variados)
            // Históricos cerrados (varios días)
            insertFichajeCerrado(conn, juan,  "2025-12-02 08:58:00", "2025-12-02 17:03:00");
            insertFichajeCerrado(conn, juan,  "2025-12-03 09:10:00", "2025-12-03 16:55:00");
            insertFichajeCerrado(conn, ana,   "2025-12-02 09:05:00", "2025-12-02 17:00:00");
            insertFichajeCerrado(conn, unai,  "2025-12-03 07:55:00", "2025-12-03 15:30:00");
            insertFichajeCerrado(conn, laura, "2025-12-04 09:00:00", "2025-12-04 14:00:00"); // turno corto
            insertFichajeCerrado(conn, iker,  "2025-12-04 10:00:00", "2025-12-04 18:10:00");
            insertFichajeCerrado(conn, marta, "2025-12-05 09:00:00", "2025-12-05 17:00:00");
            insertFichajeCerrado(conn, asier, "2025-12-05 08:30:00", "2025-12-05 16:45:00");
            insertFichajeCerrado(conn, eneko, "2025-12-06 09:15:00", "2025-12-06 12:30:00");

            // Abiertos (para probar “salida NULL”)
            insertFichajeAbierto(conn, ana,   "2025-12-14 08:59:00");
            insertFichajeAbierto(conn, unai,  "2025-12-14 09:12:00");
            insertFichajeAbierto(conn, marta, "2025-12-14 09:25:00");

            

            conn.commit(); //Confirma todos los cambios
            System.out.println("Datos insertados correctamente");

        } catch (SQLException e) {
            System.err.println("Error insertando datos: " + e.getMessage());
            e.printStackTrace();
            
        }
    }
    
    
        private static void insertAdmin(Connection conn, String nombre, String contrasenya) throws SQLException {
            String sql = "INSERT INTO admin (nombre, contrasenya) VALUES (?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, nombre);
                ps.setString(2, contrasenya);
                ps.executeUpdate();
            }
        }

        private static int insertTrabajador(Connection conn, String nombre, String contrasenya) throws SQLException {
            String sql = "INSERT INTO trabajador (nombre, contrasenya) VALUES (?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, nombre);
                ps.setString(2, contrasenya);
                ps.executeUpdate();
            }

            // Obtener último ID insertado de la manera de SQLITe,
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT last_insert_rowid()")) {

                if (rs.next()) {
                    return rs.getInt(1);
                }
            }

            throw new SQLException("No se pudo obtener el ID generado para trabajador: " + nombre);
        }


        private static int insertTarea(Connection conn, String nombre, int duracion, String estado) throws SQLException {
            String sql = "INSERT INTO tarea (nombre, duracion, estado) VALUES (?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, nombre);
                ps.setInt(2, duracion);
                ps.setString(3, estado);
                ps.executeUpdate();
            }

            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT last_insert_rowid()")) {

                if (rs.next()) {
                    return rs.getInt(1);
                }
            }

            throw new SQLException("No se pudo obtener el ID generado para tarea: " + nombre);
        }


        private static void linkTareaTrabajador(Connection conn, int idTarea, int idTrabajador) throws SQLException {
            String sql = "INSERT INTO tarea_trabajador (id_tarea, id_trabajador) VALUES (?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, idTarea);
                ps.setInt(2, idTrabajador);
                ps.executeUpdate();
            }
        }

        private static void insertFichajeCerrado(Connection conn, int idTrabajador, String entrada, String salida) throws SQLException {
            String sql = "INSERT INTO fichaje (id_trabajador, entrada, salida) VALUES (?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, idTrabajador);
                ps.setString(2, entrada);
                ps.setString(3, salida);
                ps.executeUpdate();
            }
        }

        private static void insertFichajeAbierto(Connection conn, int idTrabajador, String entrada) throws SQLException {
            String sql = "INSERT INTO fichaje (id_trabajador, entrada, salida) VALUES (?, ?, NULL)";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, idTrabajador);
                ps.setString(2, entrada);
                ps.executeUpdate();
            }
        }
    
    // Handlers para que funcione el metodo principal_
    public static void main(String[] args) {
        ejecutarLimpieza();
        insertarDatosInicio(); 
    }
}