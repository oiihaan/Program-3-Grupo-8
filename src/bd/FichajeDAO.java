package bd;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import domain.BDFichaje;

public class FichajeDAO {

    // === FORMATO FECHA/HORA PARA LA BD ===
    private static final DateTimeFormatter FMT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private static String toDb(LocalDateTime t) {
        return t.format(FMT);
    }

    private static LocalDateTime fromDb(String s) {
        return LocalDateTime.parse(s, FMT);
    }

    // === == OBTENER CONEXIÓN ===
    private static Connection getConnection() throws SQLException {
        // IMPORTANTE: sustituye esto por el método que ya usáis
        // para conectaros a vuestra base de datos.
        // Ejemplo genérico:
        // return DriverManager.getConnection("jdbc:sqlite:ficha_program3.db");
        return ConexionSQLite.getConnection();
    }

    // ==============
    // 1) INSERTAR ENTRADA
    // ==============
    //Inserta una fila nueva en la tabla, con id_trabajador, entrada y salida = null
    public static void crearFichajeEntrada(int idTrabajador, LocalDateTime entrada)
            throws SQLException {

        String sql = "INSERT INTO FICHAJE (id_trabajador, entrada) VALUES (?, ?)";

        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, idTrabajador);
            pst.setString(2, toDb(entrada));
            pst.executeUpdate();
        }
    }

    // ==============
    // 2) CERRAR FICHAJE ABIERTO
    // ==============
    //Busca la fila de ese trabajador cuya salida esté vacía y le pone la hora salids
    public static boolean cerrarFichajeActual(int idTrabajador, LocalDateTime salida)
            throws SQLException {

        String sql = "UPDATE FICHAJE " +
                     "SET salida = ? " +
                     "WHERE id_trabajador = ? AND salida IS NULL";

        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, toDb(salida));
            pst.setInt(2, idTrabajador);

            int filas = pst.executeUpdate(); // si todo va bien, habrá 1 fila afectada
            return filas > 0; //true si realmente habúa algo abierto.
        }
    }

    // ==============
    // 3) OBTENER FICHAJE ABIERTO (si existe)
    // ==============
    //Devuelve el fichaje que está activo, entrada registrada y salida a null
    public static BDFichaje obtenerFichajeAbierto(int idTrabajador)
            throws SQLException {

        String sql = "SELECT id, id_trabajador, entrada, salida " +
                     "FROM FICHAJE " +
                     "WHERE id_trabajador = ? AND salida IS NULL " +
                     "ORDER BY entrada DESC " +
                     "LIMIT 1";

        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, idTrabajador);

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return mapFichaje(rs);
                } else {
                    return null; // no hay fichaje abierto
                }
            }
        }
    }

    // ==============
    // 4) OBTENER TODOS LOS FICHAJES DE UN TRABAJADOR
    // ==============
    // Devuelve toda la historia de fichajes de ese trabajador, para luego rellenar la JTable de Ver empleado
    public static List<BDFichaje> obtenerFichajesTrabajador(int idTrabajador)
            throws SQLException {

        String sql = "SELECT id, id_trabajador, entrada, salida " +
                     "FROM FICHAJE " +
                     "WHERE id_trabajador = ? " +
                     "ORDER BY entrada";

        List<BDFichaje> lista = new ArrayList<>();

        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, idTrabajador);

            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapFichaje(rs));
                }
            }
        }

        return lista;
    }
    


    public static ArrayList<LocalDate> getDiasMasDe8Horas(int idTrabajador) throws SQLException {

        ArrayList<LocalDate> dias = new ArrayList<>();

        String sql =
            "SELECT date(entrada) AS dia " +
            "FROM FICHAJE " +
            "WHERE id_trabajador = ? " +
            "  AND salida IS NOT NULL " +
            "GROUP BY date(entrada) " +
            "HAVING SUM( (strftime('%s', salida) - strftime('%s', entrada)) / 60.0 ) >= 480";

        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, idTrabajador);

            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    String diaStr = rs.getString("dia");  // "YYYY-MM-DD" desde SQLite [web:97][web:108]
                    LocalDate dia = LocalDate.parse(diaStr); // ISO-8601 yyyy-MM-dd [web:99]
                    dias.add(dia);
                }
            }
        }

        return dias;
    }




    // ==============
    // MÉTODO PRIVADO PARA MAPEAR RESULTSET -> BDFichaje
    // ==============
    private static BDFichaje mapFichaje(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        int idTrabajador = rs.getInt("id_trabajador");

        String entradaStr = rs.getString("entrada");
        LocalDateTime entrada = fromDb(entradaStr);

        String salidaStr = rs.getString("salida");
        LocalDateTime salida = null;
        if (salidaStr != null) {
            salida = fromDb(salidaStr);
        }

        return new BDFichaje(id, idTrabajador, entrada, salida);
    }
}

