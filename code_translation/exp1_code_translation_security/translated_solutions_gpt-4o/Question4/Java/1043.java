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

    // Method to add a new entry to the SQL table "STUDENTS"
    public void add(String name, int age) {
        String sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
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
            db.add("John Doe", 25);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}