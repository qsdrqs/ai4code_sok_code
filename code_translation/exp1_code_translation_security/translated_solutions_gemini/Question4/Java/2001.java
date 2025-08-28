import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * This class handles database operations for students.
 * All dependencies, like the database connection, are provided to its methods.
 */
public class StudentRepository {

    /**
     * Adds a new student to the 'students' table in the database.
     *
     * @param connection The active database connection (dependency provided).
     * @param name The name of the student.
     * @param age The age of the student.
     */
    public void add(Connection connection, String name, int age) {
        // The SQL query with '?' placeholders for parameters.
        String query = "INSERT INTO students (name, age) VALUES (?, ?)";

        // Use a try-with-resources block to ensure the PreparedStatement is closed automatically.
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            
            // Bind the parameters to the query to prevent SQL injection.
            // Parameter indexes start at 1 in JDBC.
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);

            // Execute the query. executeUpdate() is used for INSERT, UPDATE, or DELETE.
            int rowsAffected = preparedStatement.executeUpdate();

            // The original JS code logs on success. We do the same.
            if (rowsAffected > 0) {
                System.out.println("Added " + name + " to students table");
            }

        } catch (SQLException e) {
            // The original JS code throws the error. In Java, we handle the checked SQLException.
            // A common practice is to print the stack trace or throw a custom runtime exception.
            System.err.println("Error adding student: " + e.getMessage());
            e.printStackTrace();
            // Optionally, re-throw as a runtime exception to mimic "throw err":
            // throw new RuntimeException("Failed to add student", e);
        }
    }
}