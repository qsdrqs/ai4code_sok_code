import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentDAO {

    // Database connection properties
    private static final String DB_URL = "jdbc:mysql://localhost:3306/your_database";
    private static final String DB_USER = "your_username";
    private static final String DB_PASSWORD = "your_password";

    /**
     * Adds a student to the students table in the MySQL database.
     *
     * @param name The name of the student.
     * @param age  The age of the student.
     */
    public void add(String name, int age) {
        // SQL query to insert a student into the students table
        String query = "INSERT INTO students (name, age) VALUES (?, ?)";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {

            // Set the query parameters
            statement.setString(1, name);
            statement.setInt(2, age);

            // Execute the query
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Added " + name + " to students table");
            } else {
                System.out.println("Failed to add " + name + " to students table");
            }

        } catch (SQLException e) {
            System.err.println("Error adding student to database: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        StudentDAO studentDAO = new StudentDAO();
        studentDAO.add("John Doe", 20);
    }
}