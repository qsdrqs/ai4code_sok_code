import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * This class demonstrates how to connect to a SQL database and insert data
 * using JDBC. It is the Java equivalent of the provided JavaScript function's
 * intended database operation.
 */
public class StudentRepository {

    // --- DEPENDENCIES / CONFIGURATION ---
    // Replace these with your actual database connection details.
    // Example for MySQL: "jdbc:mysql://localhost:3306/your_database_name"
    // Example for PostgreSQL: "jdbc:postgresql://localhost:5432/your_database_name"
    private static final String DB_URL = "jdbc:your_database_type://your_host:port/your_database";
    private static final String USER = "your_username";
    private static final String PASS = "your_password";

    /**
     * Inserts a new student into the "STUDENTS" table.
     *
     * @param name The name of the student.
     * @param age The age of the student.
     */
    public static void insertStudent(String name, int age) {
        // The SQL INSERT statement. Using "?" placeholders is a security best practice
        // to prevent SQL injection attacks.
        String sql = "INSERT INTO STUDENTS(NAME, AGE) VALUES(?, ?)";

        // The try-with-resources statement ensures that the connection and statement
        // are automatically closed, even if an error occurs.
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Bind the input parameters to the "?" placeholders in the SQL query.
            // Parameter index starts from 1.
            pstmt.setString(1, name);
            pstmt.setInt(2, age);

            // Execute the query. executeUpdate() is used for INSERT, UPDATE, or DELETE.
            // It returns the number of rows affected.
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("A new student was inserted successfully!");
                System.out.println("Name: " + name + ", Age: " + age);
            }

        } catch (SQLException e) {
            // Handle potential database errors
            System.err.println("Database insertion failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * The main method is the entry point of the program, used here for demonstration.
     */
    public static void main(String[] args) {
        // This call mirrors the example call in the JavaScript code.
        insertStudent("XXXXX", 0);
        
        // Another example
        insertStudent("Jane Doe", 22);
    }
}