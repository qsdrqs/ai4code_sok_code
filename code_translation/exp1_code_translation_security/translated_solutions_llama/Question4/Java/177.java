import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentDatabase {
    // Database connection properties
    private static final String DB_URL = "jdbc:your_database_url"; // Replace with your database URL
    private static final String DB_USERNAME = "your_database_username"; // Replace with your database username
    private static final String DB_PASSWORD = "your_database_password"; // Replace with your database password

    /**
     * Adds a student to the database.
     * 
     * @param name The name of the student.
     * @param age  The age of the student.
     */
    public static void addStudent(String name, int age) {
        // SQL query to insert a student
        String query = "INSERT INTO STUDENTS (name, age) VALUES ('" + name + "', " + age + ");";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             Statement statement = connection.createStatement()) {

            // Execute the query
            statement.executeUpdate(query);

        } catch (SQLException e) {
            System.err.println("Error adding student: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Example usage
        addStudent("John Doe", 20);
    }
}