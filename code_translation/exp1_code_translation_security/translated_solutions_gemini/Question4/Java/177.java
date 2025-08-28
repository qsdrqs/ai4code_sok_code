import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * This class handles database operations for Students.
 * It uses JDBC, the standard Java API for database connectivity.
 */
public class StudentRepository {

    // The database connection string (URL).
    // This example uses H2, an in-memory/file-based database, for simplicity.
    // This would be changed for other databases like PostgreSQL, MySQL, etc.
    private static final String DATABASE_URL = "jdbc:h2:./studentdb"; // Creates a file named studentdb.mv.db
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "";

    /**
     * Inserts a new student into the STUDENTS table.
     *
     * @param name The name of the student.
     * @param age The age of the student.
     */
    public void addStudent(String name, int age) {
        // The SQL query with placeholders (?) to prevent SQL injection.
        String sql = "INSERT INTO STUDENTS(name, age) VALUES(?, ?)";

        // The try-with-resources statement ensures that each resource is closed
        // at the end of the statement, which is crucial for preventing resource leaks.
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            // Bind the parameter values to the placeholders in the query.
            preparedStatement.setString(1, name); // Binds name to the first '?'
            preparedStatement.setInt(2, age);     // Binds age to the second '?'

            // Execute the update. executeUpdate() is used for INSERT, UPDATE, or DELETE.
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " row(s) affected. Student '" + name + "' added.");

        } catch (SQLException e) {
            // Handle potential database errors.
            System.err.println("Database error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Example of how to use the method
    public static void main(String[] args) {
        // Before running this, you need to create the table.
        // See the "Prerequisites" section below.
        StudentRepository repository = new StudentRepository();
        repository.addStudent("Alice", 22);
        repository.addStudent("Bob", 25);
    }
}