import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseExample {

    public static void addNewEntry(String name, int age) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Load the SQLite JDBC driver (you must have the SQLite JDBC library in your classpath)
            Class.forName("org.sqlite.JDBC");

            // Establish a connection to the database
            connection = DriverManager.getConnection("jdbc:sqlite:database.db");

            // Prepare the SQL statement
            String sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);

            // Execute the insert statement
            preparedStatement.executeUpdate();

            // Query the database to verify the insertion
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT rowid AS id, NAME, AGE FROM STUDENTS");

            // Print the results
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String studentName = resultSet.getString("NAME");
                int studentAge = resultSet.getInt("AGE");
                System.out.println(id + ": " + studentName + " " + studentAge);
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        addNewEntry("XXXXX", 0);
    }
}