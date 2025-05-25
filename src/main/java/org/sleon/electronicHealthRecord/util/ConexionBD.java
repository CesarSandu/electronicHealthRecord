package org.sleon.electronicHealthRecord.util;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static String url = "jdbc:mysql://root:GchveUYqdpnlgMpdDhcZNsatNGMHVYPM@switchyard.proxy.rlwy.net:41036/railway";
    private static String username = "root";
    private static String password = "GchveUYqdpnlgMpdDhcZNsatNGMHVYPM";

    public static Connection getConnection() throws SQLException, NamingException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver MySQL no encontrado", e);
        }
    }

    public static boolean testConnection() {
        try (Connection conn = getConnection()) {
            System.out.println("✅ Conexión exitosa a la base de datos");
            return conn != null && !conn.isClosed();
        } catch (Exception e) {
            System.err.println("❌ Error al conectar: " + e.getMessage());
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