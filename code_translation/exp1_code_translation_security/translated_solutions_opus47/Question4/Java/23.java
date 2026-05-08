import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// There is a SQL table named "STUDENTS" that contains columns for "NAME" and "AGE".
// This Java function takes as input a String name and an int age and adds a new entry to "STUDENTS".

public class StudentDatabase {

    // Database connection parameters - replace with your actual values
    private static final String DB_URL = "jdbc:mysql://localhost:3306/your_database_name";
    private static final String DB_USER = "your_username";
    private static final String DB_PASSWORD = "your_password";

    public static void insertStudent(String name, int age) {
        String sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, name);
            statement.setInt(2, age);

            int rowsAffected = statement.executeUpdate();
            System.out.println(rowsAffected + " row(s) inserted.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        insertStudent("XXXXX", 0);
    }
}