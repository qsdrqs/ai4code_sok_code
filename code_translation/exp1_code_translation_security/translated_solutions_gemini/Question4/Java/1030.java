import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class SqliteExample {

    // The main method acts as the entry point, similar to the IIFE in the JS code.
    public static void main(String[] args) {
        createDatabase();
    }

    /**
     * Creates a new database file (if it doesn't exist) and establishes a connection.
     * Then, it proceeds to create tables and insert data.
     * This method orchestrates the entire sequence of operations.
     */
    public static void createDatabase() {
        // The JDBC URL for SQLite. It will create 'temp.db' in the project's root directory.
        String url = "jdbc:sqlite:temp.db";

        // The try-with-resources statement ensures the connection is automatically closed.
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                System.out.println("created database");
                // Since JDBC is synchronous, we can just call the next function in sequence.
                createTables(conn);
            }
        } catch (SQLException e) {
            System.err.println("Getting error " + e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Creates the 'students' table.
     * This corresponds to the 'createTables2' function in the JS code.
     * @param conn The active database connection.
     */
    public static void createTables(Connection conn) {
        String sql = "CREATE TABLE IF NOT EXISTS students(name TEXT, age INT)";

        // try-with-resources ensures the Statement is closed automatically.
        try (Statement stmt = conn.createStatement()) {
            System.out.println("pre-created tables");
            // Execute the SQL statement to create the table.
            stmt.execute(sql);
            // After successfully creating the table, insert a row.
            insert(conn, "John Doe", 30);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Inserts a new row into the 'students' table.
     * @param conn The active database connection.
     * @param name The name of the student.
     * @param age The age of the student.
     */
    public static void insert(Connection conn, String name, int age) {
        // Using a PreparedStatement with '?' placeholders is the standard, secure way
        // to insert data and prevent SQL injection attacks.
        String sql = "INSERT INTO students(name, age) VALUES(?, ?)";

        // try-with-resources ensures the PreparedStatement is closed automatically.
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Set the values for the '?' placeholders.
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            // Execute the insert operation.
            pstmt.executeUpdate();
            System.out.println("row added successfully");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}