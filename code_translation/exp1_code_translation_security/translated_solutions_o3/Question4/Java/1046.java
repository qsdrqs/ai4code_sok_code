import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public final class StudentRepository {

    // Supply these from your own configuration mechanism.
    private static final String DB_URL      = "jdbc:mysql://localhost:3306/your_database";
    private static final String DB_USER     = "user";
    private static final String DB_PASSWORD = "password";

    /**
     * Inserts a row into the STUDENTS table.
     *
     * @param name student's name
     * @param age  student's age
     */
    public static void insertInto(String name, int age) {

        String sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, name);
            stmt.setInt(2, age);

            stmt.executeUpdate();      // run INSERT
        } catch (SQLException ex) {
            ex.printStackTrace();      // or use your logger of choice
        }
        // try-with-resources automatically closes stmt and connection
    }
}