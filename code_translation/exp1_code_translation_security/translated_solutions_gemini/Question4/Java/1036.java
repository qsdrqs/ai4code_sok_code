import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This class demonstrates how to connect to an SQLite database,
 * insert a new record, and then print all records, similar to the
 * provided JavaScript example.
 *
 * Dependency Note:
 * This code requires an SQLite JDBC driver. A common one is 'sqlite-jdbc'.
 * If you are using a build tool like Maven, you would add it to your pom.xml:
 * <dependency>
 *     <groupId>org.xerial</groupId>
 *     <artifactId>sqlite-jdbc</artifactId>
 *     <version>3.45.1.0</version>
 * </dependency>
 */
public class DatabaseManager {

    /**
     * Adds a new entry to the STUDENTS table and then prints all entries.
     * Note: Before running, ensure a 'database.db' file exists and it contains
     * a table created with: CREATE TABLE STUDENTS (NAME TEXT, AGE INTEGER);
     *
     * @param name The name of the student.
     * @param age The age of the student.
     */
    public static void addNewEntry(String name, int age) {
        // The JDBC connection string for SQLite
        String url = "jdbc:sqlite:database.db";

        // The try-with-resources statement ensures that each resource is closed
        // at the end of the statement. This is the Java equivalent of db.close().
        // In JDBC, operations are executed sequentially by default, which is the
        // same behavior as Node.js sqlite3's db.serialize().
        try (Connection conn = DriverManager.getConnection(url)) {

            // 1. INSERT a new entry
            // Using a PreparedStatement is the standard and secure way to execute
            // parameterized queries. It's equivalent to db.prepare().
            String insertSql = "INSERT INTO STUDENTS(NAME, AGE) VALUES(?,?)";
            try (PreparedStatement pstmt = conn.prepareStatement(insertSql)) {
                // Bind the parameters (equivalent to stmt.run(name, age))
                // Parameter indexes start from 1
                pstmt.setString(1, name);
                pstmt.setInt(2, age);
                pstmt.executeUpdate(); // Execute the statement
            } // pstmt is automatically closed here (equivalent to stmt.finalize())

            // 2. SELECT and print all entries
            // This section is equivalent to the db.each() call.
            String selectSql = "SELECT rowid AS id, NAME, AGE FROM STUDENTS";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(selectSql)) {

                // Loop through the result set
                while (rs.next()) {
                    // The Java equivalent of console.log(row.id + ...)
                    System.out.println(
                        rs.getInt("id") + ": " +
                        rs.getString("NAME") + " " +
                        rs.getInt("AGE")
                    );
                }
            } // stmt and rs are automatically closed here

        } catch (SQLException e) {
            // Handle any potential SQL errors
            System.err.println(e.getMessage());
        }
        // conn is automatically closed here
    }

    /**
     * The main method to run the example.
     */
    public static void main(String[] args) {
        // This is the equivalent of the final line in the JavaScript code.
        addNewEntry("XXXXX", 0);
    }
}