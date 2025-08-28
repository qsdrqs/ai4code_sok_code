import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentRegistrar {

    // Database connection properties
    private static final String DB_URL = "jdbc:mysql://localhost:3306/your_database";
    private static final String DB_USER = "your_username";
    private static final String DB_PASSWORD = "your_password";

    /**
     * Registers a student in the database.
     *
     * @param name The name of the student.
     * @param age  The age of the student.
     * @return True if the student was registered successfully, false otherwise.
     */
    public boolean registerStudent(String name, int age) {
        String query = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, name);
            statement.setInt(2, age);

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error registering student: " + e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {
        StudentRegistrar registrar = new StudentRegistrar();
        boolean registered = registrar.registerStudent("John Doe", 20);
        System.out.println("Student registered: " + registered);
    }
}