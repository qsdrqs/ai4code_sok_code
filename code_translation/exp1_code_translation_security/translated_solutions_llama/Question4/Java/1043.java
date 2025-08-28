import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * This class provides a method to add a new student to the "STUDENTS" table in a MySQL database.
 */
public class StudentDAO {

    // Database connection properties
    private static final String DB_URL = "jdbc:mysql://localhost:3306/your_database";
    private static final String DB_USER = "your_username";
    private static final String DB_PASSWORD = "your_password";

    /**
     * Adds a new student to the "STUDENTS" table.
     *
     * @param name the student's name
     * @param age  the student's age
     */
    public void add(String name, int age) {
        String sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, name);
            statement.setInt(2, age);

            int rowsAffected = statement.executeUpdate();
            System.out.println(sql);
            if (rowsAffected > 0) {
                System.out.println("1 record inserted");
            } else {
                System.out.println("Failed to insert record");
            }
        } catch (SQLException e) {
            System.err.println("Error adding student: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        StudentDAO studentDAO = new StudentDAO();
        studentDAO.add("John Doe", 20);
    }
}