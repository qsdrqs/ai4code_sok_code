import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Direct translation of the original JavaScript/Node program.
 * The program
 *   1. Creates (or opens) temp.db
 *   2. Creates table students
 *   3. Inserts one row
 *   4. Prints every row in the table
 */
public class DatabaseDemo {

    public static void main(String[] args) {
        // Equivalent to the IIFE in JavaScript
        createDatabase();
    }

    /* ---------------------------------------------------------------------- */
    /* The methods below mirror the structure of the JS code                  */
    /* ---------------------------------------------------------------------- */

    private static void createDatabase() {
        System.out.println("created database");

        // Make sure the JDBC driver is available
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            // In a real project you might log/throw; for this example we exit
            System.err.println("Cannot load SQLite JDBC driver.");
            System.exit(1);
        }

        // Creating/opening the database file
        final String url = "jdbc:sqlite:temp.db";

        try (Connection conn = DriverManager.getConnection(url)) {
            createTablesAndInsert(conn);
        } catch (SQLException ex) {
            System.out.println("Getting error " + ex.getMessage());
            System.exit(1);
        }
    }

    private static void createTablesAndInsert(Connection conn) throws SQLException {
        System.out.println("pre-created tables");

        String createSql = "CREATE TABLE IF NOT EXISTS students(name TEXT, age INTEGER)";
        try (Statement st = conn.createStatement()) {
            st.execute(createSql);
        }

        // Equivalent to: insert(db, 'John Doe', 30);
        insert(conn, "John Doe", 30);

        // Equivalent to: db.all("select * FROM students", ...)
        queryAndPrint(conn);
    }

    private static void insert(Connection conn, String name, int age) throws SQLException {
        String insertSql = "INSERT INTO students(name, age) VALUES(?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(insertSql)) {
            ps.setString(1, name);
            ps.setInt(2, age);
            ps.executeUpdate();
            System.out.println("row added successfully");
        }
    }

    private static void queryAndPrint(Connection conn) throws SQLException {
        String querySql = "SELECT * FROM students";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(querySql)) {

            while (rs.next()) {
                String n = rs.getString("name");
                int a  = rs.getInt("age");
                System.out.println(n + " " + a);
            }
        }
    }
}