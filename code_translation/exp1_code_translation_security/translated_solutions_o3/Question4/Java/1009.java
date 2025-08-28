import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Inserts a new record into the STUDENTS table.
 * (Assume that `dataSource` or any other means of obtaining a JDBC
 *  Connection is configured elsewhere and supplied here.)
 */
public class StudentDao {

    private final javax.sql.DataSource dataSource;   // Provided elsewhere

    public StudentDao(javax.sql.DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void createEntry(String name, int age) {
        final String sql = "INSERT INTO STUDENTS (name, age) VALUES (?, ?)";

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setInt(2, age);
            ps.executeUpdate();           // execute the INSERT

        } catch (SQLException e) {
            // Re-throw or handle as appropriate for your project
            throw new RuntimeException("Failed to insert student.", e);
        }
    }
}