import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    // Path to the SQLite database
    private static final String DB_PATH = "/home/codex/Documents/User_Study_UI/database.db";

    // One shared connection for this simple example
    private static Connection connection;

    public static void main(String[] args) {
        try {
            // Load SQLite JDBC driver (the driver JAR must be on the class-path)
            Class.forName("org.sqlite.JDBC");

            // Open the database
            connection = DriverManager.getConnection("jdbc:sqlite:" + DB_PATH);

            // Insert a record
            studentWrite("XXXXX", 0);

            // Read and display everything in STUDENTS
            printAllStudents();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Clean up
            if (connection != null) {
                try { connection.close(); } catch (SQLException ignored) {}
            }
        }
    }

    /**
     * Inserts a row into the STUDENTS table.
     */
    private static void studentWrite(String name, int age) throws SQLException {
        final String sql = "INSERT INTO STUDENTS(NAME, AGE) VALUES(?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setInt(2, age);
            stmt.executeUpdate();
        }
    }

    /**
     * Reads every row from STUDENTS and prints it to stdout.
     */
    private static void printAllStudents() throws SQLException {
        final String sql = "SELECT * FROM STUDENTS";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            ResultSetMetaData meta = rs.getMetaData();
            int columnCount = meta.getColumnCount();

            while ( rs.next() ) {
                // Display all columns for the current row
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(meta.getColumnName(i) + "=" + rs.getObject(i) + " ");
                }
                System.out.println();
            }
        }
    }
}