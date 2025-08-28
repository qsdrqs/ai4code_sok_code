import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentDAO {

    /**
     * Inserts a student record into the STUDENTS database.
     *
     * @param name the student's name
     * @param age  the student's age
     * @throws SQLException           if a database access error occurs
     * @throws ClassNotFoundException if the MySQL JDBC driver class is not found
     */
    public static void insert(String name, int age) throws SQLException, ClassNotFoundException {

        // 1) Load the MySQL JDBC driver (the driver jar will be on the classpath in your build).
        Class.forName("com.mysql.cj.jdbc.Driver");

        // 2) Connection parameters.
        String url      = "jdbc:mysql://localhost:3306/STUDENTS"; // database = STUDENTS
        String user     = "yourusername";
        String password = "yourpassword";

        // 3) SQL to execute (parameterized to avoid SQL-injection risk).
        String sql = "INSERT INTO students (name, age) VALUES (?, ?)";

        // 4) Perform the operation using try-with-resources so everything is closed automatically.
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, name);
            statement.setInt(2, age);

            int rowsInserted = statement.executeUpdate();
            System.out.println(rowsInserted + " record inserted");
        }
    }
}