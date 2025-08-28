import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentDAO {

    /**
     * Inserts a new student into the STUDENTS table.
     *
     * @param conn  an active database connection
     * @param name  the name of the student (Unicode string)
     * @param age   the age of the student
     */
    public static void insertUser(Connection conn, String name, int age) {
        String sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            // Use setNString for NVARCHAR (Unicode) columns
            stmt.setNString(1, name);
            stmt.setInt(2, age);
            stmt.executeUpdate();
        } catch (SQLException e) {
            // Log the error, similar to console.log in JavaScript
            e.printStackTrace();
        }
    }
}