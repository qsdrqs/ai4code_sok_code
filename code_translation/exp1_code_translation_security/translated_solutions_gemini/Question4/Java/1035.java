import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
// Uncomment the imports below to use the commented-out query section
// import java.sql.ResultSet;

/**
 * This class demonstrates how to connect to an SQLite database, create a table,
 * and insert data using JDBC, mirroring the functionality of the original JavaScript code.
 */
public class StudentDatabaseManager {

    // The database connection string. "jdbc:sqlite:" is the standard prefix for the SQLite JDBC driver.
    // "studentdb" is the name of the database file.
    private static final String DB_URL = "jdbc:sqlite:studentdb";

    /**
     * The main entry point for the application.
     */
    public static void main(String[] args) {
        // The try-with-resources statement ensures that the connection is automatically closed
        // at the end of the block, which is equivalent to db.close() in the JS code.
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            if (conn != null) {
                System.out.println("Connected to the database.");

                // SQL statement for creating a new table
                String createTableSql = "CREATE TABLE IF NOT EXISTS STUDENTS (NAME TEXT, AGE INT)";

                // A Statement is used for executing static SQL statements
                try (Statement stmt = conn.createStatement()) {
                    // Execute the SQL statement
                    stmt.execute(createTableSql);
                    System.out.println("Table 'STUDENTS' created or already exists.");
                }

                // Insert data into the table by calling the insert method
                insert(conn, "Foo", 42);
                insert(conn, "Bar", 55);
            }
        } catch (SQLException e) {
            // Print a detailed error message if something goes wrong
            e.printStackTrace();
        }
    }

    /**
     * Inserts a new student record into the STUDENTS table.
     * This method is equivalent to the 'insert' arrow function in the JavaScript code.
     *
     * @param conn The active database connection.
     * @param name The name of the student.
     * @param age  The age of the student.
     */
    public static void insert(String name, int age) {
        // The node-sqlite3 `serialize` function ensures sequential execution.
        // JDBC on a single connection is sequential by default, so no direct equivalent is needed.
        String insertSql = "INSERT INTO STUDENTS(NAME, AGE) VALUES(?, ?)";

        // A PreparedStatement is used for executing parameterized queries, which prevents SQL injection.
        // This is equivalent to db.prepare(...) in the JS code.
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(insertSql)) {
            
            // Bind the values to the prepared statement's parameters
            // This is equivalent to stmt.run([name, age])
            pstmt.setString(1, name);
            pstmt.setInt(2, age);

            // Execute the update
            pstmt.executeUpdate();
            System.out.println("Successfully inserted record: NAME=" + name + ", AGE=" + age);

            /*
            // This block is the Java equivalent of the commented-out db.each() in the original code.
            String querySql = "SELECT * FROM STUDENTS";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(querySql)) {
                
                // Loop through the result set
                while (rs.next()) {
                    System.out.println("Row -> NAME: " + rs.getString("NAME") + ", AGE: " + rs.getInt("AGE"));
                }
            }
            */

        } catch (SQLException e) {
            e.printStackTrace();
        }
        // The PreparedStatement is automatically closed by the try-with-resources block,
        // which is equivalent to stmt.finalize() in the JS code.
    }
}