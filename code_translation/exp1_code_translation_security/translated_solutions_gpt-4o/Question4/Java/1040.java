import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteExample {

    public static void main(String[] args) {
        // Create an in-memory SQLite database
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite::memory:")) {
            // Create the STUDENTS table if it doesn't exist
            try (Statement statement = connection.createStatement()) {
                statement.execute("CREATE TABLE IF NOT EXISTS STUDENTS (NAME TEXT, AGE INT)");
            }

            // Add new entries
            addNewEntry(connection, "XXXXX", 0);
            addNewEntry(connection, "XXXXX", 0);

            // Query and print the entries
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery("SELECT NAME, AGE FROM STUDENTS")) {
                while (resultSet.next()) {
                    String name = resultSet.getString("NAME");
                    int age = resultSet.getInt("AGE");
                    System.out.println("Name: " + name + ", Age: " + age);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void addNewEntry(Connection connection, String name, int age) {
        String sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}