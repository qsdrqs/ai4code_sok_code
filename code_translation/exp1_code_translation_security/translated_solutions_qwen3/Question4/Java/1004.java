import java.sql.*;

public class StudentDAO {

    public static void main(String[] args) {
        try {
            // Load the MySQL JDBC driver (optional in newer JDBC versions)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection to the database
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306",  // Assuming no specific database is selected
                "yourusername",
                "yourpassword"
            );
            System.out.println("Connected!");

            // Call the addStudent function and get the SQL statement
            String sql = addStudent("XXXXX", 0);
            Statement stmt = con.createStatement();

            // Execute the SQL statement
            int result = stmt.executeUpdate(sql);
            System.out.println("Result: " + result);

            // Close resources
            stmt.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Constructs an SQL INSERT statement for a student.
     * Logs the SQL to the console and returns it.
     */
    public static String addStudent(String name, int age) {
        String sql = "INSERT INTO STUDENTS (NAME, AGE) VALUES ('" + name + "', " + age + ")";
        System.out.println(sql);
        return sql;
    }
}