import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentRepository {

    // Whatever configuration mechanism you use will provide these.
    // They’re hard-coded here only so the snippet compiles in isolation.
    private static final String JDBC_URL      = "jdbc:mysql://localhost:3306/your_db";
    private static final String JDBC_USER     = "your_user";
    private static final String JDBC_PASSWORD = "your_password";

    public static void insertStudent(String name, int age) {
        // 1. Obtain a connection
        try (Connection connection = DriverManager.getConnection(
                     JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             // 2. Prepare the INSERT statement
             PreparedStatement stmt = connection.prepareStatement(
                     "INSERT INTO STUDENTS(title, completed) VALUES (?, ?)")) {

            // 3. Bind the parameters
            stmt.setString(1, "Learn how to insert a new row"); // same literal value as JS version
            stmt.setBoolean(2, true);

            // 4. Execute the INSERT
            stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();                // handle/report the error as you see fit
        }
        // 5. Resources (Connection & PreparedStatement) are auto-closed by try-with-resources
    }

    public static void main(String[] args) {
        insertStudent("XXXXX", 0);
    }
}