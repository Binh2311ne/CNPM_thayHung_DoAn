
package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {
    public static Connection getJDBCConnection() {
        Connection connection = null;
        System.out.println("************JDBC Start***********");

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            String user = "root";
            String pass = "lienminh113";
            String url = "jdbc:mysql://localhost:3306/unify?";


            connection = DriverManager.getConnection(url, user, pass);
            System.out.println("************JDBC thành công***********");

        } catch (ClassNotFoundException ex) {
            System.out.println("************MySQL JDBC Driver not found***********");
            ex.printStackTrace();
        } catch (SQLException ex) {
            System.out.println("************JDBC Failed***********");
            ex.printStackTrace();
        }

        return connection;
    }
}




