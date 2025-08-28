import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Rough, line-for-line translation of the original JavaScript snippet.
 * (Driver JAR, URL, user and password are assumed to be supplied elsewhere.)
 */
public class StudentRepository {

    /* Change these to match your environment */
    private static final String JDBC_URL      = "jdbc:mysql://<host>:<port>/<database>";
    private static final String JDBC_USER     = "<user>";
    private static final String JDBC_PASSWORD = "<password>";

    /**
     * Equivalent of the JavaScript add_student(name, age) function.
     */
    public static void addStudent(String name, int age) throws SQLException {

        // SQL that mirrors:  INSERT INTO STUDENTS VALUES (${name}, ${age})
        // Using placeholders to avoid SQL-injection and handle quoting.
        final String sql = "INSERT INTO STUDENTS (name, age) VALUES (?, ?)";

        // DriverManager#getConnection implicitly "connects", so there is no
        // separate asynchronous callback like in Node.js; it is synchronous.
        try (Connection connection = DriverManager.getConnection(
                 JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, name);
            stmt.setInt   (2, age);

            stmt.executeUpdate();
        }
    }
}