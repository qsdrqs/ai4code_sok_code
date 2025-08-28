import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentDatabase {

    // Method to insert a student into the database
    public static void insertStudent(String name, int age) {
        // Database connection details
        String url = "jdbc:mysql://localhost:3306/your_database_name"; // Replace with your database URL
        String user = "your_username"; // Replace with your database username
        String password = "your_password"; // Replace with your database password

        // SQL insert statement
        String sql = "INSERT INTO STUDENTS(title, completed) VALUES(?, ?)";

        // Establishing the connection and executing the query
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            // Setting the parameters for the prepared statement
            preparedStatement.setString(1, "Learn how to insert a new row");
            preparedStatement.setBoolean(2, true);

            // Executing the insert statement
            preparedStatement.executeUpdate();

            System.out.println("Student inserted successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Call the method to insert a student
        insertStudent("XXXXX", 0);
    }
}