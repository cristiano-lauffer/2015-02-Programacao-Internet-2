/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Cristiano
 */
public class ConnectionFactory {

    private static final String DRIVER = "org.postgresql.Driver";
    private static final String HOSTNAME = "localhost";
    private static final String PORT = "5432";
    private static final String DATABASE = "TimeClock";
    private static final String URL = "jdbc:postgresql://" + HOSTNAME + ":" + PORT + "/" + DATABASE;
    private static final String USER = "postgres";
    private static final String PASS = "!remember2014";

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);
        return (DriverManager.getConnection(URL, USER, PASS));
    }
}
