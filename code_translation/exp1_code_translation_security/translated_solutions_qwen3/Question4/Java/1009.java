import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentDAO {

    // Database connection details
    private static final String URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String USER = "username";
    private static final String PASSWORD = "password";

    /**
     * Inserts a new student entry into the STUDENTS table.
     *
     * @param name the name of the student
     * @param age  the age of the student
     */
    public static void createEntry(String name, int age) {
        Connection con = null;
        try {
            // Establish a new connection
            con = DriverManager.getConnection(URL, USER, PASSWORD);

            // Prepare the SQL statement with placeholders
            String sql = "INSERT INTO STUDENTS (name, age) VALUES (?, ?)";
            PreparedStatement pstmt = con.prepareStatement(sql);

            // Set the parameters
            pstmt.setString(1, name);
            pstmt.setInt(2, age);

            // Execute the insert
            pstmt.executeUpdate();

        } catch (SQLException e) {
            // Throw a RuntimeException to mimic JavaScript's `throw err`
            throw new RuntimeException("Database error occurred", e);
        } finally {
            // Close the connection in the finally block
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    // Ignore any exceptions during closing
                }
            }
        }
    }
}