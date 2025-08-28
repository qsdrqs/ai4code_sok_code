import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentDatabase {

    // Method to add a student to the database
    public static void addStudent(Connection db, String name, int age) {
        String sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";

        try (PreparedStatement pstmt = db.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.executeUpdate();
            System.out.println("Inserted successfully");
        } catch (SQLException e) {
            System.out.println("Insertion error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Example usage
        String url = "jdbc:sqlite:path_to_your_database.db"; // Update with your database path

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                addStudent(conn, "John Doe", 20);
            }
        } catch (SQLException e) {
            System.out.println("Connection error: " + e.getMessage());
        }
    }
}