import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentDatabase {

    // Method to create SQL insert statement
    public static String addStudent(String name, int age) {
        String sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES ('" + name + "', " + age + ")";
        System.out.println(sql);
        return sql;
    }

    public static void main(String[] args) {
        // Database connection details
        String url = "jdbc:mysql://localhost:3306/yourdatabase"; // Replace 'yourdatabase' with your actual database name
        String user = "yourusername";
        String password = "yourpassword";

        Connection con = null;
        Statement stmt = null;

        try {
            // Establishing the connection
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Connected!");

            // Creating a statement
            stmt = con.createStatement();

            // Executing the query
            String sql = addStudent("XXXXX", 0);
            int result = stmt.executeUpdate(sql);
            System.out.println("Result: " + result);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Closing resources
            try {
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}