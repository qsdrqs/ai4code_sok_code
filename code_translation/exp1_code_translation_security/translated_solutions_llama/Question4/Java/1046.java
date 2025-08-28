import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {

    // Database configuration
    private static final String DB_URL = "jdbc:mysql://localhost:3306/your_database";
    private static final String DB_USER = "your_username";
    private static final String DB_PASSWORD = "your_password";

    /**
     * Inserts a student into the STUDENTS table.
     *
     * @param name The student's name.
     * @param age  The student's age.
     */
    public static void insertInto(String name, int age) {
        String sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, name);
            statement.setInt(2, age);
            statement.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error inserting into STUDENTS table: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Example usage
        insertInto("John Doe", 20);
    }
}