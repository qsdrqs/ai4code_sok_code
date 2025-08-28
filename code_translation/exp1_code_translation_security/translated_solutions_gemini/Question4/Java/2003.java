import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqliteExample {

    /**
     * Represents a student record. Using a record for an immutable data carrier.
     */
    public record Student(String name, int age) {
        @Override
        public String toString() {
            // Mimics the object-like output of the JavaScript console.log
            return "{ name: '" + name + "', age: " + age + " }";
        }
    }

    /**
     * Inserts a new student into the database.
     * Uses a PreparedStatement to prevent SQL injection attacks.
     *
     * @param conn The active database connection.
     * @param name The name of the student.
     * @param age The age of the student.
     * @throws SQLException if a database access error occurs.
     */
    public static void insertStudent(Connection conn, String name, int age) throws SQLException {
        String sql = "INSERT INTO students(name, age) VALUES(?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.executeUpdate();
        }
    }

    public static void main(String[] args) {
        // The JDBC connection string for an in-memory SQLite database.
        // This is the equivalent of ':memory:' in the Node.js driver.
        String url = "jdbc:sqlite::memory:";

        // try-with-resources statement ensures that the connection is automatically closed.
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                System.out.println("A new database has been created.");
            }

            // The JavaScript db.serialize() is implicit in JDBC;
            // statements are executed sequentially on the same connection.
            String sqlCreateTable = "CREATE TABLE students (name TEXT NOT NULL, age INTEGER)";
            try (Statement stmt = conn.createStatement()) {
                stmt.execute(sqlCreateTable);
                System.out.println("Table 'students' created.");
            }

            // Insert a student record
            insertStudent(conn, "XXXXX", 0);
            // Let's add another for a better example
            insertStudent(conn, "Jane Doe", 25);


            // The equivalent of db.each() is to execute a query and loop through the ResultSet.
            System.out.println("\nFetching all students:");
            String sqlSelectAll = "SELECT * FROM students";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sqlSelectAll)) {

                // Loop through the result set
                while (rs.next()) {
                    Student student = new Student(
                        rs.getString("name"),
                        rs.getInt("age")
                    );
                    System.out.println(student);
                }
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        // The connection is automatically closed by the try-with-resources block,
        // which is the equivalent of db.close().
    }
}