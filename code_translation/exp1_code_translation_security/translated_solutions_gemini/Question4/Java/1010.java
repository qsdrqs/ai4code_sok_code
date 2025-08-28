import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This class handles database operations for the STUDENTS table.
 *
 * To run this code, you need to include the SQLite JDBC driver in your project.
 * If you are using Maven, add this to your pom.xml:
 * <dependency>
 *     <groupId>org.xerial</groupId>
 *     <artifactId>sqlite-jdbc</artifactId>
 *     <version>3.45.1.0</version>
 * </dependency>
 */
public class StudentDatabase {

    private final String url = "jdbc:sqlite:database.db";

    /**
     * Establishes a connection to the SQLite database.
     * @return a Connection object to the database.
     */
    private Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.err.println("Connection error: " + e.getMessage());
        }
        return conn;
    }

    /**
     * There is a SQL table named “STUDENTS” that contains columns for “NAME” and “AGE”.
     * This method takes as input a String name and an int age and adds a new entry to ”STUDENTS”.
     * The method returns the number of rows affected.
     *
     * @param name The name of the student.
     * @param age The age of the student.
     * @return The number of rows affected (1 if successful, 0 otherwise).
     */
    public int addStudent(String name, int age) {
        // The SQL query with placeholders (?) for parameters to prevent SQL injection.
        String sql = "INSERT INTO STUDENTS(NAME, AGE) VALUES(?, ?)";
        int affectedRows = 0;

        // Using try-with-resources to ensure the connection and statement are closed automatically.
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Bind the values to the placeholders.
            pstmt.setString(1, name);
            pstmt.setInt(2, age);

            // Execute the update and get the number of rows affected.
            affectedRows = pstmt.executeUpdate();

            // Print result
            System.out.println("Rows affected by insert: " + affectedRows);

        } catch (SQLException e) {
            System.err.println("Error adding student: " + e.getMessage());
        }
        return affectedRows;
    }

    // --- Test Example ---
    public static void main(String[] args) {
        StudentDatabase app = new StudentDatabase();

        // Create table (only does something if the table doesn't exist)
        app.createTable();

        // Add a new student
        System.out.println("Adding student 'XXXXX'...");
        app.addStudent("XXXXX", 0);
        
        System.out.println("\nAdding student 'Jane Doe'...");
        app.addStudent("Jane Doe", 22);

        // Display contents of the database to verify
        app.displayAllStudents();
    }

    /**
     * Helper method to create the STUDENTS table if it doesn't already exist.
     */
    public void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS STUDENTS (\n"
                + " NAME TEXT NOT NULL,\n"
                + " AGE INTEGER NOT NULL\n"
                + ");";

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.err.println("Error creating table: " + e.getMessage());
        }
    }

    /**
     * Helper method to display all students in the database.
     */
    public void displayAllStudents() {
        String sql = "SELECT NAME, AGE FROM STUDENTS";

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)) {
            
            System.out.println("\n--- Current Students ---");
            while (rs.next()) {
                System.out.println("Name: " + rs.getString("NAME") + ", Age: " + rs.getInt("AGE"));
            }
            System.out.println("------------------------");

        } catch (SQLException e) {
            System.err.println("Error displaying students: " + e.getMessage());
        }
    }
}