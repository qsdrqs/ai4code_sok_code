import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentRegistrar {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/your_database";
    private static final String USER = "your_username";
    private static final String PASS = "your_password";

    public boolean registerStudent(String name, int age) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean isRegistered = false;

        try {
            // Establish a connection
            connection = DriverManager.getConnection(DB_URL, USER, PASS);

            // Create SQL query
            String query = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";

            // Create a PreparedStatement
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);

            // Execute the query
            int rowsAffected = preparedStatement.executeUpdate();
            isRegistered = rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return isRegistered;
    }

    public static void main(String[] args) {
        StudentRegistrar registrar = new StudentRegistrar();
        boolean result = registrar.registerStudent("John Doe", 20);
        System.out.println("Student registered: " + result);
    }
}