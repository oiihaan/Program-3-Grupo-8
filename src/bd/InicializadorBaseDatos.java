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

            // Al llamar a getConnection(), si no existe el .db se crea vacío

            if (!existiaAntes) {           // Solo la primera vez
            }


        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error inicializando la base de datos: " + e.getMessage());
        }
    }
}
