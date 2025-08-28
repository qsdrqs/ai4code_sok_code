import java.sql.*;

public class Main {
    public static void main(String[] args) {
        // Database connection URL
        String url = "jdbc:sqlite:studentdb";

        try (Connection conn = DriverManager.getConnection(url)) {
            // Create a statement object
            try (Statement stmt = conn.createStatement()) {
                // Create table if it doesn't exist
                String createTableQuery = "CREATE TABLE IF NOT EXISTS STUDENTS (NAME TEXT, AGE INT)";
                stmt.execute(createTableQuery);
            }

            // Insert data into the table
            insert(conn, "Foo", 42);
            insert(conn, "Bar", 55);

            // Fetch and print all rows from the table
            // printAllRows(conn);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Inserts a new student into the STUDENTS table.
     *
     * @param conn Database connection
     * @param name Student's name
     * @param age  Student's age
     * @throws SQLException If an error occurs during the insertion
     */
    public static void insert(Connection conn, String name, int age) throws SQLException {
        // Prepare the insert query
        String insertQuery = "INSERT INTO STUDENTS(NAME, AGE) VALUES (?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.executeUpdate();
        }
    }

    /**
     * Prints all rows from the STUDENTS table.
     *
     * @param conn Database connection
     * @throws SQLException If an error occurs during the query execution
     */
    public static void printAllRows(Connection conn) throws SQLException {
        // Prepare the select query
        String selectQuery = "SELECT * FROM STUDENTS";

        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(selectQuery)) {
            while (rs.next()) {
                System.out.println("Name: " + rs.getString("NAME") + ", Age: " + rs.getInt("AGE"));
            }
        }
    }
}