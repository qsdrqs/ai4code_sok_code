import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentUtil {

    /**
     * Adds a student to the STUDENTS table in the database.
     * 
     * @param db   The database connection.
     * @param name The student's name.
     * @param age  The student's age.
     */
    public static void addStudent(Connection db, String name, int age) {
        String sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";
        
        try (PreparedStatement pstmt = db.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("inserted " + e.getMessage());
        }
    }
}