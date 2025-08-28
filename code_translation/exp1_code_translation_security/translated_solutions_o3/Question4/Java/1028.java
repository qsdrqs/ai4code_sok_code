import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentDao {

    private final Connection connection;

    // The Connection is injected / supplied elsewhere in your project
    public StudentDao(Connection connection) {
        this.connection = connection;
    }

    /**
     * Inserts a new student record into the STUDENTS table.
     *
     * @param name The student’s name.
     * @param age  The student’s age.
     * @throws RuntimeException if the insert fails.
     */
    public void addStudent(String name, int age) {
        String sql = "INSERT INTO STUDENTS (name, age) VALUES (?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setInt(2, age);

            stmt.executeUpdate();
            System.out.println("1 record inserted");
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting student record", e);
        }
    }
}