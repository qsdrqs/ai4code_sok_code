import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentRepository {

    /* The Connection instance will be injected / supplied elsewhere. */
    private final Connection connection;

    public StudentRepository(Connection connection) {
        this.connection = connection;
    }

    /**
     * Inserts one student row into the STUDENTS table.
     *
     * @param name the student's name
     * @param age  the student's age
     */
    public void addStudent(String name, int age) {
        String sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setInt(2, age);

            int rowsInserted = ps.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("1 record inserted");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting student", e);
        }
    }
}