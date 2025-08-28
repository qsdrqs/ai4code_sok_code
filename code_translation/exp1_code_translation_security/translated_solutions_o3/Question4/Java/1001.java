import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentRepository {

    // The connection is assumed to be supplied/injected elsewhere
    private final Connection connection;

    public StudentRepository(Connection connection) {
        this.connection = connection;
    }

    public void addSQLEntry(String name, int age) {
        String sql = "INSERT INTO students (name, age) VALUES (?, ?)";

        try (PreparedStatement stmt =
                     connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Bind parameters
            stmt.setString(1, name);
            stmt.setInt(2, age);

            // Execute the INSERT
            stmt.executeUpdate();

            // Retrieve the auto-generated key (id)
            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) {
                    long id = keys.getLong(1);
                    System.out.println("User added with id: " + id);
                }
            }

        } catch (SQLException e) {
            // Log/handle SQL error
            e.printStackTrace();
        }
    }
}