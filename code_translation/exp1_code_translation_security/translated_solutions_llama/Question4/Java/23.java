import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentInserter {

    // Database connection properties
    private static final String DB_URL = "jdbc:mysql://localhost:3306/your_database";
    private static final String DB_USERNAME = "your_username";
    private static final String DB_PASSWORD = "your_password";

    /**
     * Inserts a new student into the "STUDENTS" table.
     *
     * @param name the student's name
     * @param age  the student's age
     */
    public static void insertStudent(String name, int age) {
        // SQL query to insert a new student
        String sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.setInt(2, age);

            int rowsAffected = pstmt.executeUpdate();
            System.out.println("Inserted " + rowsAffected + " row(s)");

        } catch (SQLException e) {
            System.err.println("Error inserting student: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        insertStudent("XXXXX", 0);
    }
}