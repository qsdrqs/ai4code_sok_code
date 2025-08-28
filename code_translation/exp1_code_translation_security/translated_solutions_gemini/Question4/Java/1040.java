import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteExample {

    /**
     * Adds a new entry to the STUDENTS table.
     * It first ensures the table exists, then inserts the data using a PreparedStatement
     * to prevent SQL injection.
     *
     * @param conn The active database connection.
     * @param name The name of the student.
     * @param age  The age of the student.
     */
    public static void addNewEntry(Connection conn, String name, int age) {
        // The 'db.serialize' in node-sqlite3 ensures sequential execution.
        // In Java, JDBC calls are synchronous by default, so we achieve the same
        // effect by executing statements one after another.

        // SQL to create the table if it doesn't exist.
        String createTableSql = "CREATE TABLE IF NOT EXISTS STUDENTS (NAME TEXT, AGE INT);";

        // SQL to insert a new student. Using '?' for parameters is a best practice.
        String insertSql = "INSERT INTO STUDENTS(NAME, AGE) VALUES(?, ?);";

        try (Statement stmt = conn.createStatement()) {
            // Create table (if it's not already there)
            stmt.execute(createTableSql);

            // Prepare and execute the insert statement
            try (PreparedStatement pstmt = conn.prepareStatement(insertSql)) {
                pstmt.setString(1, name);
                pstmt.setInt(2, age);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        // The connection string for an in-memory SQLite database
        String url = "jdbc:sqlite::memory:";

        // A try-with-resources statement ensures that the connection is closed automatically.
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                System.out.println("A connection to the in-memory database has been established.");

                // Add two new entries, just like in the JavaScript example
                addNewEntry(conn, "John Doe", 21);
                addNewEntry(conn, "Jane Smith", 24);

                // Query the database and print the results
                // The 'db.each' in node-sqlite3 iterates over each row.
                // The Java equivalent is to loop through a ResultSet.
                String selectSql = "SELECT NAME, AGE FROM STUDENTS";

                try (Statement stmt = conn.createStatement();
                     ResultSet rs = stmt.executeQuery(selectSql)) {
                    
                    System.out.println("\nRetrieving student data:");
                    // Loop through the result set
                    while (rs.next()) {
                        // The JS 'row' object is equivalent to the current state of the ResultSet
                        System.out.println(String.format("{ NAME: '%s', AGE: %d }",
                                rs.getString("NAME"),
                                rs.getInt("AGE")));
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}