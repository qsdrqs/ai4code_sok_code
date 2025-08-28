import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentManager {

    public static void addStudent(String name, int age) {
        try {
            // Load the UCanAccess JDBC driver
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

            // Establish a connection to the database
            // Replace "students.accdb" with the actual path to your Access database
            Connection connection = DriverManager.getConnection("jdbc:ucanaccess://students.accdb");

            // Construct the SQL INSERT command
            String command = "INSERT INTO STUDENTS VALUES ('" + name + "','" + age + "');";

            // Create a Statement and execute the command
            Statement stmt = connection.createStatement();
            stmt.execute(command);

            // Close the connection (Statement is automatically closed when Connection is closed)
            connection.close();

        } catch (ClassNotFoundException | SQLException e) {
            // Print any exceptions that occur
            e.printStackTrace();
        }
    }
}