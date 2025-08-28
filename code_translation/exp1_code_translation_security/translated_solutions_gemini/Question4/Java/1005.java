import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * This class handles database operations for students.
 * It encapsulates the connection details and provides methods to interact with the database.
 */
public class StudentRepository {

    // --- Database Connection Details ---
    // These would typically be loaded from a configuration file.
    private static final String DB_URL = "jdbc:mysql://localhost:3306/your_database_name";
    private static final String DB_USER = "your_username";
    private static final String DB_PASSWORD = "your_password";

    /**
     * Adds a new student to the STUDENTS table in the database.
     *
     * @param name The name of the student.
     * @param age The age of the student.
     */
    public void addStudent(String name, int age) {
        // The SQL query with placeholders (?) to prevent SQL injection.
        String sql = "INSERT INTO STUDENTS (name, age) VALUES (?, ?)";

        // Using a try-with-resources statement to ensure the connection and statement
        // are automatically closed, even if an error occurs.
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = con.prepareStatement(sql)) {

            // Set the values for the placeholders.
            // This is the safe way to insert data. The driver handles escaping.
            preparedStatement.setString(1, name); // Binds the 'name' to the first '?'
            preparedStatement.setInt(2, age);     // Binds the 'age' to the second '?'

            // Execute the query. executeUpdate() is used for INSERT, UPDATE, DELETE.
            int rowsAffected = preparedStatement.executeUpdate();

            System.out.println(rowsAffected + " student record(s) inserted successfully.");

        } catch (SQLException e) {
            // In a real application, you would use a proper logging framework.
            System.err.println("Error adding student to the database.");
            e.printStackTrace();
            // You might want to re-throw the exception or handle it as per your application's needs.
        }
    }

    // Example of how to use the method
    public static void main(String[] args) {
        StudentRepository repository = new StudentRepository();
        repository.addStudent("Alice", 22);
        repository.addStudent("Bob", 25);
    }
}