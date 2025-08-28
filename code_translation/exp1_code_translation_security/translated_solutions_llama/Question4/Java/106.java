import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Adds a new entry to the "STUDENTS" table in the given database.
 * 
 * @param name The name of the student.
 * @param age  The age of the student.
 * @param db   The database connection.
 */
public class StudentDAO {

    public void addEntry(String name, int age, Connection db) {
        String sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";

        try (PreparedStatement pstmt = db.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error adding entry: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Example usage:
        // Assume you have a Connection object 'db' established
        // StudentDAO studentDAO = new StudentDAO();
        // studentDAO.addEntry("John Doe", 20, db);
    }
}