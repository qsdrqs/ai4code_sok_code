import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentRepository {

    // --- DB connection parameters ---
    private static final String URL      = "jdbc:mysql://localhost:3306/mydb";
    private static final String USERNAME = "yourusername";
    private static final String PASSWORD = "yourpassword";

    /**
     * Inserts one student row into the STUDENTS table.
     *
     * @param name the student's name
     * @param age  the student's age
     */
    public static void addStudent(String name, int age) {

        String sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";

        // Load the MySQL driver (optional for JDBC 4+, but kept for clarity)
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC driver not found", e);
        }

        // Try-with-resources closes the connection and statement automatically
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(sql)) {

            System.out.println("Connected!");

            ps.setString(1, name);  // bind parameters
            ps.setInt(2, age);

            ps.executeUpdate();     // perform the INSERT
            System.out.println("1 record inserted");

        } catch (SQLException ex) {
            throw new RuntimeException("Database operation failed", ex);
        }
    }

    // Simple test
    public static void main(String[] args) {
        addStudent("John Doe", 21);
    }
}