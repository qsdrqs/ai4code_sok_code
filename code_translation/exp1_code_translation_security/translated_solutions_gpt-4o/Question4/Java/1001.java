import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseHelper {

    private static final String URL = "jdbc:your_database_url";
    private static final String USER = "your_database_user";
    private static final String PASSWORD = "your_database_password";

    public static void addSQLentry(String name, int age) {
        String sql = "INSERT INTO students (name, age) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, name);
            pstmt.setInt(2, age);

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                try (var rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        long id = rs.getLong(1);
                        System.out.println("User added with id: " + id);
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        addSQLentry("John Doe", 25);
    }
}