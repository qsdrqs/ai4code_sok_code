import java.sql.*;

public class Main {

    public static void main(String[] args) {
        try {
            // Load the SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");

            // Create a connection to an in-memory database
            Connection conn = DriverManager.getConnection("jdbc:sqlite::memory:");

            // Create a statement object
            Statement stmt = conn.createStatement();

            // Add new entries
            addNewEntry(conn, "XXXXX", 0);
            addNewEntry(conn, "XXXXX", 0);

            // Query the database
            queryDatabase(conn);

            // Close the connection
            conn.close();
        } catch (ClassNotFoundException e) {
            System.out.println("SQLite JDBC Driver not found.");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Adds a new entry to the STUDENTS table.
     *
     * @param conn the database connection
     * @param name the student's name
     * @param age  the student's age
     * @throws SQLException if a database access error occurs
     */
    public static void addNewEntry(Connection conn, String name, int age) throws SQLException {
        // Create table if it does not exist
        String createTableQuery = "CREATE TABLE IF NOT EXISTS STUDENTS (NAME text, AGE INT)";
        conn.prepareStatement(createTableQuery).execute();

        // Insert a new entry
        String insertQuery = "INSERT INTO STUDENTS VALUES (?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(insertQuery);
        pstmt.setString(1, name);
        pstmt.setInt(2, age);
        pstmt.executeUpdate();
        pstmt.close();
    }

    /**
     * Queries the STUDENTS table and prints the results.
     *
     * @param conn the database connection
     * @throws SQLException if a database access error occurs
     */
    public static void queryDatabase(Connection conn) throws SQLException {
        String query = "SELECT NAME, AGE FROM STUDENTS";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            System.out.println("Name: " + rs.getString("NAME") + ", Age: " + rs.getInt("AGE"));
        }

        rs.close();
        stmt.close();
    }
}