import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Helper-class that handles basic CRUD operations on the STUDENTS table.
 */
public final class StudentRepository {

    private final Connection connection;      // supplied from elsewhere

    public StudentRepository(Connection connection) {
        this.connection = connection;
    }

    /**
     * Adds a new entry to the SQL table STUDENTS.
     *
     * @param name the student's name
     * @param age  the student's age
     * @throws SQLException if the insert fails
     */
    public void add(String name, int age) throws SQLException {

        // Prepared-statement version (safer than string concatenation).
        final String sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";

        // Just to mimic the original console.log:
        System.out.println(
                "INSERT INTO STUDENTS (NAME, AGE) VALUES ('" + name + "', " + age + ")");

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setInt(2, age);

            ps.executeUpdate();
            System.out.println("1 record inserted");
        }
    }
}