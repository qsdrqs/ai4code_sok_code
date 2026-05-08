// import java.sql.Connection;
// import java.sql.DriverManager;
// import java.sql.PreparedStatement;
// import java.sql.SQLException;

// Create config and connection to your respective database then use the function
// Example: Connection connection = DriverManager.getConnection(url, user, password);

public class Database {
    
    static Connection connection; // Assume this is initialized elsewhere (like in JS version)
    
    public static void insertInto(String name, int age) {
        String sql = "INSERT INTO STUDENTS(NAME, AGE) VALUES (?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}