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
            System.out.println("   -> Base de datos limpiada");

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
            int juan    = insertTrabajador(conn, "Juan Perez", "pass1");
            int ana     = insertTrabajador(conn, "Ana Lopez", "pass2");
            int unai    = insertTrabajador(conn, "Unai", "uu");
            int laura   = insertTrabajador(conn, "Laura Martin", "laura");
            int iker    = insertTrabajador(conn, "Iker Ruiz", "iker");
          
            int marta   = insertTrabajador(conn, "Marta Gomez", "mg123");
            int asier   = insertTrabajador(conn, "Asier Garcia", "ag456");
            int eneko   = insertTrabajador(conn, "Eneko Gil", "eg789");
            int pablo   = insertTrabajador(conn, "Pablo Torres", "pt111");
            int lucia   = insertTrabajador(conn, "Lucia Santos", "ls222");
            int diego   = insertTrabajador(conn, "Diego Ramirez", "dr333");
            int sara    = insertTrabajador(conn, "Sara Blanco", "sb444");
            int david   = insertTrabajador(conn, "David Ortiz", "do555");
            int nerea   = insertTrabajador(conn, "Nerea Diaz", "nd666");
            int ruben   = insertTrabajador(conn, "Ruben Vidal", "rv777");
            int maria   = insertTrabajador(conn, "Maria Castro", "mc888");
            int jon     = insertTrabajador(conn, "Jon Aranburu", "ja999");
            int andrea  = insertTrabajador(conn, "Andrea Ruiz", "ar135");
            int carlos  = insertTrabajador(conn, "Carlos Peña", "cp246");
            int irene   = insertTrabajador(conn, "Irene Soto", "is357");
            int alvaro  = insertTrabajador(conn, "Alvaro Lozano", "al468");
            int esther  = insertTrabajador(conn, "Esther Cano", "ec579");
            int rocio   = insertTrabajador(conn, "Rocio Marin", "rm680");
            int sergio  = insertTrabajador(conn, "Sergio Vidal", "sv791");
            int noelia  = insertTrabajador(conn, "Noelia Rueda", "nr802");
            int oscar   = insertTrabajador(conn, "Oscar Prieto", "op913");
            int ainhoa  = insertTrabajador(conn, "Ainhoa Lasa", "al024");
            int javier  = insertTrabajador(conn, "Javier Soto", "js135");
            int patxi   = insertTrabajador(conn, "Patxi Etxeberria", "pe246");

            // 3) Tareas (nombre, duracion EN MINUTOS, estado)
            int t1  = insertTarea(conn, "Reparar impresora", 5, "finalizado");
            int t2  = insertTarea(conn, "Actualizar software", 8, "pendiente");
            int t3  = insertTarea(conn, "Montar estantería", 10, "pendiente");
            int t4  = insertTarea(conn, "Inventario almacén", 7, "pendiente");
            int t5  = insertTarea(conn, "Revisar seguridad", 4, "finalizado");
            int t6  = insertTarea(conn, "Soporte tickets", 3, "pendiente");
            int t7  = insertTarea(conn, "Limpieza zona A", 6, "pendiente");
            int t8  = insertTarea(conn, "Migración PC recepción", 1, "pendiente");
            int t9  = insertTarea(conn, "Cableado puesto 3", 9, "finalizado");
            int t10 = insertTarea(conn, "Formación onboarding", 4, "pendiente");
            int t11 = insertTarea(conn, "Revisión extintores", 6, "finalizado");
            int t12 = insertTarea(conn, "Backup servidores", 8, "finalizado");
            int t13 = insertTarea(conn, "Configurar correo corporativo", 3, "pendiente");
            int t14 = insertTarea(conn, "Alta usuario en sistema", 2, "pendiente");
            int t15 = insertTarea(conn, "Cambio teclado y ratón", 3, "finalizado");
            int t16 = insertTarea(conn, "Revisión cámaras CCTV", 7, "pendiente");
            int t17 = insertTarea(conn, "Instalar impresora red", 5, "pendiente");
            int t18 = insertTarea(conn, "Actualización antivirus", 4, "finalizado");
            int t19 = insertTarea(conn, "Revisión copias de seguridad", 6, "pendiente");
            int t20 = insertTarea(conn, "Soporte remoto a usuario", 2, "pendiente");
            int t21 = insertTarea(conn, "Configurar VPN", 5, "pendiente");
            int t22 = insertTarea(conn, "Crear grupo seguridad AD", 3, "pendiente");
            int t23 = insertTarea(conn, "Revisar impresoras planta 2", 6, "pendiente");
            int t24 = insertTarea(conn, "Desinstalar software obsoleto", 4, "finalizado");
            int t25 = insertTarea(conn, "Actualizar drivers gráficos", 3, "pendiente");
            int t26 = insertTarea(conn, "Configurar proyector sala", 2, "pendiente");
            int t27 = insertTarea(conn, "Revisión puntos de red", 7, "pendiente");
            int t28 = insertTarea(conn, "Crear informe incidencias", 5, "pendiente");
            int t29 = insertTarea(conn, "Soporte reunión online", 2, "pendiente");
            int t30 = insertTarea(conn, "Revisión UPS sala servidores", 6, "pendiente");
            int t31 = insertTarea(conn, "Preparar equipo nuevo", 8, "pendiente");
            int t32 = insertTarea(conn, "Clonar imagen disco", 9, "pendiente");
            int t33 = insertTarea(conn, "Testear copia de seguridad", 6, "pendiente");
            int t34 = insertTarea(conn, "Revisar licencias software", 4, "pendiente");
            int t35 = insertTarea(conn, "Configurar firma correo", 2, "pendiente");
            int t36 = insertTarea(conn, "Cambiar contraseña WiFi", 3, "pendiente");
            int t37 = insertTarea(conn, "Revisión logs seguridad", 7, "pendiente");
            int t38 = insertTarea(conn, "Configurar impresora WiFi", 5, "pendiente");
            int t39 = insertTarea(conn, "Crear cuentas invitados", 3, "pendiente");
            int t40 = insertTarea(conn, "Actualizar portal interno", 6, "pendiente");

            // 4) Asignaciones tarea_trabajador (muchas, para probar JList/relaciones)
            // Juan
            linkTareaTrabajador(conn, t1, juan);  linkTareaTrabajador(conn, t12, juan); linkTareaTrabajador(conn, t8, juan);  linkTareaTrabajador(conn, t18, juan); linkTareaTrabajador(conn, t21, juan); linkTareaTrabajador(conn, t31, juan);
            // Ana
            linkTareaTrabajador(conn, t2, ana);   linkTareaTrabajador(conn, t6, ana);  linkTareaTrabajador(conn, t13, ana); linkTareaTrabajador(conn, t19, ana); linkTareaTrabajador(conn, t28, ana); linkTareaTrabajador(conn, t34, ana);
            // Unai
            linkTareaTrabajador(conn, t3, unai);  linkTareaTrabajador(conn, t9, unai); linkTareaTrabajador(conn, t14, unai); linkTareaTrabajador(conn, t22, unai); linkTareaTrabajador(conn, t32, unai);
            // Laura
            linkTareaTrabajador(conn, t4, laura); linkTareaTrabajador(conn, t10, laura); linkTareaTrabajador(conn, t17, laura); linkTareaTrabajador(conn, t26, laura); linkTareaTrabajador(conn, t38, laura);
            // Iker
            linkTareaTrabajador(conn, t5, iker);  linkTareaTrabajador(conn, t11, iker); linkTareaTrabajador(conn, t16, iker); linkTareaTrabajador(conn, t27, iker); linkTareaTrabajador(conn, t37, iker);
            // Marta
            linkTareaTrabajador(conn, t7, marta); linkTareaTrabajador(conn, t6, marta); linkTareaTrabajador(conn, t15, marta); linkTareaTrabajador(conn, t23, marta); linkTareaTrabajador(conn, t33, marta);
            // Asier
            linkTareaTrabajador(conn, t8, asier); linkTareaTrabajador(conn, t12, asier); linkTareaTrabajador(conn, t19, asier); linkTareaTrabajador(conn, t24, asier); linkTareaTrabajador(conn, t30, asier);
            // Eneko
            linkTareaTrabajador(conn, t10, eneko); linkTareaTrabajador(conn, t2, eneko); linkTareaTrabajador(conn, t20, eneko); linkTareaTrabajador(conn, t29, eneko); linkTareaTrabajador(conn, t39, eneko);
            // Pablo
            linkTareaTrabajador(conn, t13, pablo); linkTareaTrabajador(conn, t17, pablo); linkTareaTrabajador(conn, t25, pablo); linkTareaTrabajador(conn, t31, pablo);
            // Lucia
            linkTareaTrabajador(conn, t14, lucia); linkTareaTrabajador(conn, t1, lucia); linkTareaTrabajador(conn, t21, lucia); linkTareaTrabajador(conn, t35, lucia);
            // Diego
            linkTareaTrabajador(conn, t15, diego); linkTareaTrabajador(conn, t9, diego); linkTareaTrabajador(conn, t27, diego); linkTareaTrabajador(conn, t36, diego);
            // Sara
            linkTareaTrabajador(conn, t16, sara); linkTareaTrabajador(conn, t3, sara); linkTareaTrabajador(conn, t23, sara); linkTareaTrabajador(conn, t33, sara);
            // David
            linkTareaTrabajador(conn, t18, david); linkTareaTrabajador(conn, t4, david); linkTareaTrabajador(conn, t30, david); linkTareaTrabajador(conn, t37, david);
            // Nerea
            linkTareaTrabajador(conn, t19, nerea); linkTareaTrabajador(conn, t5, nerea); linkTareaTrabajador(conn, t28, nerea); linkTareaTrabajador(conn, t34, nerea);
            // Ruben
            linkTareaTrabajador(conn, t20, ruben); linkTareaTrabajador(conn, t7, ruben); linkTareaTrabajador(conn, t26, ruben); linkTareaTrabajador(conn, t38, ruben);
            // Maria
            linkTareaTrabajador(conn, t11, maria); linkTareaTrabajador(conn, t2, maria); linkTareaTrabajador(conn, t29, maria); linkTareaTrabajador(conn, t39, maria);
            // Jon
            linkTareaTrabajador(conn, t21, jon); linkTareaTrabajador(conn, t25, jon); linkTareaTrabajador(conn, t30, jon); linkTareaTrabajador(conn, t40, jon);
            // Andrea
            linkTareaTrabajador(conn, t22, andrea); linkTareaTrabajador(conn, t18, andrea); linkTareaTrabajador(conn, t24, andrea); linkTareaTrabajador(conn, t32, andrea);
            // Carlos
            linkTareaTrabajador(conn, t23, carlos); linkTareaTrabajador(conn, t10, carlos); linkTareaTrabajador(conn, t27, carlos); linkTareaTrabajador(conn, t31, carlos);
            // Irene
            linkTareaTrabajador(conn, t24, irene); linkTareaTrabajador(conn, t6, irene); linkTareaTrabajador(conn, t28, irene); linkTareaTrabajador(conn, t35, irene);
            // Alvaro
            linkTareaTrabajador(conn, t25, alvaro); linkTareaTrabajador(conn, t13, alvaro); linkTareaTrabajador(conn, t33, alvaro); linkTareaTrabajador(conn, t36, alvaro);
            // Esther
            linkTareaTrabajador(conn, t26, esther); linkTareaTrabajador(conn, t17, esther); linkTareaTrabajador(conn, t32, esther); linkTareaTrabajador(conn, t38, esther);
            // Rocio
            linkTareaTrabajador(conn, t27, rocio); linkTareaTrabajador(conn, t4, rocio); linkTareaTrabajador(conn, t37, rocio); linkTareaTrabajador(conn, t40, rocio);
            // Sergio
            linkTareaTrabajador(conn, t28, sergio); linkTareaTrabajador(conn, t3, sergio); linkTareaTrabajador(conn, t39, sergio);
            // Noelia
            linkTareaTrabajador(conn, t29, noelia); linkTareaTrabajador(conn, t7, noelia); linkTareaTrabajador(conn, t34, noelia);
            // Oscar
            linkTareaTrabajador(conn, t30, oscar); linkTareaTrabajador(conn, t5, oscar); linkTareaTrabajador(conn, t31, oscar);
            // Ainhoa
            linkTareaTrabajador(conn, t32, ainhoa); linkTareaTrabajador(conn, t8, ainhoa); linkTareaTrabajador(conn, t35, ainhoa);
            // Javier
            linkTareaTrabajador(conn, t33, javier); linkTareaTrabajador(conn, t9, javier); linkTareaTrabajador(conn, t36, javier);
            // Patxi
            linkTareaTrabajador(conn, t34, patxi); linkTareaTrabajador(conn, t2, patxi); linkTareaTrabajador(conn, t37, patxi);

         // 5) Fichajes (muchos por cada trabajador)

            // Juan
            insertFichajeCerrado(conn, juan, "2025-12-01 08:55:00", "2025-12-01 17:05:00");
            insertFichajeCerrado(conn, juan, "2025-12-02 08:58:00", "2025-12-02 17:03:00");
            insertFichajeCerrado(conn, juan, "2025-12-03 09:10:00", "2025-12-03 16:55:00");
            insertFichajeCerrado(conn, juan, "2025-12-04 08:57:00", "2025-12-04 17:01:00");
            insertFichajeCerrado(conn, juan, "2025-12-05 09:02:00", "2025-12-05 17:06:00");
            insertFichajeCerrado(conn, juan, "2025-12-06 09:00:00", "2025-12-06 17:00:00");
            insertFichajeCerrado(conn, juan, "2025-12-07 08:59:00", "2025-12-07 17:02:00");

            // Ana
            insertFichajeCerrado(conn, ana, "2025-12-01 09:00:00", "2025-12-01 17:00:00");
            insertFichajeCerrado(conn, ana, "2025-12-02 09:05:00", "2025-12-02 17:00:00");
            insertFichajeCerrado(conn, ana, "2025-12-03 09:02:00", "2025-12-03 16:50:00");
            insertFichajeCerrado(conn, ana, "2025-12-04 09:01:00", "2025-12-04 16:59:00");
            insertFichajeCerrado(conn, ana, "2025-12-05 09:03:00", "2025-12-05 17:02:00");
            insertFichajeCerrado(conn, ana, "2025-12-06 09:01:00", "2025-12-06 16:58:00");
            insertFichajeCerrado(conn, ana, "2025-12-07 09:04:00", "2025-12-07 17:01:00");

            // Unai
            insertFichajeCerrado(conn, unai, "2025-12-01 08:00:00", "2025-12-01 15:30:00");
            insertFichajeCerrado(conn, unai, "2025-12-02 07:55:00", "2025-12-02 15:10:00");
            insertFichajeCerrado(conn, unai, "2025-12-03 07:55:00", "2025-12-03 15:30:00");
            insertFichajeCerrado(conn, unai, "2025-12-04 08:05:00", "2025-12-04 15:40:00");
            insertFichajeCerrado(conn, unai, "2025-12-05 08:10:00", "2025-12-05 15:35:00");
            insertFichajeCerrado(conn, unai, "2025-12-06 08:02:00", "2025-12-06 15:32:00");
            insertFichajeCerrado(conn, unai, "2025-12-07 08:07:00", "2025-12-07 15:37:00");

            // Laura
            insertFichajeCerrado(conn, laura, "2025-12-01 09:00:00", "2025-12-01 14:30:00");
            insertFichajeCerrado(conn, laura, "2025-12-02 09:00:00", "2025-12-02 14:30:00");
            insertFichajeCerrado(conn, laura, "2025-12-03 09:05:00", "2025-12-03 14:05:00");
            insertFichajeCerrado(conn, laura, "2025-12-04 09:00:00", "2025-12-04 14:00:00");
            insertFichajeCerrado(conn, laura, "2025-12-05 09:10:00", "2025-12-05 14:15:00");
            insertFichajeCerrado(conn, laura, "2025-12-06 09:05:00", "2025-12-06 14:10:00");
            insertFichajeCerrado(conn, laura, "2025-12-07 09:02:00", "2025-12-07 14:05:00");

            // Iker
            insertFichajeCerrado(conn, iker, "2025-12-01 10:00:00", "2025-12-01 18:00:00");
            insertFichajeCerrado(conn, iker, "2025-12-02 10:00:00", "2025-12-02 18:00:00");
            insertFichajeCerrado(conn, iker, "2025-12-03 10:05:00", "2025-12-03 18:05:00");
            insertFichajeCerrado(conn, iker, "2025-12-04 10:00:00", "2025-12-04 18:10:00");
            insertFichajeCerrado(conn, iker, "2025-12-05 10:02:00", "2025-12-05 18:03:00");
            insertFichajeCerrado(conn, iker, "2025-12-06 10:03:00", "2025-12-06 18:04:00");
            insertFichajeCerrado(conn, iker, "2025-12-07 10:01:00", "2025-12-07 18:02:00");

            // Marta
            insertFichajeCerrado(conn, marta, "2025-12-01 09:00:00", "2025-12-01 17:00:00");
            insertFichajeCerrado(conn, marta, "2025-12-02 09:05:00", "2025-12-02 17:05:00");
            insertFichajeCerrado(conn, marta, "2025-12-03 09:00:00", "2025-12-03 17:00:00");
            insertFichajeCerrado(conn, marta, "2025-12-04 09:10:00", "2025-12-04 17:05:00");
            insertFichajeCerrado(conn, marta, "2025-12-05 09:03:00", "2025-12-05 16:58:00");
            insertFichajeCerrado(conn, marta, "2025-12-06 09:02:00", "2025-12-06 17:01:00");
            insertFichajeCerrado(conn, marta, "2025-12-07 09:06:00", "2025-12-07 16:59:00");

            // Asier
            insertFichajeCerrado(conn, asier, "2025-12-01 08:25:00", "2025-12-01 16:30:00");
            insertFichajeCerrado(conn, asier, "2025-12-02 08:35:00", "2025-12-02 16:35:00");
            insertFichajeCerrado(conn, asier, "2025-12-03 08:25:00", "2025-12-03 16:30:00");
            insertFichajeCerrado(conn, asier, "2025-12-04 08:40:00", "2025-12-04 16:40:00");
            insertFichajeCerrado(conn, asier, "2025-12-05 08:30:00", "2025-12-05 16:45:00");
            insertFichajeCerrado(conn, asier, "2025-12-06 08:32:00", "2025-12-06 16:32:00");
            insertFichajeCerrado(conn, asier, "2025-12-07 08:38:00", "2025-12-07 16:36:00");

            // Eneko
            insertFichajeCerrado(conn, eneko, "2025-12-01 09:15:00", "2025-12-01 13:00:00");
            insertFichajeCerrado(conn, eneko, "2025-12-02 09:20:00", "2025-12-02 13:05:00");
            insertFichajeCerrado(conn, eneko, "2025-12-03 09:18:00", "2025-12-03 13:02:00");
            insertFichajeCerrado(conn, eneko, "2025-12-04 09:15:00", "2025-12-04 13:00:00");
            insertFichajeCerrado(conn, eneko, "2025-12-05 09:22:00", "2025-12-05 13:08:00");
            insertFichajeCerrado(conn, eneko, "2025-12-06 09:20:00", "2025-12-06 13:10:00");
            insertFichajeCerrado(conn, eneko, "2025-12-07 09:17:00", "2025-12-07 13:05:00");

            // Pablo
            insertFichajeCerrado(conn, pablo, "2025-12-01 08:50:00", "2025-12-01 17:00:00");
            insertFichajeCerrado(conn, pablo, "2025-12-02 08:55:00", "2025-12-02 17:05:00");
            insertFichajeCerrado(conn, pablo, "2025-12-03 08:52:00", "2025-12-03 17:03:00");
            insertFichajeCerrado(conn, pablo, "2025-12-04 08:53:00", "2025-12-04 17:04:00");
            insertFichajeCerrado(conn, pablo, "2025-12-05 08:57:00", "2025-12-05 17:02:00");

            // Lucia
            insertFichajeCerrado(conn, lucia, "2025-12-01 09:05:00", "2025-12-01 17:10:00");
            insertFichajeCerrado(conn, lucia, "2025-12-02 09:00:00", "2025-12-02 17:00:00");
            insertFichajeCerrado(conn, lucia, "2025-12-03 09:07:00", "2025-12-03 17:02:00");
            insertFichajeCerrado(conn, lucia, "2025-12-04 09:03:00", "2025-12-04 17:05:00");
            insertFichajeCerrado(conn, lucia, "2025-12-05 09:01:00", "2025-12-05 17:03:00");

            // Diego
            insertFichajeCerrado(conn, diego, "2025-12-01 07:55:00", "2025-12-01 16:00:00");
            insertFichajeCerrado(conn, diego, "2025-12-02 08:05:00", "2025-12-02 16:10:00");
            insertFichajeCerrado(conn, diego, "2025-12-03 08:00:00", "2025-12-03 16:05:00");
            insertFichajeCerrado(conn, diego, "2025-12-04 08:02:00", "2025-12-04 16:03:00");
            insertFichajeCerrado(conn, diego, "2025-12-05 08:07:00", "2025-12-05 16:06:00");

            // Sara
            insertFichajeCerrado(conn, sara, "2025-12-01 09:00:00", "2025-12-01 15:00:00");
            insertFichajeCerrado(conn, sara, "2025-12-02 09:10:00", "2025-12-02 15:05:00");
            insertFichajeCerrado(conn, sara, "2025-12-03 09:02:00", "2025-12-03 15:10:00");
            insertFichajeCerrado(conn, sara, "2025-12-04 09:04:00", "2025-12-04 15:08:00");
            insertFichajeCerrado(conn, sara, "2025-12-05 09:06:00", "2025-12-05 15:06:00");

            // David
            insertFichajeCerrado(conn, david, "2025-12-01 10:00:00", "2025-12-01 18:00:00");
            insertFichajeCerrado(conn, david, "2025-12-02 10:05:00", "2025-12-02 18:10:00");
            insertFichajeCerrado(conn, david, "2025-12-03 10:03:00", "2025-12-03 18:02:00");
            insertFichajeCerrado(conn, david, "2025-12-04 10:02:00", "2025-12-04 18:03:00");
            insertFichajeCerrado(conn, david, "2025-12-05 10:04:00", "2025-12-05 18:06:00");

            // Nerea
            insertFichajeCerrado(conn, nerea, "2025-12-01 09:00:00", "2025-12-01 17:00:00");
            insertFichajeCerrado(conn, nerea, "2025-12-02 09:02:00", "2025-12-02 16:55:00");
            insertFichajeCerrado(conn, nerea, "2025-12-03 09:04:00", "2025-12-03 17:03:00");
            insertFichajeCerrado(conn, nerea, "2025-12-04 09:03:00", "2025-12-04 17:01:00");
            insertFichajeCerrado(conn, nerea, "2025-12-05 09:05:00", "2025-12-05 17:04:00");

            // Ruben
            insertFichajeCerrado(conn, ruben, "2025-12-01 08:45:00", "2025-12-01 17:00:00");
            insertFichajeCerrado(conn, ruben, "2025-12-02 08:50:00", "2025-12-02 17:05:00");
            insertFichajeCerrado(conn, ruben, "2025-12-03 08:48:00", "2025-12-03 17:02:00");
            insertFichajeCerrado(conn, ruben, "2025-12-04 08:49:00", "2025-12-04 17:04:00");
            insertFichajeCerrado(conn, ruben, "2025-12-05 08:51:00", "2025-12-05 17:06:00");

            // Maria
            insertFichajeCerrado(conn, maria, "2025-12-01 09:00:00", "2025-12-01 17:00:00");
            insertFichajeCerrado(conn, maria, "2025-12-02 09:00:00", "2025-12-02 17:00:00");
            insertFichajeCerrado(conn, maria, "2025-12-03 09:01:00", "2025-12-03 16:59:00");
            insertFichajeCerrado(conn, maria, "2025-12-04 09:02:00", "2025-12-04 17:01:00");
            insertFichajeCerrado(conn, maria, "2025-12-05 09:04:00", "2025-12-05 16:59:00");

            // Jon
            insertFichajeCerrado(conn, jon, "2025-12-01 08:55:00", "2025-12-01 17:05:00");
            insertFichajeCerrado(conn, jon, "2025-12-02 08:57:00", "2025-12-02 17:02:00");
            insertFichajeCerrado(conn, jon, "2025-12-03 08:56:00", "2025-12-03 17:03:00");
            insertFichajeCerrado(conn, jon, "2025-12-04 08:58:00", "2025-12-04 17:01:00");

            // Andrea
            insertFichajeCerrado(conn, andrea, "2025-12-01 09:05:00", "2025-12-01 17:10:00");
            insertFichajeCerrado(conn, andrea, "2025-12-02 09:03:00", "2025-12-02 17:01:00");
            insertFichajeCerrado(conn, andrea, "2025-12-03 09:01:00", "2025-12-03 17:02:00");
            insertFichajeCerrado(conn, andrea, "2025-12-04 09:02:00", "2025-12-04 17:04:00");

            // Carlos
            insertFichajeCerrado(conn, carlos, "2025-12-01 08:50:00", "2025-12-01 17:00:00");
            insertFichajeCerrado(conn, carlos, "2025-12-02 08:52:00", "2025-12-02 17:03:00");
            insertFichajeCerrado(conn, carlos, "2025-12-03 08:54:00", "2025-12-03 17:01:00");
            insertFichajeCerrado(conn, carlos, "2025-12-04 08:55:00", "2025-12-04 17:02:00");
            insertFichajeCerrado(conn, carlos, "2025-12-05 08:56:00", "2025-12-05 17:04:00");

            // Irene
            insertFichajeCerrado(conn, irene, "2025-12-01 09:00:00", "2025-12-01 16:30:00");
            insertFichajeCerrado(conn, irene, "2025-12-02 09:05:00", "2025-12-02 16:40:00");
            insertFichajeCerrado(conn, irene, "2025-12-03 09:03:00", "2025-12-03 16:38:00");
            insertFichajeCerrado(conn, irene, "2025-12-04 09:04:00", "2025-12-04 16:42:00");

            // Alvaro
            insertFichajeCerrado(conn, alvaro, "2025-12-01 10:00:00", "2025-12-01 18:00:00");
            insertFichajeCerrado(conn, alvaro, "2025-12-02 10:02:00", "2025-12-02 18:05:00");
            insertFichajeCerrado(conn, alvaro, "2025-12-03 10:01:00", "2025-12-03 18:02:00");
            insertFichajeCerrado(conn, alvaro, "2025-12-04 10:03:00", "2025-12-04 18:04:00");

            // Esther
            insertFichajeCerrado(conn, esther, "2025-12-01 09:00:00", "2025-12-01 15:00:00");
            insertFichajeCerrado(conn, esther, "2025-12-02 09:10:00", "2025-12-02 15:10:00");
            insertFichajeCerrado(conn, esther, "2025-12-03 09:02:00", "2025-12-03 15:05:00");
            insertFichajeCerrado(conn, esther, "2025-12-04 09:06:00", "2025-12-04 15:08:00");

            // Rocio
            insertFichajeCerrado(conn, rocio, "2025-12-01 08:45:00", "2025-12-01 16:45:00");
            insertFichajeCerrado(conn, rocio, "2025-12-02 08:50:00", "2025-12-02 16:50:00");
            insertFichajeCerrado(conn, rocio, "2025-12-03 08:47:00", "2025-12-03 16:48:00");
            insertFichajeCerrado(conn, rocio, "2025-12-04 08:49:00", "2025-12-04 16:47:00");
            insertFichajeCerrado(conn, rocio, "2025-12-05 08:52:00", "2025-12-05 16:52:00");

            // Sergio
            insertFichajeCerrado(conn, sergio, "2025-12-01 09:00:00", "2025-12-01 17:00:00");
            insertFichajeCerrado(conn, sergio, "2025-12-02 09:05:00", "2025-12-02 17:05:00");
            insertFichajeCerrado(conn, sergio, "2025-12-03 09:02:00", "2025-12-03 17:02:00");
            insertFichajeCerrado(conn, sergio, "2025-12-04 09:04:00", "2025-12-04 17:03:00");

            // Noelia
            insertFichajeCerrado(conn, noelia, "2025-12-01 09:00:00", "2025-12-01 16:55:00");
            insertFichajeCerrado(conn, noelia, "2025-12-02 09:03:00", "2025-12-02 16:58:00");
            insertFichajeCerrado(conn, noelia, "2025-12-03 09:04:00", "2025-12-03 16:57:00");
            insertFichajeCerrado(conn, noelia, "2025-12-04 09:05:00", "2025-12-04 16:59:00");

            // Oscar
            insertFichajeCerrado(conn, oscar, "2025-12-01 08:55:00", "2025-12-01 17:05:00");
            insertFichajeCerrado(conn, oscar, "2025-12-02 08:56:00", "2025-12-02 17:04:00");
            insertFichajeCerrado(conn, oscar, "2025-12-03 08:57:00", "2025-12-03 17:03:00");
            insertFichajeCerrado(conn, oscar, "2025-12-04 08:58:00", "2025-12-04 17:02:00");

            // Ainhoa
            insertFichajeCerrado(conn, ainhoa, "2025-12-01 09:00:00", "2025-12-01 15:30:00");
            insertFichajeCerrado(conn, ainhoa, "2025-12-02 09:02:00", "2025-12-02 15:35:00");
            insertFichajeCerrado(conn, ainhoa, "2025-12-03 09:03:00", "2025-12-03 15:32:00");
            insertFichajeCerrado(conn, ainhoa, "2025-12-04 09:05:00", "2025-12-04 15:37:00");

            // Javier
            insertFichajeCerrado(conn, javier, "2025-12-01 08:50:00", "2025-12-01 17:00:00");
            insertFichajeCerrado(conn, javier, "2025-12-02 08:53:00", "2025-12-02 17:02:00");
            insertFichajeCerrado(conn, javier, "2025-12-03 08:55:00", "2025-12-03 17:01:00");
            insertFichajeCerrado(conn, javier, "2025-12-04 08:57:00", "2025-12-04 17:03:00");

            // Patxi
            insertFichajeCerrado(conn, patxi, "2025-12-01 09:00:00", "2025-12-01 17:00:00");
            insertFichajeCerrado(conn, patxi, "2025-12-02 09:05:00", "2025-12-02 17:05:00");
            insertFichajeCerrado(conn, patxi, "2025-12-03 09:02:00", "2025-12-03 17:01:00");
            insertFichajeCerrado(conn, patxi, "2025-12-04 09:06:00", "2025-12-04 17:06:00");

            // Abiertos (para probar “salida NULL”)
            insertFichajeAbierto(conn, ana,   "2025-12-14 08:59:00");
            insertFichajeAbierto(conn, unai,  "2025-12-14 09:12:00");
            insertFichajeAbierto(conn, marta, "2025-12-14 09:25:00");
            insertFichajeAbierto(conn, juan,  "2025-12-14 09:03:00");
            insertFichajeAbierto(conn, laura, "2025-12-14 09:18:00");
            insertFichajeAbierto(conn, iker,  "2025-12-14 10:05:00");
            insertFichajeAbierto(conn, lucia, "2025-12-14 09:07:00");
            insertFichajeAbierto(conn, diego, "2025-12-14 08:10:00");
            insertFichajeAbierto(conn, sara,  "2025-12-14 09:11:00");
            insertFichajeAbierto(conn, david, "2025-12-14 10:01:00");
            insertFichajeAbierto(conn, esther,"2025-12-14 09:09:00");
            insertFichajeAbierto(conn, rocio, "2025-12-14 08:51:00");
            insertFichajeAbierto(conn, sergio,"2025-12-14 09:05:00");
            insertFichajeAbierto(conn, noelia,"2025-12-14 09:10:00");
            insertFichajeAbierto(conn, oscar, "2025-12-14 08:57:00");
            insertFichajeAbierto(conn, ainhoa,"2025-12-14 09:03:30");
            insertFichajeAbierto(conn, javier,"2025-12-14 08:59:30");
            insertFichajeAbierto(conn, patxi, "2025-12-14 09:12:30");

            

            conn.commit(); //Confirma todos los cambios
            System.out.println("   -> Datos insertados correctamente");

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
        
        public static void imprimirLogoAnimado() {


                String[] logo = {
                	"---------------------------------------------------",
                    "  .  . .  .  ..;S8SSX@8@@@XS88t;..  .  .  . .  .  .",
                    "   .      .;8@8@888@88@@@8@S@@S88@X;.  .       :;.",
                    "     .  .:888X88XX8%8@88888X88@%8@8@8:  .  ..%@8X.",
                    " .     ;8@8@X8X8@;;  .@@@% .:t@8X8@8X88  tXX88X:  ",
                    "   . :8888@XX%:      .X8@:.   ..;t@X@S:t8%; 8t .  ",
                    "  . :888X@8.          .tSS   .     ....;8@88;.   .",
                    "   ;@888XS    . . .   .   .   . . .;%@88@%S@;.. . ",
                    "  t8@8S8..  .       .   .      .8@;:88St88@8S.    ",
                    " .X8888    .   .  .       . . :S;.%8@%:.@;8XS... .",
                    " S88S@S  .   .      . .     .X%@88tS8: .%X888S .  ",
                    ":X8@88         ;t88:    . .X@88 @8t:.  . .SX8X8: .",
                    "%@@@8S .  . . X%8tXXt    .;;:@8888:      ;X888%   ",
                    "8@8@X::::.    .t88 88%t @8:%8.8S8:  ..::::888@8   ",
                    "8@@;@SS8t% .  .@8@.88.@8;88t.SS;..   8%8888@888   ",
                    "8S88@t8X@%      .:t8:8:t@X.88@t   . . S88888@@X8  ",
                    "X8Xt ::  .      ::8:@@8888XX8t.  .        @@@@t   ",
                    ":888X8.   .     .:  @; 8 tS8t     ..     :@8@8%   ",
                    " 8%8;..          .  :  S8@% .  .   .     @8S8@    ",
                    " S8@8@8:.  .     .    8t@@   .   . .  . %8888X .  ",
                    " :@8@t88.         .    S%. .   .    .  888888:  . ",
                    "  :8@8.88.        .          .   . .  S88@@8;     ",
                    "  .%@88.8X: .      .  ::: .    .    ;S@8X@8.  . .",
                    "    ;8%8X@8Xt.     . .8SX. . .   :tS88@8Xt  .    ",
                    "  .  .:8;8;8t:%.   . .@88.     .%88@@888      . .",
                    "    . .:X.88@t@8S8:;:.@@X::;%8X88@@@8@S  .  .    ",
                    "  .   .  :S8t88%8%XX888888@X888@88S%;     .    . ",
                    "     .   .  :;8S888@@@888%888888@:   . .     .   ",
                    "   .       . . .%SS8@8X88@8%tS;   .      .  .   .",
                    "      . .      .               .    .  .      .  ",
                	"---------------------------------------------------\n"

                };

                for (String linea : logo) {
                    System.out.println(linea);
                    try {
						Thread.sleep(70);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} // milisegundos de pausa entre líneas
                }
            }
        

    
    // Handlers para que funcione el metodo principal_
    public static void main(String[] args) {
    	System.out.println("========================================");
        System.out.println("        INICIO DEL PROCESO");
        System.out.println("========================================\n");

        System.out.println("[1] Limpiando base de datos...\n");
        imprimirLogoAnimado();
        ejecutarLimpieza();

        System.out.println("\n[2] Insertando datos iniciales...\n");
        imprimirLogoAnimado();
        insertarDatosInicio(); 
        
        System.out.println("\n========================================");
        System.out.println("       PROCESO FINALIZADO");
        System.out.println("========================================");
    }
    
}