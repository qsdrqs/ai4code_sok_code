import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

public class StudentDatabase {

    public void addStudent(String name, int age) {
        // Database URL, username, and password should be specified according to your database setup
        String url = "jdbc:your_database_url";
        String user = "your_database_username";
        String password = "your_database_password";

        Connection connection = null;
        Statement statement = null;

        try {
            // Establish the connection
            connection = DriverManager.getConnection(url, user, password);

            // Create a statement
            statement = connection.createStatement();

            // Create the SQL command
            String command = "INSERT INTO STUDENTS (name, age) VALUES ('" + name + "', " + age + ");";

            // Execute the command
            statement.executeUpdate(command);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the resources
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        StudentDatabase db = new StudentDatabase();
        db.addStudent("John Doe", 20);
    }
}