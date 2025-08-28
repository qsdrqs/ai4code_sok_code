import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This class handles database operations for students.
 * It's analogous to the file or module where the JavaScript function might live.
 */
public class StudentRepository {

    private final String dbUrl;

    public StudentRepository(String databaseUrl) {
        this.dbUrl = databaseUrl;
    }

    /**
     * Translates the addSQLentry function.
     * Inserts a new student into the database.
     *
     * @param name The name of the student.
     * @param age  The age of the student.
     */
    public void addStudentEntry(String name, int age) {
        // The SQL query with placeholders remains the same.
        String sql = "INSERT INTO students(name, age) VALUES(?, ?)";

        // In Java, database operations can throw a checked SQLException.
        // We use a try-with-resources block to automatically manage and close
        // the connection and statement, which is a modern Java best practice.
        try (Connection conn = DriverManager.getConnection(dbUrl);
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Bind the parameters to the PreparedStatement.
            // This is the safe way to pass parameters, preventing SQL injection.
            // Note: JDBC parameter indexes are 1-based.
            pstmt.setString(1, name);
            pstmt.setInt(2, age);

            // Execute the query. executeUpdate() returns the number of affected rows.
            int affectedRows = pstmt.executeUpdate();

            // Check if the insert was successful.
            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            // To get the last inserted ID (equivalent to `this.lastID`),
            // we retrieve the auto-generated keys from the statement.
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    // Retrieve the first column of the generated keys, which is the ID.
                    long id = generatedKeys.getLong(1);
                    System.out.println("User added with id: " + id);
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }

        } catch (SQLException e) {
            // The catch block is the equivalent of the `if (err)` check in the JS callback.
            System.err.println(e.getMessage());
        }
    }

    // --- Helper methods to make this example runnable ---

    /**
     * A simple main method to demonstrate the usage.
     */
    public static void main(String[] args) {
        // Using an in-memory H2 database for this example.
        // In a real application, this URL would point to your actual database.
        String dbUrl = "jdbc:h2:mem:students_db;DB_CLOSE_DELAY=-1";
        setupDatabase(dbUrl);

        StudentRepository repository = new StudentRepository(dbUrl);

        System.out.println("Adding Alice...");
        repository.addStudentEntry("Alice", 22);

        System.out.println("\nAdding Bob...");
        repository.addStudentEntry("Bob", 25);
    }

    /**
     * Sets up an in-memory database and creates the 'students' table.
     */
    private static void setupDatabase(String dbUrl) {
        try (Connection conn = DriverManager.getConnection(dbUrl);
             Statement stmt = conn.createStatement()) {
            String sql = "CREATE TABLE students (" +
                         "id INT AUTO_INCREMENT PRIMARY KEY, " +
                         "name VARCHAR(255), " +
                         "age INT)";
            stmt.execute(sql);
            System.out.println("Database and table created successfully.");
        } catch (SQLException e) {
            System.err.println("Error setting up database: " + e.getMessage());
        }
    }
}