import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * This class is responsible for database operations related to Students.
 * It requires a database connection to be instantiated.
 */
public class StudentRepository {

    // The connection object is a member of the class, equivalent to the
    // 'connection' variable in the JavaScript scope.
    private final Connection connection;

    /**
     * Constructs a StudentRepository with a given database connection.
     * @param connection An active connection to a SQL database.
     */
    public StudentRepository(Connection connection) {
        this.connection = connection;
    }

    /**
     * Translates the addStudent JavaScript function.
     * Inserts a new student record into the STUDENTS table.
     *
     * @param name The name of the student.
     * @param age The age of the student.
     */
    public void addStudent(String name, int age) {
        // The SQL query with '?' placeholders is the same.
        // This is a PreparedStatement, which prevents SQL injection.
        String sql = "INSERT INTO STUDENTS(NAME, AGE) VALUES(?, ?)";

        // In Java, database operations can throw a checked SQLException,
        // which must be handled. A try-with-resources block is the best
        // practice as it automatically closes the PreparedStatement.
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {

            // Bind the values to the '?' placeholders.
            // JDBC uses 1-based indexing for parameters.
            pstmt.setString(1, name);
            pstmt.setInt(2, age);

            // Execute the query. For INSERT, UPDATE, or DELETE, use executeUpdate().
            // The original JS code had an empty callback `() => {}`, meaning it
            // "fires and forgets". This synchronous call achieves the same result.
            pstmt.executeUpdate();

            System.out.println("Successfully added student: " + name);

        } catch (SQLException e) {
            // Handle potential database errors.
            System.err.println("Error adding student: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * A main method to demonstrate how to use the StudentRepository class.
     */
    public static void main(String[] args) {
        // Database connection details. Replace with your actual credentials.
        // For this example, we use an in-memory H2 database, but the logic
        // is the same for MySQL. Just change the URL and driver.
        String dbUrl = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1"; // In-memory DB
        String user = "sa";
        String password = "";

        // A try-with-resources block on the Connection ensures it is always closed.
        try (Connection conn = DriverManager.getConnection(dbUrl, user, password)) {
            
            // Optional: Create the table for this demonstration
            try (var statement = conn.createStatement()) {
                statement.execute("CREATE TABLE IF NOT EXISTS STUDENTS (ID INT AUTO_INCREMENT PRIMARY KEY, NAME VARCHAR(255), AGE INT)");
            }

            // 1. Instantiate the repository with the connection
            StudentRepository repository = new StudentRepository(conn);

            // 2. Call the method to add students
            repository.addStudent("Alice", 22);
            repository.addStudent("Bob", 25);

        } catch (SQLException e) {
            System.err.println("Database connection failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}