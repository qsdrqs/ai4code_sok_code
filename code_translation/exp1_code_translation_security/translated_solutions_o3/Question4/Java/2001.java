import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentDao {

    private final Connection connection;   // assume this is supplied elsewhere

    public StudentDao(Connection connection) {
        this.connection = connection;
    }

    /**
     * Adds a student’s name and age to the students table.
     */
    public void add(String name, int age) {
        String sql = "INSERT INTO students (name, age) VALUES (?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setInt(2, age);
            stmt.executeUpdate();

            System.out.println("Added " + name + " to students table");
        } catch (SQLException e) {
            throw new RuntimeException("Error while inserting student record", e);
        }
    }
}