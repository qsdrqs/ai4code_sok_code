import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentDAO {

    // --- Database Credentials ---
    // It's better practice to store these in a configuration file.
    private static final String DB_URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String USER = "yourusername";
    private static final String PASSWORD = "yourpassword";

    /**
     * Connects to the database and adds a new student to the STUDENTS table.
     *
     * @param name The name of the student.
     * @param age The age of the student.
     */
    public void addStudent(String name, int age) {
        // The SQL query with placeholders (?) for parameters.
        // This prevents SQL injection attacks.
        String sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";

        // Using try-with-resources to ensure the connection and statement are
        // automatically closed, even if an error occurs.
        try (Connection con = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            System.out.println("Connected to the database successfully!");

            // Bind the parameters to the PreparedStatement
            pstmt.setString(1, name); // Sets the first '?' to the student's name
            pstmt.setInt(2, age);     // Sets the second '?' to the student's age

            // Execute the query
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println(rowsAffected + " record inserted successfully.");
            } else {
                System.out.println("No records were inserted.");
            }

        } catch (SQLException e) {
            // Handle potential SQL errors
            e.printStackTrace();
        }
    }

    // --- Example of how to use the method ---
    public static void main(String[] args) {
        StudentDAO dao = new StudentDAO();
        dao.addStudent("Alice", 22);
        dao.addStudent("Bob", 25);
    }
}