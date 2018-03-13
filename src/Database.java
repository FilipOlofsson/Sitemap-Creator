import java.sql.*;

public class Database {

    private Connection connection;

    Database(String server, String username, String password, String database) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://"+server+"/"+database, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    /*
        Execute a query, the returned value is PROBABLY not interesting. It returns either the row count or nothing.
        Don't think it's required for anything for now.
     */
    int executeQuery(String query) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeUpdate(query);
    }

}
