import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentManager {

    private Connection connection;

    // Constructor to initialize the database connection
    public StudentManager(Connection connection) {
        this.connection = connection;
    }

    /**
     * Adds a student to the STUDENTS table.
     * 
     * @param name the name of the student
     * @param age the age of the student
     * @throws SQLException if a database access error occurs
     */
    public void addStudent(String name, int age) throws SQLException {
        // Construct the SQL INSERT statement
        String sql = "INSERT INTO STUDENTS (name, age) VALUES ('" + name + "', " + age + ")";

        // Execute the SQL statement using a try-with-resources block
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("1 record inserted");
        }
    }
}