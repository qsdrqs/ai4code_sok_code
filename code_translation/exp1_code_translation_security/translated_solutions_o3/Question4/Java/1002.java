import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public final class StudentRepository {

    /**
     * Inserts a user into the STUDENTS table.
     *
     * Assumes that the supplied {@code Connection} is already open and valid.
     *
     * @param conn open JDBC connection
     * @param name student name
     * @param age  student age
     */
    public static void insertUser(Connection conn, String name, int age) {
        final String sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);  // @NAME
            ps.setInt(2, age);      // @AGE
            ps.executeUpdate();
        } catch (SQLException e) {
            // Mimic `console.log(err);` from the original JavaScript
            e.printStackTrace();
        }
    }

    // prevent instantiation
    private StudentRepository() { }
}