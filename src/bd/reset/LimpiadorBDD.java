package bd.reset;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
            "DELETE FROM administrador; " +     
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

        try (Connection conn = DriverManager.getConnection(DB_PATH);
             Statement stmt = conn.createStatement()) {

            conn.setAutoCommit(false); //Para que se metan todos de golpe o si falla algo no nos meta nada
            
     //Trabajadores
            stmt.executeUpdate("INSERT INTO trabajador (nombre, contrasenya) VALUES ('Juan Perez', 'pass1')");
            stmt.executeUpdate("INSERT INTO trabajador (nombre, contrasenya) VALUES ('Ana Lopez', 'pass2')");
            stmt.executeUpdate("INSERT INTO trabajador (nombre, contrasenya) VALUES ('Unai', 'uu')");


      //Tareas    
            stmt.executeUpdate("INSERT INTO tarea (nombre, duracion, estado) VALUES ('Reparar Impresora', 30, 'finalizado')");
            stmt.executeUpdate("INSERT INTO tarea (nombre, duracion, estado) VALUES ('Actualizar Software', 60, 'pendiente')");
            stmt.executeUpdate("INSERT INTO tarea (nombre, duracion, estado) VALUES ('Montar Estantería', 120, 'ejecutando')");


       //Tarea_Trabajador 
            stmt.executeUpdate("INSERT INTO tarea_trabajador (id_tarea, id_trabajador) VALUES (1, 1)"); // Tarea 1 -> Juan

      //Fichaje
            
            //Fichaje cerrado (con entrada y salida)
            stmt.executeUpdate("INSERT INTO FICHAJE (id_trabajador, entrada, salida) " +
                               "VALUES (1, '2025-12-05 09:00:00', '2025-12-05 17:00:00')");
            
            //Fichaje abierto (trabajador actualmente trabajando) -> salida es NULL
            stmt.executeUpdate("INSERT INTO FICHAJE (id_trabajador, entrada, salida) " +
                               "VALUES (2, '2025-12-05 09:05:00', NULL)");


     //Admin
            stmt.executeUpdate("INSERT INTO admin (nombre, contrasenya) VALUES ('admin1', 'admin123')");


            conn.commit(); //Confirma todos los cambios
            System.out.println("Datos insertados correctamente");

        } catch (SQLException e) {
            System.err.println("Error insertando datos: " + e.getMessage());
            e.printStackTrace();
            
        }
    }
    
    
    public static void Main(String args) {
    	ejecutarLimpieza();
    	insertarDatosInicio();
    }
}