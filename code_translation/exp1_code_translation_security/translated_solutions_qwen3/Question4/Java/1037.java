import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentInserter {

    public static void insertStudent(String name, int age) {
        // Database configuration (replace with your actual values)
        String url = "jdbc:mysql://localhost:3306/yourDatabase"; // Replace with your DB URL
        String user = "yourUsername"; // Replace with your DB username
        String password = "yourPassword"; // Replace with your DB password

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection and execute the SQL statement
            try (Connection connection = DriverManager.getConnection(url, user, password);
                 Statement statement = connection.createStatement()) {

                // SQL insert statement (hardcoded as in the original JS code)
                String sql = "INSERT INTO STUDENTS (title, completed) VALUES ('Learn how to insert a new row', true)";

                // Execute the insert statement
                statement.executeUpdate(sql);

            }
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("SQL Exception occurred.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Call the insertStudent method with dummy values
        insertStudent("XXXXX", 0);
    }
}