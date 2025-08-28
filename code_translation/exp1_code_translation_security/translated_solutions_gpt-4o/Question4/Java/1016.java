import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentDatabase {

    // Method to add a student to the database
    public void addStudent(String name, int age) {
        // Database URL, username, and password
        String url = "jdbc:mysql://localhost:3306/yourDatabaseName"; // Replace with your database URL
        String user = "yourUsername"; // Replace with your database username
        String password = "yourPassword"; // Replace with your database password

        // SQL query to insert a new student
        String sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";

        // Establish a connection and execute the query
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            // Set the parameters for the prepared statement
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);

            // Execute the update
            preparedStatement.executeUpdate();
            System.out.println("Student added successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error adding student to the database.");
        }
    }

    public static void main(String[] args) {
        StudentDatabase db = new StudentDatabase();
        db.addStudent("John Doe", 20); // Example usage
    }
}