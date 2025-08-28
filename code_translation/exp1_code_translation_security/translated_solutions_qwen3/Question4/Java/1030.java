import java.sql.*;

public class SQLiteExample {

    public static void main(String[] args) {
        createDatabase();
    }

    static void createDatabase() {
        Connection conn = null;
        try {
            // Load the SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");

            // Connect to the database (creates it if it doesn't exist)
            conn = DriverManager.getConnection("jdbc:sqlite:temp.db");
            System.out.println("Created database");

            // Create table and insert data
            createTables2(conn);

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Close the connection
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    static void createTables2(Connection conn) {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS students(name TEXT, age INT)";
        try (Statement stmt = conn.createStatement()) {
            System.out.println("pre-created tables");
            stmt.execute(createTableSQL);
            insert(conn, "John Doe", 30);
        } catch (SQLException e) {
            System.err.println("Error creating table: " + e.getMessage());
        }
    }

    static void insert(Connection conn, String name, int age) {
        String sql = "INSERT INTO students(name, age) VALUES(?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.executeUpdate();
            System.out.println("Row added successfully");
        } catch (SQLException e) {
            System.err.println("Insert error: " + e.getMessage());
        }
    }
}