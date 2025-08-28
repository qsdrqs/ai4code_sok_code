import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentDAO {

    // Database connection properties
    private static final String DB_URL = "jdbc:mysql://localhost:3306/your_database";
    private static final String DB_USER = "your_username";
    private static final String DB_PASSWORD = "your_password";

    public void addStudent(String name, int age) {
        String sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, name);
            statement.setInt(2, age);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("1 record inserted");
            }

        } catch (SQLException e) {
            System.err.println("Error inserting student: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        StudentDAO studentDAO = new StudentDAO();
        studentDAO.addStudent("John Doe", 20);
    }
}