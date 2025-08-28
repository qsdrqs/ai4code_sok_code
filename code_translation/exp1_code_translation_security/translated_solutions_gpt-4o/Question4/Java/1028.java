import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentDatabase {

    private Connection connection;

    public StudentDatabase(String url, String user, String password) throws SQLException {
        // Establish a connection to the database
        this.connection = DriverManager.getConnection(url, user, password);
    }

    public void addStudent(String name, int age) {
        String sql = "INSERT INTO STUDENTS (name, age) VALUES (?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            // Set the parameters for the prepared statement
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);

            // Execute the update
            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("1 record inserted");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            // Replace with your database URL, username, and password
            StudentDatabase db = new StudentDatabase("jdbc:mysql://localhost:3306/yourdatabase", "username", "password");
            db.addStudent("John Doe", 20);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}