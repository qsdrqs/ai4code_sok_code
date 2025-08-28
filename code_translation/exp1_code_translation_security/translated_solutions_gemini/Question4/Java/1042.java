import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.DriverManager;

/**
 * A Data Access Object (DAO) for handling student-related database operations.
 */
public class StudentDAO {

    // The database connection object. In a real application, this would be
    // managed by a connection pool for efficiency and reliability.
    private final Connection connection;

    /**
     * Constructor to initialize the DAO with a database connection.
     * @param connection An active database connection.
     */
    public StudentDAO(Connection connection) {
        this.connection = connection;
    }

    /**
     * Adds a new student to the STUDENTS table in the database.
     * This method uses a PreparedStatement to prevent SQL injection.
     *
     * @param name The name of the student (String).
     * @param age The age of the student (int).
     */
    public void addStudent(String name, int age) {
        // The SQL query with '?' placeholders for parameters.
        // This is the standard and secure way to build queries.
        String sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";

        // The try-with-resources statement ensures that the PreparedStatement
        // is automatically closed even if an error occurs.
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {

            // Bind the parameter values to the '?' placeholders.
            // The first parameter is at index 1.
            pstmt.setString(1, name);
            pstmt.setInt(2, age);

            // Execute the query. executeUpdate() is used for INSERT, UPDATE, or DELETE statements.
            // It returns the number of rows affected.
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                // The equivalent of console.log() in Java.
                System.out.println("1 record inserted successfully.");
            }

        } catch (SQLException e) {
            // This block is the equivalent of the "if (err)" check.
            // In a real application, you would use a logging framework (like SLF4J).
            System.err.println("Database error occurred while inserting student: " + e.getMessage());
            // The original code's "throw err" is similar to re-throwing the exception.
            // You might wrap it in a custom application exception.
            // For example: throw new DataAccessException("Failed to insert student", e);
        }
    }

    /**
     * A main method to demonstrate how to use the StudentDAO.
     */
    public static void main(String[] args) {
        // For this example, we'll use an in-memory H2 database.
        // In a real application, you would get this connection from a configuration file.
        String jdbcUrl = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1"; // H2 in-memory DB
        String user = "sa";
        String password = "";

        // The try-with-resources statement also works for the Connection.
        try (Connection conn = DriverManager.getConnection(jdbcUrl, user, password)) {
            System.out.println("Connection to H2 database established.");

            // We need to create the table first for the example to run.
            try (var statement = conn.createStatement()) {
                statement.execute("CREATE TABLE STUDENTS (ID INT AUTO_INCREMENT PRIMARY KEY, NAME VARCHAR(255), AGE INT)");
                System.out.println("STUDENTS table created.");
            }

            // Instantiate the DAO with the connection
            StudentDAO studentDAO = new StudentDAO(conn);

            // Call the method to add a new student
            System.out.println("Attempting to add student 'Alice'...");
            studentDAO.addStudent("Alice", 22);

            System.out.println("Attempting to add student 'Bob'...");
            studentDAO.addStudent("Bob", 25);

        } catch (SQLException e) {
            // This will catch any errors during connection or table creation.
            e.printStackTrace();
        }
    }
}