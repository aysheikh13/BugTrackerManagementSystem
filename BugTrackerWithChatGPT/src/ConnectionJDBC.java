import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.sql.SQLException;

public class ConnectionJDBC {
    // These are the variables necessary for logging into the database through the usage of the JDBC and importing its Connection properties
    private static final String userName = "Insert_Username";
    private static final String password = "Insert_Password";
    private static final String dbms = "Insert_DBMS";
    private static final String serverName = "Insert_Server_Name";
    private static final String portNumber = "Insert_Port_Number";
    private static final String databaseName = "Insert_Database_Name";

    // This method establishes the connection to the database and ouputs if there is a connection
    public static Connection getConnection() throws SQLException {

        Connection database;
        Properties connectionProps = new Properties();

        connectionProps.put("user", userName);
        connectionProps.put("password", password);

        database = DriverManager.getConnection("jdbc:" + dbms + "://" + serverName + ":" + portNumber + "/" + databaseName , connectionProps);
        System.out.println("Connected to database");

        return database;
    }
}
