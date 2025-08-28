import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseExample {

    // Method to insert data into the STUDENTS table
    public static void insertInto(String name, int age) {
        // Database URL, username, and password
        String url = "jdbc:mysql://localhost:3306/your_database_name"; // Replace with your database URL
        String user = "your_username"; // Replace with your database username
        String password = "your_password"; // Replace with your database password

        // SQL query to insert data
        String sql = "INSERT INTO STUDENTS(NAME, AGE) VALUES (?, ?)";

        // Establishing a connection and executing the query
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            // Setting the parameters for the prepared statement
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);

            // Executing the query
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Example usage
        insertInto("John Doe", 25);
    }
}