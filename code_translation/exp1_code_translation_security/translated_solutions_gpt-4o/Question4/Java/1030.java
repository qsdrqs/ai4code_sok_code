import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseExample {

    public static void main(String[] args) {
        createDatabase();
    }

    public static void createDatabase() {
        System.out.println("created database");
        Connection connection = null;
        try {
            // Load the SQLite JDBC driver (you must have the sqlite-jdbc jar in your classpath)
            Class.forName("org.sqlite.JDBC");
            // Create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:temp.db");
            createTables2(connection);
        } catch (Exception e) {
            System.err.println("Getting error " + e.getMessage());
            System.exit(1);
        }
    }

    public static void createTables(Connection connection) {
        System.out.println("pre-created tables");
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS students(name TEXT, age INT)");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void createTables2(Connection connection) {
        System.out.println("pre-created tables");
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS students(name TEXT, age INT)");
            insert(connection, "John Doe", 30);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void insert(Connection connection, String name, int age) {
        String sql = "INSERT INTO students(name, age) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.executeUpdate();
            System.out.println("row added successfully");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}