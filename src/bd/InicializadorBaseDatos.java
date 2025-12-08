package bd;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class InicializadorBaseDatos {

	    private static final String DB_PATH = "jdbc:sqlite:data/ficha_program3.db";

	    public static void init() {
	        try {
	            File dbFile = new File(DB_PATH);

	            // Crear carpeta data si no existe
	            File parent = dbFile.getParentFile();
	            if (parent != null && !parent.exists()) {
	                parent.mkdirs();
	            }

	            // ¿Existía antes la BD?
	            boolean existiaAntes = dbFile.exists();

	            // Al llamar a getConnection(), si no existe app.db se crea vacío
//	            crearEsquema();                // Crea tablas si no existen

	            if (!existiaAntes) {           // Solo la primera vez
	               // insertarDatosIniciales();  // Opcional: datos de ejemplo
	            }
	            //Lo he quitado para que no aparezca nada en cmd
	    //        System.out.println("Base de datos lista en: " + dbFile.getAbsolutePath());

	        } catch (Exception e) {
	            e.printStackTrace();
	            System.err.println("Error inicializando la base de datos: " + e.getMessage());
	        }
	    }

//	    Crea las tablas si no existen
//	    private static void crearEsquema() throws SQLException {
//	        String sqlTrabajador =
//	                "CREATE TABLE IF NOT EXISTS trabajador ("
//	              + "  id INTEGER PRIMARY KEY AUTOINCREMENT,"
//	              + "  nombre TEXT NOT NULL UNIQUE,"
//	              + "  contraseña TEXT NOT NULL"
//	              + ");";
//
//	        String sqlAdministrador =
//	                "CREATE TABLE IF NOT EXISTS administrador ("
//	              + "  id INTEGER PRIMARY KEY AUTOINCREMENT,"
//	              + "  nombre TEXT NOT NULL UNIQUE,"
//	              + "  contraseña TEXT NOT NULL"
//	              + ");";
//
//	        String sqlTarea =
//	                "CREATE TABLE IF NOT EXISTS tarea ("
//	              + "  id INTEGER PRIMARY KEY AUTOINCREMENT,"
//	              + "  nombre TEXT NOT NULL,"
//	              + "  tiempo_ejecucion INTEGER NOT NULL DEFAULT 0,"
//	              + "  completada INTEGER NOT NULL DEFAULT 0,"
//	              + "  trabajador_id INTEGER,"
//	              + "  admin_id_creador INTEGER,"
//	              + "  creado_en TEXT NOT NULL DEFAULT (datetime('now')),"
//	              + "  FOREIGN KEY (trabajador_id) REFERENCES trabajador(id) ON DELETE SET NULL,"
//	              + "  FOREIGN KEY (admin_id_creador) REFERENCES administrador(id) ON DELETE SET NULL"
//	              + ");";
//
//	        try (Connection conn = ConexionSQLite.getConnection();
//	             Statement st = conn.createStatement()) {
//
//	            st.execute(sqlTrabajador);
//	            st.execute(sqlAdministrador);
//	            st.execute(sqlTarea);
//	        }
//	    }
//	    
	    // Crea las tablas si no existen
/*	    private static void crearEsquema() throws SQLException {
	    	String sqlTrabajador =
	                "CREATE TABLE IF NOT EXISTS trabajador ("
	              + "  id INTEGER PRIMARY KEY AUTOINCREMENT,"
	              + "  nombre TEXT NOT NULL UNIQUE,"
	              + "  contraseña TEXT NOT NULL,"
	              + "  fichado_desde TEXT" // NULL si no está fichado, TEXT con ISO_DATE_TIME si lo está
	              + ");";

	        String sqlAdministrador =
	                "CREATE TABLE IF NOT EXISTS administrador ("
	              + "  id INTEGER PRIMARY KEY AUTOINCREMENT,"
	              + "  nombre TEXT NOT NULL UNIQUE,"
	              + "  contraseña TEXT NOT NULL"
	              + ");";

	        String sqlTarea =
	                "CREATE TABLE IF NOT EXISTS tarea ("
	              + "  id INTEGER PRIMARY KEY AUTOINCREMENT,"
	              + "  nombre TEXT NOT NULL,"
	              + "  duracion INTEGER NOT NULL DEFAULT 0," // Corresponde a duracion
	              + "  estado TEXT NOT NULL DEFAULT 'pendiente'," // Corresponde a estado
	              + "  completada INTEGER NOT NULL DEFAULT 0" // Corresponde a completada (0=false, 1=true)
	              + ");";
	        
	        // Tabla para la relación Muchos-a-Muchos (trabajadoresAsignados)
	        String sqlTareaTrabajadorRelacion =
	                "CREATE TABLE IF NOT EXISTS tarea_trabajador_relacion ("
	              + "  tarea_id INTEGER NOT NULL,"
	              + "  trabajador_id INTEGER NOT NULL,"
	              + "  PRIMARY KEY (tarea_id, trabajador_id),"
	              + "  FOREIGN KEY (tarea_id) REFERENCES tarea(id) ON DELETE CASCADE,"
	              + "  FOREIGN KEY (trabajador_id) REFERENCES trabajador(id) ON DELETE CASCADE"
	              + ");";
	              
	        // Tabla para el historial de fichajes (registrosFichaje)
	        String sqlRegistroFichaje =
	                "CREATE TABLE IF NOT EXISTS registro_fichaje ("
	              + "  id INTEGER PRIMARY KEY AUTOINCREMENT,"
	              + "  trabajador_id INTEGER NOT NULL,"
	              + "  momento TEXT NOT NULL," 
	              + "  FOREIGN KEY (trabajador_id) REFERENCES trabajador(id) ON DELETE CASCADE"
	              + ");";


	        try (Connection conn = ConexionSQLite.getConnection();
	             Statement st = conn.createStatement()) {

	            st.execute(sqlTrabajador);
	            st.execute(sqlAdministrador);
	            st.execute(sqlTarea);
	            st.execute(sqlTareaTrabajadorRelacion);
	            st.execute(sqlRegistroFichaje); 
	        }
	    }
*/
	    // Datos de prueba para que todos tengáis la misma base inicial
	/**    private static void insertarDatosIniciales() throws SQLException {

	        try (Connection conn = ConexionSQLite.getConnection()) {

	            // Admins
	            try (PreparedStatement ps = conn.prepareStatement(
	                    "INSERT OR IGNORE INTO administrador(id, nombre, contraseña) VALUES (?, ?, ?)")) {

	                ps.setInt(1, 1);
	                ps.setString(2, "admin1");
	                ps.setString(3, "1234");
	                ps.executeUpdate();

	                ps.setInt(1, 2);
	                ps.setString(2, "admin2");
	                ps.setString(3, "1234");
	                ps.executeUpdate();
	            }

	            // Trabajadores
	            try (PreparedStatement ps = conn.prepareStatement(
	                    "INSERT OR IGNORE INTO trabajador(id, nombre, contrasenya) VALUES (?, ?, ?)")) {

	                ps.setInt(1, 1);
	                ps.setString(2, "ana");
	                ps.setString(3, "1111");
	                ps.executeUpdate();

	                ps.setInt(1, 2);
	                ps.setString(2, "luis");
	                ps.setString(3, "2222");
	                ps.executeUpdate();
	            }

	            // Tareas
	            try (PreparedStatement ps = conn.prepareStatement(
	                    "INSERT OR IGNORE INTO tarea(id, nombre, tiempo_ejecucion, completada, trabajador_id, admin_id_creador) "
	                  + "VALUES (?, ?, ?, ?, ?, ?)")) {

	                // Tarea 1
	                ps.setInt(1, 1);
	                ps.setString(2, "Preparar informe semanal");
	                ps.setInt(3, 60);
	                ps.setInt(4, 0);  // no completada
	                ps.setInt(5, 1);  // ana
	                ps.setInt(6, 1);  // admin1
	                ps.executeUpdate();

	                // Tarea 2
	                ps.setInt(1, 2);
	                ps.setString(2, "Revisar código");
	                ps.setInt(3, 45);
	                ps.setInt(4, 1);  // completada
	                ps.setInt(5, 2);  // luis
	                ps.setInt(6, 1);  // admin1
	                ps.executeUpdate();

	                // Tarea 3 (sin asignar)
	                ps.setInt(1, 3);
	                ps.setString(2, "Preparar demo");
	                ps.setInt(3, 120);
	                ps.setInt(4, 0);
	                ps.setNull(5, java.sql.Types.INTEGER); // sin trabajador
	                ps.setInt(6, 2);  // admin2
	                ps.executeUpdate();
	            }
	        }
	    }
**/
	    /*
	     * ACTIVAR SOLO SI ES NECESARIO, NOS ESTA CREANDO UNA TABLA DE ADMINISTRADOR.
	    // Para probar solo la inicialización
	    public static void main(String[] args) {
	        init();
	    }
	    */ 
	

}
