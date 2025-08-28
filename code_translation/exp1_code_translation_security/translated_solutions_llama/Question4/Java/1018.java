import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {

    // Database connection properties
    private static final String DB_URL = "jdbc:mysql://localhost:3306/your_database";
    private static final String USER = "your_username";
    private static final String PASS = "your_password";

    public static void add(String name, int age) {
        // SQL query string
        String stmt = "INSERT INTO todos (title, completed) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(stmt)) {

            pstmt.setString(1, name);
            pstmt.setInt(2, age);

            int rowsInserted = pstmt.executeUpdate();
            System.out.println("Row inserted: " + rowsInserted);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        add("Example", 25);
    }
}