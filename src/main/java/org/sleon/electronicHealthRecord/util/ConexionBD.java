package org.sleon.electronicHealthRecord.util;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static DriverManager driverManager;
    private static String url= "jdbc:mysql://localhost:3306/electronichealthrecord?serverTimezone=America/Monterrey";
    private static String username = "cesar";
    private static String password = "141466Ce*";

    public static Connection getConnection() throws SQLException, NamingException {
        return DriverManager.getConnection(url, username, password);
    }
}