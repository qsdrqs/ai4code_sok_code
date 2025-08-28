import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This class demonstrates how to connect to an SQLite database,
 * insert data, and query data using JDBC.
 *
 * Dependency:
 * This code requires the SQLite JDBC driver. If you are using a build tool
 * like Maven, you would add the following to your pom.xml:
 *
 * <dependency>
 *     <groupId>org.xerial</groupId>
 *     <artifactId>sqlite-jdbc</artifactId>
 *     <version>3.45.1.0</version> <!-- Use the latest version -->
 * </dependency>
 */
public class StudentDatabaseManager {

    // The database connection URL. For SQLite, it's "jdbc:sqlite:<path_to_db_file>"
    private static final String DB_URL = "jdbc:sqlite:/home/codex/Documents/User_Study_UI/database.db";

    /**
     * Inserts a new student record into the STUDENTS table.
     * Uses a PreparedStatement to prevent SQL injection.
     *
     * @param conn The active database connection.
     * @param name The name of the student.
     * @param age The age of the student.
     */
    public static void studentWrite(Connection conn, String name, int age) {
        String sql = "INSERT INTO STUDENTS(NAME, AGE) VALUES(?, ?)";

        // Using try-with-resources to ensure the PreparedStatement is closed automatically
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name); // Set the first parameter (NAME)
            pstmt.setInt(2, age);     // Set the second parameter (AGE)
            pstmt.executeUpdate();    // Execute the insert operation
            System.out.println("Record for " + name + " inserted successfully.");
        } catch (SQLException e) {
            System.err.println("Error inserting record: " + e.getMessage());
        }
    }

    /**
     * Selects all records from the STUDENTS table and prints them to the console.
     *
     * @param conn The active database connection.
     */
    public static void selectAllStudents(Connection conn) {
        String sql = "SELECT * FROM STUDENTS";

        // Using try-with-resources to ensure Statement and ResultSet are closed
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\n--- All Students ---");
            // Loop through the result set
            while (rs.next()) {
                // Retrieve data by column name
                String name = rs.getString("NAME");
                int age = rs.getInt("AGE");

                // Mimic the JavaScript output: console.log(row, row)
                String rowOutput = String.format("Row{NAME=%s, AGE=%d}", name, age);
                System.out.println(rowOutput + " " + rowOutput);
            }
            System.out.println("--------------------");

        } catch (SQLException e) {
            System.err.println("Error querying records: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Using try-with-resources to ensure the connection is closed automatically
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            if (conn != null) {
                System.out.println("Successfully connected to the SQLite database.");

                // 1. Write a new student record to the database
                studentWrite(conn, "XXXXX", 0);

                // 2. Select and display all student records
                selectAllStudents(conn);
            }
        } catch (SQLException e) {
            System.err.println("Database connection failed: " + e.getMessage());
        }
    }
}