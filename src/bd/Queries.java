package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.nio.file.*;

public class Queries {

    private Connection conn() throws SQLException {
        try {
            // 1️ Asegura que exista la carpeta "data"
            Path dataDir = Paths.get("data");
            Files.createDirectories(dataDir);

            // 2️ Ruta relativa al proyecto
            String dbPath = dataDir.resolve("app.db").toString();

            // 3️ Construye la URL JDBC (usa barras normales, siempre válidas)
            String url = "jdbc:sqlite:" + dbPath;

            // 4️ Devuelve la conexión
            Connection conn = DriverManager.getConnection(url);
            return conn;

        } catch (Exception e) {
            throw new SQLException("Error al conectar con la base de datos", e);
        }
    }
}
