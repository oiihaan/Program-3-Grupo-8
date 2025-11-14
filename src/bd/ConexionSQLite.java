package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionSQLite {

	// esta funcion se puede hacer mas compleja añadiendo getDbPath() para que coja el archivo .db desde la ruta exacta del ordenador
	// de cada uno pero lo he visto innecesario ya que todos vamos a tener la misma estructura de proyecto y no debería haber problemas con esto.
	
	private static final String URL = "jdbc:sqlite:data/ficha_program3.db";
	
	// esto simplemente hace la conexion de nuestro .db con sqlite
	public static Connection getConnection() throws SQLException {
		try {
			Connection conn = DriverManager.getConnection(URL);
			try (Statement st = conn.createStatement()){
				st.execute("PRAGMA foreign_keys = ON");
			}
			return conn;
		}catch(Exception e) {
			System.out.println("ERROR: no ha sido posible conectar con la base de datos");
			return null;
		}	
	}
}
