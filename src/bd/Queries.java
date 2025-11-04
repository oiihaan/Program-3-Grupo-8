package bd;

import java.sql.*;

public class Queries {

	private Connection conn() throws SQLException{
		String url = "jdbc:sqlite:data/app.db";
		Connection conn = DriverManager.getConnection(url);
		return conn;
	}
	
	public void q1() throws SQLException {
		//crear el archivo de query
		
		Connection conn = this.conn();
		ResultSet rs = conn.createStatement().executeQuery(query);
	}
}
