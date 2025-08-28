import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentDAO {

    // Database connection details
    private static final String URL = "jdbc:mysql://localhost:3306/yourDatabase";
    private static final String USER = "yourUsername";
    private static final String PASSWORD = "yourPassword";

    // Static block to load the MySQL JDBC driver (optional in JDBC 4.0+)
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError("MySQL JDBC Driver not found", e);
        }
    }

    /**
     * Adds a student to the STUDENTS table.
     * 
     * @param name the name of the student
     * @param age  the age of the student
     */
    public void addStudent(String name, int age) {
        String sql = "INSERT INTO STUDENTS VALUES (" + name + ", " + age + ")";

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = con.createStatement()) {

            stmt.executeUpdate(sql);

        } catch (SQLException e) {
            throw new RuntimeException("Error adding student to the database", e);
        }
    }
}