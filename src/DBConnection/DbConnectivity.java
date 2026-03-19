package DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public interface DbConnectivity {
    String url= "jdbc:mysql://localhost:3306/job_portal";
    String username ="root";
    String userpassword = "Shahbaj@1225";

    public static Connection getConnection() throws SQLException{
        return  DriverManager.getConnection(url,username,userpassword);
    };
}
