import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {

    // Database connection properties
    private static final String DB_URL = "jdbc:mysql://localhost:3306/your_database";
    private static final String DB_USER = "your_username";
    private static final String DB_PASSWORD = "your_password";

    public static void main(String[] args) {
        addStudent("John Doe", 20);
    }

    /**
     * Adds a student to the database.
     *
     * @param name The student's name.
     * @param age  The student's age.
     */
    public static void addStudent(String name, int age) {
        String query = "INSERT INTO STUDENTS (name, age) VALUES (?, ?)";

        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error adding student: " + e.getMessage());
        }
    }
}