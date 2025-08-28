import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * A class to manage student data in a MySQL database.
 */
public class StudentManager {

    private final Connection connection;

    /**
     * Constructs a new StudentManager with the given database connection.
     *
     * @param connection the database connection to use
     */
    public StudentManager(Connection connection) {
        this.connection = connection;
    }

    /**
     * Adds a new student to the STUDENTS table.
     *
     * @param name the name of the student
     * @param age  the age of the student
     */
    public void addStudent(String name, int age) {
        String sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            // Ignoring the error as per the original JavaScript code's behavior
        }
    }
}