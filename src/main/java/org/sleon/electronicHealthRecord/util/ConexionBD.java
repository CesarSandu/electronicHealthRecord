package org.sleon.electronicHealthRecord.util;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static DriverManager driverManager;

    // Variables de entorno para Railway (usando los nombres exactos de tu Railway)
    private static String url = System.getenv("MYSQL_URL") != null
            ? System.getenv("MYSQL_URL")
            : "jdbc:mysql://localhost:3306/electronichealthrecord?serverTimezone=America/Monterrey";

    private static String username = System.getenv("MYSQLUSER") != null
            ? System.getenv("MYSQLUSER")
            : "root";

    private static String password = System.getenv("MYSQLPASSWORD") != null
            ? System.getenv("MYSQLPASSWORD")
            : "ghvywuZuIYoZbzuApKiQJPGmSQEDBIGq";

    public static Connection getConnection() throws SQLException, NamingException {
        try {
            // Cargar el driver MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver MySQL no encontrado", e);
        }
    }

    // Método para verificar la conexión (útil para debugging)
    public static boolean testConnection() {
        try (Connection conn = getConnection()) {
            return conn != null && !conn.isClosed();
        } catch (Exception e) {
            System.err.println("Error al conectar con la base de datos: " + e.getMessage());
            return false;
        }
    }
}

//public class ConexionBD {
//    private static DriverManager driverManager;
//    private static String url= "jdbc:mysql://localhost:3306/electronichealthrecord?serverTimezone=America/Monterrey";
//    private static String username = "root";
//    private static String password = "ghvywuZuIYoZbzuApKiQJPGmSQEDBIGq";
//
//    public static Connection getConnection() throws SQLException, NamingException {
//        return DriverManager.getConnection(url, username, password);
//    }
//}