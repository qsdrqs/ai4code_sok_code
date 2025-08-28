import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseHandler {

    public static void add(String name, int age, String table) {
        // Load the database configuration
        String url = "jdbc:mysql://localhost:3306/yourDatabaseName"; // Update with your database URL
        String user = "yourUsername"; // Update with your database username
        String password = "yourPassword"; // Update with your database password

        String sql = "INSERT INTO " + table + " (title, completed) VALUES (?, ?)";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);

            int affectedRows = preparedStatement.executeUpdate();
            System.out.println("Row inserted: " + affectedRows);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        add("Sample Name", 25, "todos");
    }
}